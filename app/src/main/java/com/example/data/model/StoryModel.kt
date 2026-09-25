package com.example.data.model

data class ReadingStory(
    val id: Int,
    val title: String,
    val subtitle: String,
    val themeTag: String,
    val synopsis: String,
    val paragraphs: List<String>,
    val keyVocabulary: List<Pair<String, String>>, // kata to arti
    val mainIdea: String,
    val moralMessage: String,
    val estimatedMinutes: Int
)

data class QuizQuestion(
    val id: Int,
    val questionType: QuestionType,
    val prompt: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

enum class QuestionType(val label: String) {
    MULTIPLE_CHOICE("Pilihan Ganda"),
    TRUE_FALSE("Benar atau Salah"),
    MAIN_IDEA("Menentukan Ide Pokok"),
    EXPLICIT_INFO("Informasi Tersurat"),
    IMPLICIT_INFO("Informasi Tersirat"),
    CONCLUSION("Menentukan Kesimpulan"),
    VOCABULARY("Arti Kata / Kosakata"),
    STORY_MESSAGE("Pesan / Amanat"),
    EVENT_ORDER("Urutan Peristiwa"),
    MATCHING("Mencocokkan Informasi")
}
