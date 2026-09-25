package com.example.data.model

data class Trophy(
    val id: String,
    val title: String,
    val requirementDescription: String,
    val targetCompletedStories: Int,
    val iconEmoji: String,
    val rankColorHex: Long
)

data class Badge(
    val id: String,
    val name: String,
    val description: String,
    val iconEmoji: String,
    val category: String
)

object TrophiesAndBadgesData {
    val trophies = listOf(
        Trophy(
            id = "trophy_1",
            title = "Piala Pembaca Pemula",
            requirementDescription = "Diberikan setelah berhasil menyelesaikan 1 bacaan teks panjang dan tantangan soalnya.",
            targetCompletedStories = 1,
            iconEmoji = "🥉",
            rankColorHex = 0xFFCD7F32
        ),
        Trophy(
            id = "trophy_2",
            title = "Piala Penjelajah Buku",
            requirementDescription = "Diberikan setelah berhasil menyelesaikan 3 bacaan teks panjang.",
            targetCompletedStories = 3,
            iconEmoji = "🥈",
            rankColorHex = 0xFFC0C0C0
        ),
        Trophy(
            id = "trophy_3",
            title = "Piala Sahabat Buku",
            requirementDescription = "Diberikan setelah berhasil menyelesaikan 5 bacaan teks panjang.",
            targetCompletedStories = 5,
            iconEmoji = "🥇",
            rankColorHex = 0xFFFFD700
        ),
        Trophy(
            id = "trophy_4",
            title = "Piala Pembaca Hebat",
            requirementDescription = "Diberikan setelah berhasil menyelesaikan 8 bacaan teks panjang.",
            targetCompletedStories = 8,
            iconEmoji = "🏆",
            rankColorHex = 0xFFFF9800
        ),
        Trophy(
            id = "trophy_5",
            title = "Piala Master Literasi",
            requirementDescription = "Gelar kehormatan tertinggi setelah menuntaskan seluruh 10 bacaan teks panjang!",
            targetCompletedStories = 10,
            iconEmoji = "👑",
            rankColorHex = 0xFF9C27B0
        )
    )

    val badges = listOf(
        Badge(
            id = "badge_rajin",
            name = "Rajin Membaca",
            description = "Membaca teks secara tekun dan konsisten setiap sesi belajar.",
            iconEmoji = "📖",
            category = "Kebiasaan"
        ),
        Badge(
            id = "badge_ide_pokok",
            name = "Jago Ide Pokok",
            description = "Ahli menemukan kalimat utama dan gagasan pokok paragraf dengan tepat.",
            iconEmoji = "💡",
            category = "Kognitif"
        ),
        Badge(
            id = "badge_detektif",
            name = "Detektif Informasi",
            description = "Mampu mengendus informasi tersurat dan tersirat dalam cerita secara cermat.",
            iconEmoji = "🔍",
            category = "Investigasi"
        ),
        Badge(
            id = "badge_kosakata",
            name = "Ahli Kosakata",
            description = "Menguasai perbendaharaan kata baru dan istilah ilmiah dalam teks.",
            iconEmoji = "📚",
            category = "Leksikal"
        ),
        Badge(
            id = "badge_penakluk",
            name = "Penakluk Bacaan Panjang",
            description = "Fokus dan berdaya tahan tinggi memahami bacaan bertaraf kelas 6 SD.",
            iconEmoji = "🛡️",
            category = "Daya Tahan"
        ),
        Badge(
            id = "badge_juara_game",
            name = "Juara Game Literasi",
            description = "Meraih bintang 3 dan piala pada petualangan games literasi.",
            iconEmoji = "🎮",
            category = "Permainan"
        )
    )
}
