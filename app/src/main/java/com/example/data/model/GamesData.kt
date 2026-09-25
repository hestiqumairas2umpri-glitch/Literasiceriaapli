package com.example.data.model

data class LiteracyGameInfo(
    val id: Int,
    val title: String,
    val subtitle: String,
    val iconEmoji: String,
    val gameType: LiteracyGameType,
    val description: String,
    val themeColorHex: Long,
    val badgeName: String
)

enum class LiteracyGameType {
    SNAKES_LADDERS,   // Game 1: Ular Tangga
    AIRPLANE_FLY,     // Game 2: Pesawat Melaju
    ROCKET_PLANET,    // Game 3: Roket Menuju Planet Kata
    TREASURE_HUNT,    // Game 4: Berburu Harta Karun Buku
    MAIN_IDEA_DETECTIVE, // Game 5: Detektif Ide Pokok
    WORD_TRAIN,       // Game 6: Kereta Kata
    MAZE_RUNNER,      // Game 7: Labirin Literasi
    WORD_BALLOONS,    // Game 8: Balon Kata
    KNOWLEDGE_SHIP,   // Game 9: Kapal Pengetahuan
    GRAND_FESTIVAL    // Game 10: Festival Juara Literasi
}

data class GameQuestion(
    val prompt: String,
    val options: List<String>,
    val correctIndex: Int,
    val hint: String = ""
)

object GamesData {
    val gamesList = listOf(
        LiteracyGameInfo(
            id = 1,
            title = "Ular Tangga Literasi",
            subtitle = "Lempar Dadu & Taklukkan Kotak Tantangan",
            iconEmoji = "🎲",
            gameType = LiteracyGameType.SNAKES_LADDERS,
            description = "Lempar dadu, maju di papan petualangan, dan jawab teka-teki literasi pada kotak istimewa untuk meraih tangga emas!",
            themeColorHex = 0xFF43A047,
            badgeName = "Penakluk Papan Dadu"
        ),
        LiteracyGameInfo(
            id = 2,
            title = "Pesawat Melaju",
            subtitle = "Terbang Menembus Awan Pilihan Kata",
            iconEmoji = "✈️",
            gameType = LiteracyGameType.AIRPLANE_FLY,
            description = "Kendalikan kemudi pesawat melintasi langit. Pilih awan bermuatan jawaban yang benar agar pesawat melesat kencang!",
            themeColorHex = 0xFF1E88E5,
            badgeName = "Penerbang Ulung"
        ),
        LiteracyGameInfo(
            id = 3,
            title = "Roket Menuju Planet Kata",
            subtitle = "Jelajah Kosakata Antariksa",
            iconEmoji = "🚀",
            gameType = LiteracyGameType.ROCKET_PLANET,
            description = "Bantu roket mengisi bahan bakar dengan menjawab kuis kosakata, sinonim, dan antonim untuk mendarat di Planet Kata.",
            themeColorHex = 0xFF8E24AA,
            badgeName = "Kosmonot Kata"
        ),
        LiteracyGameInfo(
            id = 4,
            title = "Berburu Harta Karun Buku",
            subtitle = "Pecahkan Teka-Teki Informasi Teks",
            iconEmoji = "🗺️",
            gameType = LiteracyGameType.TREASURE_HUNT,
            description = "Gunakan peta bajak laut dan pecahkan 4 petunjuk informasi tersurat untuk membuka peti harta karun emas!",
            themeColorHex = 0xFFFF9800,
            badgeName = "Pemburu Harta Karun"
        ),
        LiteracyGameInfo(
            id = 5,
            title = "Detektif Ide Pokok",
            subtitle = "Lacak Kalimat Utama Paragraf",
            iconEmoji = "🔍",
            gameType = LiteracyGameType.MAIN_IDEA_DETECTIVE,
            description = "Nyalakan kaca pembesar detektifmu dan temukan jejak ide pokok di antara barisan kalimat paragraf.",
            themeColorHex = 0xFF3F51B5,
            badgeName = "Detektif Ulung"
        ),
        LiteracyGameInfo(
            id = 6,
            title = "Kereta Kata",
            subtitle = "Rangkai Gerbong Menjadi Kalimat Padu",
            iconEmoji = "🚂",
            gameType = LiteracyGameType.WORD_TRAIN,
            description = "Susun gerbong-gerbong kata yang terpisah menjadi satu kesatuan kalimat efektif yang utuh dan runtut.",
            themeColorHex = 0xFFE91E63,
            badgeName = "Masinis Kalimat"
        ),
        LiteracyGameInfo(
            id = 7,
            title = "Labirin Literasi",
            subtitle = "Temukan Jalan Keluar di Persimpangan",
            iconEmoji = "🌀",
            gameType = LiteracyGameType.MAZE_RUNNER,
            description = "Setiap pintu persimpangan labirin dijaga oleh teka-teki logika membaca. Pilih jawaban tepat untuk keluar!",
            themeColorHex = 0xFF00897B,
            badgeName = "Penjelajah Labirin"
        ),
        LiteracyGameInfo(
            id = 8,
            title = "Balon Kata",
            subtitle = "Ketuk Balon Sesuai Definisi Makna",
            iconEmoji = "🎈",
            gameType = LiteracyGameType.WORD_BALLOONS,
            description = "Balon-balon warna-warni melayang di udara. Ketuk balon yang memuat arti istilah yang dicari sebelum terbang jauh!",
            themeColorHex = 0xFFFF5722,
            badgeName = "Penembak Balon"
        ),
        LiteracyGameInfo(
            id = 9,
            title = "Kapal Pengetahuan",
            subtitle = "Arungi Pulau-Pulau Misteri Bahari",
            iconEmoji = "⛵",
            gameType = LiteracyGameType.KNOWLEDGE_SHIP,
            description = "Kendalikan kemudi kapal melintasi Pulau Bahari, Rimba, dan Sains dengan menyelesaikan tantangan literasi tiap dermaga.",
            themeColorHex = 0xFF0288D1,
            badgeName = "Nakhoda Pengetahuan"
        ),
        LiteracyGameInfo(
            id = 10,
            title = "Festival Juara Literasi",
            subtitle = "Tantangan Akbar Grand Championship",
            iconEmoji = "🏆",
            gameType = LiteracyGameType.GRAND_FESTIVAL,
            description = "Uji gabungan seluruh kemampuan: ide pokok, informasi tersirat, simpulan, dan kosakata dalam arena festival akbar!",
            themeColorHex = 0xFFFFB300,
            badgeName = "Juara Akbar Literasi"
        )
    )

