package com.example.data.model

data class LiteracySong(
    val id: Int,
    val title: String,
    val themeDescription: String,
    val tempoBpm: Int,
    val lyricsLines: List<LyricLine>,
    val melodyNotes: List<Pair<Double, Int>> // Frequency Hz to duration Ms
)

data class LyricLine(
    val lineText: String,
    val startTimeMs: Long,
    val durationMs: Long
)

object SongsData {
    // Musical note frequencies (Middle Octaves C4 to C6)
    private const val C4 = 261.63
    private const val D4 = 293.66
    private const val E4 = 329.63
    private const val F4 = 349.23
    private const val G4 = 392.00
    private const val A4 = 440.00
    private const val B4 = 493.88
    private const val C5 = 523.25
    private const val D5 = 587.33
    private const val E5 = 659.25
    private const val G5 = 783.99

    val songs = listOf(
        LiteracySong(
            id = 1,
            title = "Rajin Membaca Jendela Dunia",
            themeDescription = "Lagu ceria pembangkit semangat membuka lembaran buku setiap hari",
            tempoBpm = 110,
            lyricsLines = listOf(
                LyricLine("Pagi berseri mentari tersenyum cerah,", 0, 3000),
                LyricLine("Kubuka buku penuh cerita indah.", 3000, 3000),
                LyricLine("Lembar demi lembar kata menyapa riang,", 6000, 3500),
                LyricLine("Membawa angan melesat terbang tinggi melayang!", 9500, 4000),
                LyricLine("Reff: Rajin membaca membuka cakrawala dunia,", 13500, 3500),
                LyricLine("Pintar cerdas berbudi luhur mulia,", 17000, 3500),
                LyricLine("Ayo kawan kita baca bersama-sama,", 20500, 3500),
                LyricLine("Menjadi juara literasi kebanggaan bangsa!", 24000, 4000)
            ),
            melodyNotes = listOf(
                Pair(C5, 400), Pair(E5, 400), Pair(G5, 600), Pair(E5, 400),
                Pair(D5, 400), Pair(F4, 400), Pair(A4, 600), Pair(G4, 400),
                Pair(C5, 400), Pair(E5, 400), Pair(G5, 600), Pair(C6_ALT(C5), 600),
                Pair(B4, 400), Pair(A4, 400), Pair(G4, 800),
                Pair(E5, 500), Pair(G5, 500), Pair(A4, 500), Pair(C5, 700),
                Pair(D5, 400), Pair(E5, 400), Pair(C5, 800)
            )
        ),
        LiteracySong(
            id = 2,
            title = "Buku Sahabat Sejatiku",
            themeDescription = "Kisah tentang buku sebagai sahabat setia penuntun ilmu dan imajinasi",
            tempoBpm = 100,
            lyricsLines = listOf(
                LyricLine("Buku kecil di atas meja belajarku,", 0, 3200),
                LyricLine("Kau temani di setiap langkah waktuku.", 3200, 3200),
                LyricLine("Dari samudra hingga bintang di angkasa,", 6400, 3400),
                LyricLine("Kisahmu hidup dalam benak penuh pesona.", 9800, 3800),
                LyricLine("Buku sahabatku, lentera penerang jalanku,", 13600, 3600),
                LyricLine("Menuntun langkah meraih cita-citaku!", 17200, 4000)
            ),
            melodyNotes = listOf(
                Pair(G4, 450), Pair(C5, 450), Pair(E5, 500), Pair(D5, 400),
                Pair(C5, 400), Pair(D5, 400), Pair(E5, 600), Pair(G4, 400),
                Pair(A4, 450), Pair(C5, 450), Pair(D5, 500), Pair(C5, 700)
            )
        ),
        LiteracySong(
            id = 3,
            title = "Ayo ke Perpustakaan",
            themeDescription = "Ajakan bersemangat mengunjungi perpustakaan sekolah yang seru dan asyik",
            tempoBpm = 120,
            lyricsLines = listOf(
                LyricLine("Teng tong teng bel istirahat berdentang nyaring,", 0, 3000),
                LyricLine("Jangan bingung mari melangkah beriring.", 3000, 3000),
                LyricLine("Perpustakaan sejuk nyaman nan tenang,", 6000, 3200),
                LyricLine("Banyak buku seru yang siap kita jelang!", 9200, 3600),
                LyricLine("Ayo ke perpustakaan sahabatku,", 12800, 3200),
                LyricLine("Buka halaman, reguk luasnya ilmu!", 16000, 3800)
            ),
            melodyNotes = listOf(
                Pair(E4, 300), Pair(G4, 300), Pair(C5, 400), Pair(E5, 400),
                Pair(D5, 300), Pair(C5, 300), Pair(G4, 500), Pair(A4, 400),
                Pair(C5, 400), Pair(D5, 400), Pair(C5, 600)
            )
        ),
        LiteracySong(
            id = 4,
            title = "Semangat Belajar Tiada Henti",
            themeDescription = "Pantang menyerah dalam belajar memahami bacaan dan teks pengetahuan",
            tempoBpm = 115,
            lyricsLines = listOf(
                LyricLine("Walau teksnya panjang kubaca perlahan,", 0, 3200),
                LyricLine("Kutemukan ide pokok dalam barisan.", 3200, 3200),
                LyricLine("Kata baru kupahami artinya,", 6400, 3000),
                LyricLine("Hati gembira bertambah ilmunya!", 9400, 3600),
                LyricLine("Semangat belajar tak kenal kata lelah,", 13000, 3400),
                LyricLine("Menjadi siswa teladan di sekolah!", 16400, 4000)
            ),
            melodyNotes = listOf(
                Pair(C4, 350), Pair(E4, 350), Pair(G4, 450), Pair(A4, 350),
                Pair(G4, 350), Pair(E4, 350), Pair(C5, 600), Pair(D5, 350),
                Pair(C5, 700)
            )
        ),
        LiteracySong(
            id = 5,
            title = "Petualangan Sang Pembaca",
            themeDescription = "Melintasi dunia dongeng, sains, dan angkasa bersama kekuatan literasi",
            tempoBpm = 110,
            lyricsLines = listOf(
                LyricLine("Naik roket menembus awan gemintang,", 0, 3200),
                LyricLine("Mengarungi lautan luas membentang.", 3200, 3200),
                LyricLine("Semua kuraih hanya dengan membaca,", 6400, 3200),
                LyricLine("Dunia ajaib terbentang di depan mata!", 9600, 3600),
                LyricLine("Kita adalah para petualang sejati,", 13200, 3400),
                LyricLine("Bawa lentera ilmu di dalam hati!", 16600, 4000)
            ),
            melodyNotes = listOf(
                Pair(G4, 380), Pair(C5, 380), Pair(D5, 380), Pair(E5, 500),
                Pair(D5, 380), Pair(C5, 380), Pair(A4, 450), Pair(G4, 380),
                Pair(C5, 800)
            )
        )
    )

    private fun C6_ALT(c5: Double): Double = 1046.50
}
