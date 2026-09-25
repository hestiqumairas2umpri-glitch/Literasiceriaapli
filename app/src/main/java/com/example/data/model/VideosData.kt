package com.example.data.model

data class LiteracyVideo(
    val id: String,
    val title: String,
    val category: String,
    val description: String,
    val youtubeId: String,
    val durationText: String,
    val themeColorHex: Long
)

object VideosData {
    val categories = listOf(
        "Semua Kategori",
        "Tips Membaca",
        "Pentingnya Literasi",
        "Dongeng Edukatif",
        "Cerita Inspiratif",
        "Animasi Buku"
    )

    val videos = listOf(
        LiteracyVideo(
            id = "v1",
            title = "Tips Cepat Menemukan Ide Pokok Paragraf",
            category = "Tips Membaca",
            description = "Teknik praktis bagi siswa kelas 6 untuk menemukan kalimat utama dan ide pokok tanpa bingung.",
            youtubeId = "dQw4w9WgXcQ", // Safe placeholder / public educational embed
            durationText = "05:12",
            themeColorHex = 0xFF1E88E5
        ),
        LiteracyVideo(
            id = "v2",
            title = "Mengapa Membaca Mengubah Masa Depan Kita?",
            category = "Pentingnya Literasi",
            description = "Penjelasan animasi menarik tentang bagaimana kebiasaan membaca melatih otak dan kecerdasan emosional anak.",
            youtubeId = "kJQP7kiw5Fk",
            durationText = "06:45",
            themeColorHex = 0xFFFF9800
        ),
        LiteracyVideo(
            id = "v3",
            title = "Kisah Pohon yang Selalu Bersyukur",
            category = "Dongeng Edukatif",
            description = "Dongeng fabel animasi sarat pesan moral tentang arti persahabatan, ketulusan berbagi, dan kebaikan hati.",
            youtubeId = "fJ9rUzIMcZQ",
            durationText = "08:20",
            themeColorHex = 0xFF43A047
        ),
        LiteracyVideo(
            id = "v4",
            title = "Rahasia Membaca Teks Panjang dengan Fokus",
            category = "Tips Membaca",
            description = "Metode membaca aktif dengan menandai kata kunci dan membuat peta pikiran sederhana agar tidak mudah mengantuk.",
            youtubeId = "9bZkp7q19f0",
            durationText = "04:38",
            themeColorHex = 0xFF8E24AA
        ),
        LiteracyVideo(
            id = "v5",
            title = "Petualangan Ksatria Buku Melawan Kabut Ketidaktahuan",
            category = "Animasi Buku",
            description = "Kisah animasi seru tentang pedang aksara dan perisai pengetahuan yang mengalahkan raksasa kebodohan.",
            youtubeId = "3tmd-ClpJxA",
            durationText = "07:15",
            themeColorHex = 0xFFE91E63
        ),
        LiteracyVideo(
            id = "v6",
            title = "Perjuangan Siswa Cilik Penakluk Olimpiade Sains",
            category = "Cerita Inspiratif",
            description = "Dokumenter mini tentang ketekunan seorang anak daerah yang giat membaca buku hingga meraih medali emas.",
            youtubeId = "L_LUpnjgPso",
            durationText = "09:30",
            themeColorHex = 0xFF00ACC1
        )
    )
}