    // Questions for the games
    val snakesLaddersQuestions = listOf(
        GameQuestion("Apa nama bagian pembuka dalam suatu teks cerita?", listOf("Orientasi", "Resolusi", "Komplikasi", "Koda"), 0, "Mengenalkan tokoh dan latar"),
        GameQuestion("Sinonim dari kata 'antusias' adalah ...", listOf("Bersemangat", "Lesu", "Kecewa", "Takut"), 0, "Penuh energi positif"),
        GameQuestion("Kalimat yang memuat gagasan utama disebut ...", listOf("Kalimat utama", "Kalimat penjelas", "Kalimat tanya", "Kalimat seru"), 0, "Menjadi inti bahasan"),
        GameQuestion("Informasi yang tertulis jelas dalam teks disebut informasi ...", listOf("Tersurat", "Tersirat", "Palsu", "Rahasia"), 0, "Bisa dibaca langsung"),
        GameQuestion("Antonim dari kata 'subur' adalah ...", listOf("Gersang / Tandus", "Makmur", "Rimbun", "Hijau"), 0, "Tanah yang sulit ditanami"),
        GameQuestion("Pesan kebaikan dalam cerita disebut ...", listOf("Amanat", "Latar", "Alur", "Tema"), 0, "Nasihat dari pengarang")
    )

    val airplaneQuestions = listOf(
        GameQuestion("Pilih awan yang memuat kata baku:", listOf("Praktik", "Praktek", "Praktekkan", "Praktekan"), 0),
        GameQuestion("Pilih awan makna kata 'konservasi':", listOf("Perlindungan alam", "Penebangan pohon", "Pembakaran sampah", "Penggalian tambang"), 0),
        GameQuestion("Pilih awan berisi majas personifikasi:", listOf("Ombak berkejar-kejaran di pantai", "Budi berlari cepat seperti kilat", "Ayah bekerja membanting tulang", "Ia sekaya raja"), 0),
        GameQuestion("Pilih awan yang memuat kalimat efektif:", listOf("Semua siswa hadir di aula.", "Semua para siswa-siswa hadir di aula.", "Siswa-siswa sekalian semuanya hadir.", "Para anak-anak siswa hadir."), 0)
    )

    val rocketQuestions = listOf(
        GameQuestion("Arti kata 'atmosfer' adalah ...", listOf("Lapisan gas yang menyelimuti planet", "Tanah bebatuan di bulan", "Kawah gunung berapi", "Cahaya bintang jatuh"), 0),
        GameQuestion("Sinonim kata 'inovasi' adalah ...", listOf("Penemuan pembaruan baru", "Kebiasaan lama", "Peniruan karya", "Penghapusan tugas"), 0),
        GameQuestion("Lawan kata dari 'ekspansi' adalah ...", listOf("Penciutan / Penyusutan", "Perluasan", "Penambahan", "Peningkatan"), 0),
        GameQuestion("Kata 'orbiter' berkaitan erat dengan bidang ...", listOf("Astronomi & Antariksa", "Pertanian padi", "Peternakan sapi", "Perikanan air tawar"), 0)
    )

    val treasureClues = listOf(
        GameQuestion("Petunjuk 1: Temukan kata yang bermakna 'wadah penampung air':", listOf("Tandon", "Kapsul", "Ajir", "Sentra"), 0),
        GameQuestion("Petunjuk 2: Temukan kata yang bermakna 'kejujuran budi pekerti':", listOf("Integritas", "Aerodinamika", "Abrasi", "Supel"), 0),
        GameQuestion("Petunjuk 3: Temukan kata yang bermakna 'botol plastik padat daur ulang':", listOf("Ecobrick", "Ensiklopedia", "Komposter", "Metafora"), 0),
        GameQuestion("Petunjuk 4: Kunci emas! Apakah sayap bagi jiwa untuk merdeka melintasi zaman?", listOf("Buku dan Membaca", "Pedang besi", "Emas permata", "Peti timah"), 0)
    )

    val wordTrainSentences = listOf(
        listOf("Membaca", "buku", "setiap", "hari", "menambah", "wawasan", "kita"),
        listOf("Hutan", "lindung", "menyimpan", "cadangan", "air", "bersih", "alami"),
        listOf("Persahabatan", "sejati", "saling", "menghargai", "perbedaan", "budaya", "Nusantara")
    )
}
