package com.example.util

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.sin

object SoundManager {
    var sfxEnabled: Boolean = true
    var musicEnabled: Boolean = true
    var volumeLevel: Float = 0.8f

    private val audioScope = CoroutineScope(Dispatchers.Default)
    private var musicJob: Job? = null

    // Helper to generate and play PCM waveform
    private fun playFrequencies(frequencies: List<Pair<Double, Int>>, volume: Float = 0.5f) {
        if (!sfxEnabled) return
        audioScope.launch {
            try {
                val sampleRate = 22050
                val totalDurationMs = frequencies.sumOf { it.second }
                val totalSamples = (sampleRate * totalDurationMs / 1000)
                val buffer = ShortArray(totalSamples)

                var sampleIndex = 0
                for ((freq, durationMs) in frequencies) {
                    val count = (sampleRate * durationMs / 1000)
                    for (i in 0 until count) {
                        if (sampleIndex >= buffer.size) break
                        val t = 2.0 * Math.PI * i / (sampleRate / freq)
                        val envelope = if (i < count * 0.1) {
                            (i / (count * 0.1)).toFloat()
                        } else if (i > count * 0.7) {
                            ((count - i) / (count * 0.3)).toFloat()
                        } else 1.0f

                        val sample = (sin(t) * Short.MAX_VALUE * volume * envelope * volumeLevel).toInt()
                        buffer[sampleIndex++] = sample.coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
                    }
                }

                val audioTrack = AudioTrack.Builder()
                    .setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_GAME)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build()
                    )
                    .setAudioFormat(
                        AudioFormat.Builder()
                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                            .setSampleRate(sampleRate)
                            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                            .build()
                    )
                    .setBufferSizeInBytes(buffer.size * 2)
                    .setTransferMode(AudioTrack.MODE_STATIC)
                    .build()

                audioTrack.write(buffer, 0, buffer.size)
                audioTrack.play()
                delay(totalDurationMs.toLong() + 50)
                audioTrack.stop()
                audioTrack.release()
            } catch (_: Exception) {
                // Ignore audio play errors gracefully
            }
        }
    }

    fun playClick() {
        playFrequencies(listOf(Pair(600.0, 40)), volume = 0.3f)
    }

    fun playCorrect() {
        // Cheerful C-E-G chime
        playFrequencies(
            listOf(
                Pair(523.25, 90), // C5
                Pair(659.25, 90), // E5
                Pair(783.99, 160) // G5
            ),
            volume = 0.5f
        )
    }

    fun playWrong() {
        // Soft positive error sound (not discouraging)
        playFrequencies(
            listOf(
                Pair(329.63, 100), // E4
                Pair(277.18, 140)  // C#4
            ),
            volume = 0.35f
        )
    }

    fun playStar() {
        // Sparkling sequence
        playFrequencies(
            listOf(
                Pair(587.33, 70), // D5
                Pair(739.99, 70), // F#5
                Pair(880.00, 70), // A5
                Pair(1174.66, 180) // D6
            ),
            volume = 0.5f
        )
    }

    fun playTrophy() {
        // Fanfare
        playFrequencies(
            listOf(
                Pair(523.25, 120),
                Pair(523.25, 120),
                Pair(523.25, 120),
                Pair(659.25, 240),
                Pair(783.99, 360)
            ),
            volume = 0.6f
        )
    }

    fun playDiceRoll() {
        playFrequencies(
            listOf(
                Pair(400.0, 40),
                Pair(480.0, 40),
                Pair(550.0, 40),
                Pair(650.0, 70)
            ),
            volume = 0.4f
        )
    }

    fun playLevelUp() {
        playFrequencies(
            listOf(
                Pair(440.0, 80),
                Pair(554.37, 80),
                Pair(659.25, 80),
                Pair(880.0, 200)
            ),
            volume = 0.5f
        )
    }

    fun playMelody(notes: List<Pair<Double, Int>>, onNoteProgress: ((Int) -> Unit)? = null, onComplete: (() -> Unit)? = null) {
        if (!musicEnabled) {
            onComplete?.invoke()
            return
        }
        stopMusic()
        musicJob = audioScope.launch {
            try {
                for ((index, note) in notes.withIndex()) {
                    if (!isActive || !musicEnabled) break
                    onNoteProgress?.invoke(index)
                    if (note.first > 0) {
                        playFrequencies(listOf(note), volume = 0.35f)
                    }
                    delay(note.second.toLong())
                }
            } finally {
                onComplete?.invoke()
            }
        }
    }

    fun stopMusic() {
        musicJob?.cancel()
        musicJob = null
    }
}
