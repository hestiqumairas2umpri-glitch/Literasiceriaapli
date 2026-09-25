package com.example.data.model

data class LearningObjective(
    val id: Int,
    val title: String,
    val description: String,
    val iconType: ObjectiveIconType,
    val targetCompetency: String,
    val category: String
)

enum class ObjectiveIconType {
    BOOK,       // Ikon buku
    IDEA_LAMP,  // Lampu ide
    MAGNIFIER,  // Kaca pembesar
    PENCIL,     // Pensil
    STAR,       // Bintang
    TROPHY      // Piala
}

object LearningObjectivesData {
    val objectives = listOf(
        LearningObjective(
            id = 1,
            title = "Membaca & Memahami Teks Panjang",
            description = "Membaca teks cerita dan bacaan informatif panjang dengan fokus, runtut, dan konsentrasi tinggi.",
            iconType = ObjectiveIconType.BOOK,
            targetCompetency = "Kelancaran & Pemahaman Teks",
            category = "Membaca Intensif"
        ),
        LearningObjective(
            id = 2,
            title = "Menemukan Informasi Penting",
            description = "Menandai dan mencatat poin-poin utama, fakta menarik, serta data pendukung dalam bacaan.",
            iconType = ObjectiveIconType.MAGNIFIER,
            targetCompetency = "Pencarian Fakta & Data",
            category = "Analisis Informasi"
        ),
        LearningObjective(
            id = 3,
            title = "Menentukan Ide Pokok & Gagasan Pendukung",
            description = "Mengidentifikasi kalimat utama pada tiap paragraf serta membedakannya dari kalimat penjelas.",
            iconType = ObjectiveIconType.IDEA_LAMP,
            targetCompetency = "Struktur Paragraf",
            category = "Pemikiran Kritis"
        ),
        LearningObjective(
            id = 4,
            title = "Menjawab Pertanyaan Berdasarkan Teks",
            description = "Mampu merespons pertanyaan adiksimba (Apa, Di mana, Kapan, Siapa, Mengapa, Bagaimana) secara tepat.",
            iconType = ObjectiveIconType.PENCIL,
            targetCompetency = "Respons Komprehensif",
            category = "Evaluasi Teks"
        ),
        LearningObjective(
            id = 5,
            title = "Menemukan Informasi Tersurat & Tersirat",
            description = "Membedakan informasi yang tertulis langsung (tersurat) dan pesan tersembunyi yang perlu disimpulkan (tersirat).",
            iconType = ObjectiveIconType.MAGNIFIER,
            targetCompetency = "Interpretasi Mendalam",
            category = "Inferensi"
        ),
        LearningObjective(
            id = 6,
            title = "Menyimpulkan Isi Bacaan",
            description = "Merangkum inti sari bacaan menggunakan kalimat sendiri secara padat, jelas, dan bermakna.",
            iconType = ObjectiveIconType.STAR,
            targetCompetency = "Sintesis & Parafrasa",
            category = "Keterampilan Menulis"
        ),
        LearningObjective(
            id = 7,
            title = "Menganalisis Tokoh, Alur, & Pesan",
            description = "Mengidentifikasi watak tokoh protagonis-antagonis, latar tempat/waktu, tahapan alur, dan amanat moral cerita.",
            iconType = ObjectiveIconType.BOOK,
            targetCompetency = "Unsur Intrinsik Cerita",
            category = "Apresiasi Sastra"
        ),
        LearningObjective(
            id = 8,
            title = "Memahami Arti Kosakata Berdasarkan Konteks",
            description = "Menebak arti kata baru, istilah sains, sinonim, antonim, dan makna kiasan dalam kalimat.",
            iconType = ObjectiveIconType.IDEA_LAMP,
            targetCompetency = "Penguasaan Leksikal",
            category = "Perbendaharaan Kata"
        ),
        LearningObjective(
            id = 9,
            title = "Membandingkan Informasi Berbagai Bagian",
            description = "Menghubungkan fakta antarparagraf dan membandingkan sudut pandang untuk menarik kesimpulan utuh.",
            iconType = ObjectiveIconType.PENCIL,
            targetCompetency = "Korelasi Data",
            category = "Logika Literasi"
        ),
        LearningObjective(
            id = 10,
            title = "Meningkatkan Minat & Kebiasaan Membaca",
            description = "Menjadikan aktivitas membaca sebagai kegemaran harian yang menyenangkan, inspiratif, dan penuh petualangan.",
            iconType = ObjectiveIconType.TROPHY,
            targetCompetency = "Karakter Gemar Membaca",
            category = "Budaya Literasi"
        )
    )
}
