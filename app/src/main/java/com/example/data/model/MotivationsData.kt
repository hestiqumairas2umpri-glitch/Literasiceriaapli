package com.example.data.model

data class ReadingMotivation(
    val id: Int,
    val quote: String,
    val authorOrSource: String,
    val tip: String,
    val iconEmoji: String
)

object MotivationsData {
    val quotes = listOf(
        ReadingMotivation(
            id = 1,
            quote = "Setiap halaman yang kamu baca membuat pengetahuanmu bertambah dan wawasanmu semakin meluas.",
            authorOrSource = "Mutiara Literasi",
            tip = "Luangkan waktu 15 menit setiap hari untuk membaca buku kesukaanmu.",
            iconEmoji = "📖"
        ),
        ReadingMotivation(
            id = 2,
            quote = "Tidak harus membaca banyak sekaligus. Yang paling penting adalah membaca sedikit demi sedikit namun konsisten setiap hari.",
            authorOrSource = "Kunci Kebiasaan Membaca",
            tip = "Konsistensi kecil setiap hari akan membuahkan kecerdasan luar biasa di masa depan.",
            iconEmoji = "🌱"
        ),
        ReadingMotivation(
            id = 3,
            quote = "Buku adalah jendela menuju ribuan dunia baru yang belum pernah kamu kunjungi sebelumnya.",
            authorOrSource = "Pepatah Sahabat Buku",
            tip = "Lewat buku, kamu bisa menyelam ke palung samudra terdalam hingga terbang melintasi galaksi.",
            iconEmoji = "🚀"
        ),
        ReadingMotivation(
            id = 4,
            quote = "Semakin rajin kamu membaca, semakin kaya perbendaharaan kata dan kematangan caramu berpikir.",
            authorOrSource = "Pesan Guru Teladan",
            tip = "Tandai kata-kata baru yang kamu jumpai dan cari artinya di kamus.",
            iconEmoji = "💡"
        ),
        ReadingMotivation(
            id = 5,
            quote = "Jangan pernah takut dengan bacaan yang panjang. Baca secara perlahan, pahami paragraf demi paragraf, lalu temukan jawabannya.",
            authorOrSource = "Jurus Penakluk Teks",
            tip = "Tarik napas dalam, cermati kalimat utama di setiap awal paragraf.",
            iconEmoji = "🦁"
        ),
        ReadingMotivation(
            id = 6,
            quote = "Membaca adalah petualangan terhebat yang bisa kamu nikmati tanpa harus beranjak dari tempat dudukmu.",
            authorOrSource = "Imajinasi Literasi",
            tip = "Biarkan daya khayalmu menari bersama alur cerita yang kamu baca.",
            iconEmoji = "✨"
        ),
        ReadingMotivation(
            id = 7,
            quote = "Orang yang gemar membaca tidak akan pernah merasa kesepian, karena ia selalu ditemani oleh sahabat terbaik bernama ilmu.",
            authorOrSource = "Kebajikan Membaca",
            tip = "Buku yang baik adalah teman sejati yang setia menuntun hidupmu.",
            iconEmoji = "🌟"
        )
    )
}
