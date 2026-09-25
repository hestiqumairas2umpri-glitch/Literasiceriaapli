package com.example.data.model

object QuestionsDataPart1 {
    val questionsMap = mapOf(
        1 to listOf(
            QuizQuestion(
                id = 101,
                questionType = QuestionType.MAIN_IDEA,
                prompt = "Apakah ide pokok yang terkandung dalam paragraf pertama?",
                options = listOf(
                    "Reno dan Dania merapikan kardus buku di sudut perpustakaan tua sekolah",
                    "Sekolah Harapan Bangsa membeli ensiklopedia baru",
                    "Jendela perpustakaan rusak terkena sinar matahari sore",
                    "Kardus buku sumbangan hilang diambil seseorang"
                ),
                correctIndex = 0,
                explanation = "Paragraf 1 menceritakan suasana sore ketika Reno dan Dania merapikan kardus-kardus buku sumbangan di sudut belakang perpustakaan."
            ),
            QuizQuestion(
                id = 102,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Benda apa yang ditemukan Dania di dalam rongga dinding tersembunyi?",
                options = listOf(
                    "Sebuah celengan koin emas kuno",
                    "Sebuah kotak timah berukir lambang pohon beringin",
                    "Sebuah kunci besi berkarat",
                    "Sebuah jam dinding antik Belanda"
                ),
                correctIndex = 1,
                explanation = "Tersurat jelas di paragraf 2: 'Dania mengintip ke dalam rongga sempit itu dan menemukan sebuah kotak timah berukir lambang pohon beringin'."
            ),
            QuizQuestion(
                id = 103,
                questionType = QuestionType.IMPLICIT_INFO,
                prompt = "Mengapa buku harian tersebut diberi catatan tanggal 28 Oktober 1928?",
                options = listOf(
                    "Karena bertepatan dengan peristiwa bersejarah Sumpah Pemuda",
                    "Karena perpustakaan sekolah didirikan pada tanggal tersebut",
                    "Karena hari itu adalah hari libur nasional",
                    "Karena tanggal itu merupakan hari lahir Reno dan Dania"
                ),
                correctIndex = 0,
                explanation = "Secara tersirat, tanggal 28 Oktober 1928 merujuk pada ikrar Sumpah Pemuda saat bangsa Indonesia bertekad bersatu."
            ),
            QuizQuestion(
                id = 104,
                questionType = QuestionType.VOCABULARY,
                prompt = "Berdasarkan teks, apakah makna kata 'ensiklopedia'?",
                options = listOf(
                    "Koleksi cerita bergambar anak-anak",
                    "Buku yang memuat uraian lengkap tentang berbagai cabang ilmu",
                    "Daftar catatan harian pribadi seseorang",
                    "Buku petunjuk memasak makanan tradisional"
                ),
                correctIndex = 1,
                explanation = "Ensiklopedia adalah buku referensi komprehensif yang merangkum berbagai bidang ilmu pengetahuan."
            ),
            QuizQuestion(
                id = 105,
                questionType = QuestionType.TRUE_FALSE,
                prompt = "Benar atau Salah: Reno dan Dania menyembunyikan buku harian kuno itu untuk kepentingan mereka sendiri di rumah.",
                options = listOf(
                    "Benar",
                    "Salah"
                ),
                correctIndex = 1,
                explanation = "Salah, karena di paragraf terakhir tersurat bahwa Reno dan Dania menyerahkan buku tersebut kepada kepala pustakawan, Ibu Kartini."
            ),
            QuizQuestion(
                id = 106,
                questionType = QuestionType.CONCLUSION,
                prompt = "Apa kesimpulan yang tepat dari kisah penemuan buku tua tersebut?",
                options = listOf(
                    "Perpustakaan tua harus segera dibongkar karena berbahaya",
                    "Buku catatan masa lalu memberi inspirasi bahwa membaca adalah kunci kemerdekaan berpikir",
                    "Merapikan buku perpustakaan adalah pekerjaan yang sia-sia",
                    "Siswa tidak boleh membuka lemari kayu di perpustakaan"
                ),
                correctIndex = 1,
                explanation = "Buku harian tersebut menegaskan bahwa membaca adalah sayap bagi jiwa untuk merdeka dan berpengetahuan luas."
            ),
            QuizQuestion(
                id = 107,
                questionType = QuestionType.EVENT_ORDER,
                prompt = "Urutan peristiwa yang benar dalam cerita di atas adalah ...",
                options = listOf(
                    "Lantai berderit -> Menemukan kotak timah -> Membaca buku kuno -> Menyerahkan ke Ibu Kartini",
                    "Menyerahkan ke Ibu Kartini -> Menemukan kotak -> Lantai berderit -> Membaca buku",
                    "Membaca buku -> Menyerahkan ke Ibu Kartini -> Lantai berderit -> Menggeser lemari",
                    "Menemukan kotak -> Menyerahkan ke Ibu Kartini -> Lantai berderit -> Membaca buku"
                ),
                correctIndex = 0,
                explanation = "Alur dimulai dari lantai berderit saat merapikan kardus, menggeser lemari dan menemukan kotak, membaca buku, lalu menyerahkannya ke Ibu Kartini."
            ),
            QuizQuestion(
                id = 108,
                questionType = QuestionType.STORY_MESSAGE,
                prompt = "Amanat yang dapat dipetik dari sikap Reno dan Dania adalah ...",
                options = listOf(
                    "Jujur dan bertanggung jawab saat menemukan barang berharga milik umum",
                    "Menjual barang antik temuan demi mendapatkan uang saku",
                    "Menghindari kegiatan piket merapikan perpustakaan",
                    "Menyimpan rahasia sendirian tanpa memberi tahu orang dewasa"
                ),
                correctIndex = 0,
                explanation = "Reno dan Dania jujur, amanah, dan menyerahkan penemuan berharga untuk kepentingan bersama di sekolah."
            ),
            QuizQuestion(
                id = 109,
                questionType = QuestionType.MATCHING,
                prompt = "Manakah pasangan tokoh dan perannya yang paling tepat sesuai cerita?",
                options = listOf(
                    "Ibu Kartini - Kepala Pustakawan Sekolah Harapan Bangsa",
                    "Dania - Guru Fisika",
                    "Reno - Tukang kebun sekolah",
                    "Pak Darmo - Pemilik buku harian tahun 1928"
                ),
                correctIndex = 0,
                explanation = "Ibu Kartini adalah kepala pustakawan yang menerima buku harian kuno dari Reno dan Dania."
            ),
            QuizQuestion(
                id = 110,
                questionType = QuestionType.MULTIPLE_CHOICE,
                prompt = "Pepatah 'Buku adalah sayap bagi jiwa yang ingin terbang bebas...' bermakna bahwa membaca dapat ...",
                options = listOf(
                    "Membuat tubuh manusia bisa terbang seperti burung",
                    "Membuka imajinasi dan memperluas pengetahuan tanpa batas",
                    "Mempercepat waktu istirahat di sekolah",
                    "Menggantikan fungsi pesawat terbang"
                ),
                correctIndex = 1,
                explanation = "Pepatah tersebut merupakan kiasan (metafora) bahwa membaca membebaskan pikiran dan memperluas wawasan ke berbagai penjuru dunia."
            )
        ),
        2 to listOf(
            QuizQuestion(
                id = 201,
                questionType = QuestionType.MAIN_IDEA,
                prompt = "Apakah ide pokok yang disampaikan pada paragraf kedua?",
                options = listOf(
                    "Pak Darmo menjelaskan bahwa tanah hutan gembur dan lembap seperti spons cuci piring",
                    "Kicauan burung jalak suren di lereng gunung",
                    "SD Cendekia menolak kegiatan jelajah alam",
                    "Musim kemarau panjang di lereng bukit"
                ),
                correctIndex = 0,
                explanation = "Paragraf kedua berfokus pada pengamatan tanah hutan yang gembur dan lembap serta perumpamaannya seperti spons penyerap air."
            ),
            QuizQuestion(
                id = 202,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Bibit pohon apa yang ditanam oleh rombongan pramuka sebelum pulang?",
                options = listOf(
                    "Pohon kelapa dan mangga",
                    "Bibit pohon damar dan pinus",
                    "Pohon pisang dan pepaya",
                    "Bunga mawar dan melati"
                ),
                correctIndex = 1,
                explanation = "Tersurat jelas di paragraf 5: 'mereka bersama-sama menanam bibit pohon damar dan pinus sebagai bentuk nyata kepedulian'."
            ),
            QuizQuestion(
                id = 203,
                questionType = QuestionType.IMPLICIT_INFO,
                prompt = "Apa yang tersirat mengenai profesi seorang 'jagawana'?",
                options = listOf(
                    "Pekerjaan yang berdedikasi menjaga kelestarian flora dan fauna hutan",
                    "Pekerjaan mencari kayu bakar untuk dijual di pasar",
                    "Pekerjaan memburu hewan liar untuk dipelihara",
                    "Pekerjaan membangun gedung perumahan di tengah hutan"
                ),
                correctIndex = 0,
                explanation = "Jagawana seperti Pak Darmo bertugas melindungi hutan lindung dari perusakan dan memandu kelestarian alam."
            ),
            QuizQuestion(
                id = 204,
                questionType = QuestionType.VOCABULARY,
                prompt = "Apakah sinonim kata 'gembur' dalam konteks tanah yang subur?",
                options = listOf(
                    "Keras dan berbatu",
                    "Remah dan tidak padat",
                    "Kering dan tandus",
                    "Beku seperti es"
                ),
                correctIndex = 1,
                explanation = "Tanah gembur artinya tanah yang remah, berpori, dan mudah meresapkan air serta udara bagi perakaran tanaman."
            ),
            QuizQuestion(
                id = 205,
                questionType = QuestionType.CONCLUSION,
                prompt = "Kesimpulan hubungan sebab-akibat jika pohon di perbukitan ditebang liar adalah ...",
                options = listOf(
                    "Hutan menjadi semakin subur dan air semakin melimpah",
                    "Air hujan mengalir liar di permukaan, memicu banjir bandang dan longsor",
                    "Hewan-hewan hutan menjadi lebih gemuk dan jinak",
                    "Udara pegunungan akan menjadi lebih sejuk dan dingin"
                ),
                correctIndex = 1,
                explanation = "Penebangan pohon merusak resapan air, memicu erosi, longsor, banjir bandang di musim hujan, dan kekeringan di musim kemarau."
            ),
            QuizQuestion(
                id = 206,
                questionType = QuestionType.TRUE_FALSE,
                prompt = "Benar atau Salah: Hutan lebat berfungsi seperti tandon air alami yang menampung air hujan dan mengalirkannya perlahan ke sungai.",
                options = listOf(
                    "Benar",
                    "Salah"
                ),
                correctIndex = 0,
                explanation = "Benar, akar pepohonan mengikat air di dalam tanah dan melepaskannya perlahan sepanjang tahun."
            ),
            QuizQuestion(
                id = 207,
                questionType = QuestionType.STORY_MESSAGE,
                prompt = "Pesan moral yang paling utama dari bacaan 'Rahasia Hutan yang Menyimpan Air' adalah ...",
                options = listOf(
                    "Menjaga pohon dan hutan adalah menjaga sumber kehidupan dan masa depan air bersih",
                    "Sebaiknya kita menghabiskan air bersih sebanyak-banyaknya selagi ada",
                    "Jangan pernah pergi ke bukit karena banyak nyamuk dan lumpur",
                    "Menebang pohon adalah cara tercepat untuk mendapatkan uang"
                ),
                correctIndex = 0,
                explanation = "Kelestarian hutan sangat menentukan keberlangsungan cadangan air dan perlindungan lingkungan bagi manusia."
            ),
            QuizQuestion(
                id = 208,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Siapakah nama pembimbing jelajah alam rombongan pramuka tersebut?",
                options = listOf(
                    "Ibu Dewi",
                    "Pak Darmo",
                    "Kakek Wiryo",
                    "Bagas"
                ),
                correctIndex = 1,
                explanation = "Pak Darmo adalah jagawana senior yang membimbing jelajah alam anak-anak pramuka."
            ),
            QuizQuestion(
                id = 209,
                questionType = QuestionType.MULTIPLE_CHOICE,
                prompt = "Mengapa tanah hutan terasa lembap seperti spons basah?",
                options = listOf(
                    "Karena ada pipa air bocor di bawah tanah",
                    "Karena tertutup seresah daun kering dan lumut tebal yang menahan kelembapan air",
                    "Karena disiram setiap pagi oleh petugas jagawana",
                    "Karena air laut merembes ke puncak gunung"
                ),
                correctIndex = 1,
                explanation = "Seresah daun, lumut, dan perakaran lebat menjaga kelembapan tanah tetap optimal dan menahan penguapan langsung."
            ),
            QuizQuestion(
                id = 210,
                questionType = QuestionType.MATCHING,
                prompt = "Pasangan istilah dan fungsi yang benar berdasarkan bacaan adalah ...",
                options = listOf(
                    "Akar pohon - Menyerap dan mengikat air hujan di bawah tanah",
                    "Batang pohon - Menghasilkan racun bagi tanaman lain",
                    "Daun kering - Menyebabkan banjir seketika",
                    "Jagawana - Penjual kayu bakar hutan"
                ),
                correctIndex = 0,
                explanation = "Akar pohon berfungsi vital menyerap air hujan dan mengikat struktur partikel tanah agar tidak longsor."
            )
        ),
        3 to listOf(
            QuizQuestion(
                id = 301,
                questionType = QuestionType.MAIN_IDEA,
                prompt = "Ide pokok paragraf ketiga adalah ...",
                options = listOf(
                    "Made menceritakan filosofi Tri Hita Karana dan membawakan lukisan Tari Barong",
                    "Fajar membelikan Made sepatu baru di pasar",
                    "SD Nusantara membatalkan festival budaya",
                    "Made ingin segera kembali ke Gianyar Bali"
                ),
                correctIndex = 0,
                explanation = "Paragraf 3 memuat kisah Made yang mengenalkan lukisan Tari Barong dan nilai harmoni Tri Hita Karana."
            ),
            QuizQuestion(
                id = 302,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Dari daerah manakah asal daerah I Made Arimbawa sebelum pindah?",
                options = listOf(
                    "Bandung, Jawa Barat",
                    "Gianyar, Pulau Bali",
                    "Padang, Sumatera Barat",
                    "Makassar, Sulawesi Selatan"
                ),
                correctIndex = 1,
                explanation = "Tersurat di paragraf 1: 'Namanya I Made Arimbawa, siswa pindahan dari Gianyar, Pulau Bali'."
            ),
            QuizQuestion(
                id = 303,
                questionType = QuestionType.IMPLICIT_INFO,
                prompt = "Sikap Fajar saat pertama kali bertemu Made mencerminkan nilai ...",
                options = listOf(
                    "Individualisme dan acuh tak acuh",
                    "Keramahan, toleransi, dan keterbukaan menerima teman baru",
                    "Kecurigaan terhadap orang luar daerah",
                    "Keserakahan dalam mencari teman"
                ),
                correctIndex = 1,
                explanation = "Fajar dengan tulus mendampingi Made, mengajaknya duduk di sampingnya, dan mengenalkan budaya setempat."
            ),
            QuizQuestion(
                id = 304,
                questionType = QuestionType.VOCABULARY,
                prompt = "Kata 'supel' dalam kalimat 'Fajar, ketua kelas yang supel...' memiliki arti ...",
                options = listOf(
                    "Pandai bergaul dan luwes menyesuaikan diri",
                    "Mudah marah jika pendapatnya disangkal",
                    "Suka menyendiri di sudut ruangan",
                    "Keras kepala dan tidak mau mendengar nasihat"
                ),
                correctIndex = 0,
                explanation = "Supel berarti pandai menyesuaikan diri dan ramah bergaul dengan siapa saja."
            ),
            QuizQuestion(
                id = 305,
                questionType = QuestionType.CONCLUSION,
                prompt = "Apakah kesimpulan yang dapat diambil dari penampilan kolaborasi Fajar dan Made di festival?",
                options = listOf(
                    "Alat musik daerah lain tidak boleh dipadukan sama sekali",
                    "Perbedaan budaya justru menciptakan harmoni yang indah jika dipadukan dengan saling menghargai",
                    "Festival sekolah sebaiknya hanya menampilkan tarian modern saja",
                    "Made harus berhenti memainkan gamelan Bali"
                ),
                correctIndex = 1,
                explanation = "Perpaduan suling Sunda dan gamelan Bali membuktikan bahwa keberagaman budaya Nusantara saling memperindah."
            ),
            QuizQuestion(
                id = 306,
                questionType = QuestionType.TRUE_FALSE,
                prompt = "Benar atau Salah: Filosofi Tri Hita Karana mengajarkan tentang menjaga hubungan selaras dengan sesama manusia, alam sekitar, dan Tuhan Yang Maha Esa.",
                options = listOf(
                    "Benar",
                    "Salah"
                ),
                correctIndex = 0,
                explanation = "Benar, Tri Hita Karana adalah tiga penyebab keharmonisan hidup dalam budaya Bali."
            ),
            QuizQuestion(
                id = 307,
                questionType = QuestionType.MATCHING,
                prompt = "Kudapan tradisional khas Sunda yang diperkenalkan Fajar kepada Made adalah ...",
                options = listOf(
                    "Awug dan surabi",
                    "Bika ambon dan rendang",
                    "Kerak telor dan dodol Garut",
                    "Gudeg dan bakpia"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 2: 'jajanan pasar khas Sunda seperti awug dan surabi'."
            ),
            QuizQuestion(
                id = 308,
                questionType = QuestionType.STORY_MESSAGE,
                prompt = "Semboyan bangsa Indonesia yang sangat sesuai dengan pesan cerita ini adalah ...",
                options = listOf(
                    "Tut Wuri Handayani",
                    "Bhinneka Tunggal Ika",
                    "Ing Ngarso Sung Tulodo",
                    "Mitreka Satata"
                ),
                correctIndex = 1,
                explanation = "Bhinneka Tunggal Ika bermakna berbeda-beda tetapi tetap satu jua, selaras dengan persahabatan Made dan Fajar."
            ),
            QuizQuestion(
                id = 309,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Alat musik apa yang dimainkan Fajar saat pertunjukan kolaborasi seni di sekolah?",
                options = listOf(
                    "Suling bambu Sunda",
                    "Gitar listrik",
                    "Drum set",
                    "Sasando"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 4: 'memadukan alunan suling bambu Sunda dengan denting canang gamelan Bali'."
            ),
            QuizQuestion(
                id = 310,
                questionType = QuestionType.MULTIPLE_CHOICE,
                prompt = "Bagaimana respon penonton di aula saat Fajar dan Made menyelesaikan penampilannya?",
                options = listOf(
                    "Semua penonton meninggalkan ruangan dengan kecewa",
                    "Seluruh ruangan bertepuk tangan meriah dan merasa kagum",
                    "Guru meminta mereka mengulang dari awal karena salah lirik",
                    "Tidak ada penonton yang memperhatikan pertunjukan"
                ),
                correctIndex = 1,
                explanation = "Penampilan kolaborasi kedua sahabat disambut dengan tepuk tangan meriah dari seluruh warga sekolah."
            )
        ),
        4 to listOf(
            QuizQuestion(
                id = 401,
                questionType = QuestionType.MAIN_IDEA,
                prompt = "Gagasan utama paragraf kedua pada bacaan 'Perjalanan Sampah Menjadi Karya' adalah ...",
                options = listOf(
                    "Pembentukan Agen Daur Ulang Mandiri untuk memilah tiga kategori sampah di sekolah",
                    "Kantin sekolah menjual makanan bergizi tinggi",
                    "Ibu Dewi pindah mengajar ke sekolah lain",
                    "Zahra membeli tong sampah dari toko peralatan"
                ),
                correctIndex = 0,
                explanation = "Paragraf 2 membahas inisiatif Zahra membentuk kelompok Agen Daur Ulang Mandiri untuk memilah jenis sampah."
            ),
            QuizQuestion(
                id = 402,
                questionType = QuestionType.VOCABULARY,
                prompt = "Apakah yang dimaksud dengan istilah 'ecobrick' dalam bacaan?",
                options = listOf(
                    "Batu bata merah yang dibakar menggunakan arang kayu",
                    "Botol plastik bekas yang diisi padat dengan potongan limbah plastik",
                    "Kertas bekas yang dihancurkan menjadi bubur kertas",
                    "Kaca jendela yang dibuat dari pasir kuarsa"
                ),
                correctIndex = 1,
                explanation = "Ecobrick adalah botol plastik yang dipadatkan dengan plastik bekas bersih sebagai blok konstruksi ramah lingkungan."
            ),
            QuizQuestion(
                id = 403,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Karya apa yang berhasil dibuat dari ratusan ecobrick di serambi perpustakaan?",
                options = listOf(
                    "Meja bundar dan kursi pojok baca yang kokoh",
                    "Pagar lapangan sepak bola",
                    "Atap genteng ruang guru",
                    "Pot gantung bunga anggrek"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 4: 'ratusan ecobrick berhasil dirangkai menjadi meja bundar dan kursi pojok baca yang kokoh'."
            ),
            QuizQuestion(
                id = 404,
                questionType = QuestionType.CONCLUSION,
                prompt = "Kesimpulan yang benar tentang pengolahan sampah organik pada cerita tersebut adalah ...",
                options = listOf(
                    "Sampah organik dibakar agar asapnya mengusir nyamuk",
                    "Sampah organik diolah dengan komposter menjadi pupuk cair tanaman sayur",
                    "Sampah organik dimasukkan ke dalam botol plastik ecobrick",
                    "Sampah organik dibuang ke sungai belakang sekolah"
                ),
                correctIndex = 1,
                explanation = "Sampah organik sisa makanan diolah dalam drum komposter menjadi pupuk cair penyubur tanaman kebun."
            ),
            QuizQuestion(
                id = 405,
                questionType = QuestionType.TRUE_FALSE,
                prompt = "Benar atau Salah: Sampah botol plastik termasuk kategori sampah organik yang dapat terurai dalam hitungan hari di tanah.",
                options = listOf(
                    "Benar",
                    "Salah"
                ),
                correctIndex = 1,
                explanation = "Salah, botol plastik merupakan sampah anorganik yang membutuhkan waktu ratusan tahun untuk terurai secara alami."
            ),
            QuizQuestion(
                id = 406,
                questionType = QuestionType.IMPLICIT_INFO,
                prompt = "Mengapa sekolah mereka berhasil meraih predikat Juara Adiwiyata Tingkat Kota?",
                options = listOf(
                    "Karena membeli mebel mahal dari luar negeri",
                    "Karena seluruh warga sekolah berpartisipasi aktif menjaga kelestarian lingkungan dan berinovasi",
                    "Karena membayar biaya pendaftaran paling besar",
                    "Karena memiliki jumlah murid paling banyak"
                ),
                correctIndex = 1,
                explanation = "Predikat Adiwiyata diraih berkat kepedulian bersama warga sekolah dalam budaya ramah lingkungan berkelanjutan."
            ),
            QuizQuestion(
                id = 407,
                questionType = QuestionType.EVENT_ORDER,
                prompt = "Tahapan pembuatan ecobrick yang tepat adalah ...",
                options = listOf(
                    "Cuci bersih botol -> Keringkan -> Isi padat dengan potongan plastik -> Rangkai menjadi mebel",
                    "Rangkai mebel -> Isi plastik -> Cuci botol -> Jemur di terik matahari",
                    "Bakar botol -> Tuang air sabun -> Cetak menjadi batu bata",
                    "Gunting botol -> Buang plastiknya -> Lem menjadi meja"
                ),
                correctIndex = 0,
                explanation = "Botol dicuci bersih, dikeringkan, diisi padat potongan plastik dengan tongkat, lalu dirangkai menjadi meja atau kursi."
            ),
            QuizQuestion(
                id = 408,
                questionType = QuestionType.STORY_MESSAGE,
                prompt = "Nilai keteladanan yang ditunjukkan oleh Zahra dan teman-temannya adalah ...",
                options = listOf(
                    "Menunggu bantuan orang lain tanpa mau repot bertindak",
                    "Inisiatif, kepedulian lingkungan, dan kerja sama kreatif mengatasi masalah",
                    "Menyalahkan petugas kebersihan kantin sekolah",
                    "Melarang siswa lain jajan di kantin sekolah"
                ),
                correctIndex = 1,
                explanation = "Zahra dan kawan-kawan berinisiatif mencari solusi positif dengan mendaur ulang sampah menjadi barang berguna."
            ),
            QuizQuestion(
                id = 409,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Siapakah guru IPA yang mendampingi kelompok 'Agen Daur Ulang Mandiri'?",
                options = listOf(
                    "Ibu Kartini",
                    "Ibu Dewi",
                    "Pak Darmo",
                    "Pak Wiryo"
                ),
                correctIndex = 1,
                explanation = "Tersurat di paragraf 2: 'Dengan bimbingan Ibu Dewi, guru IPA mereka'."
            ),
            QuizQuestion(
                id = 410,
                questionType = QuestionType.MULTIPLE_CHOICE,
                prompt = "Apa dampak positif dari program Bank Sampah Cilik bagi siswa lain?",
                options = listOf(
                    "Siswa lain menjadi malas masuk kelas",
                    "Siswa lain tergerak mengumpulkan dan menyetorkan botol plastik bekas dari rumah",
                    "Siswa lain membuang sampah sembarangan di lapangan",
                    "Siswa lain berhenti minum air putih"
                ),
                correctIndex = 1,
                explanation = "Siswa terinspirasi membawa botol bekas dari rumah untuk dikelola secara bijak di bank sampah sekolah."
            )
        ),
        5 to listOf(
            QuizQuestion(
                id = 501,
                questionType = QuestionType.MAIN_IDEA,
                prompt = "Ide pokok paragraf ketiga pada kisah Bagas adalah ...",
                options = listOf(
                    "Bagas menguji teori aerodinamika dengan membuat model pesawat layang mini",
                    "Ayah Bagas membeli mesin jahit modern buatan Jerman",
                    "Bagas belajar berenang di danau dekat pasar",
                    "Pasar kota terbakar karena petir di sore hari"
                ),
                correctIndex = 0,
                explanation = "Paragraf 3 memaparkan bagaimana Bagas mempraktikkan teori sains dengan merancang prototipe pesawat layang bambu."
            ),
            QuizQuestion(
                id = 502,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Berapa lama prototipe glider kayu sengon rancangan Bagas mampu melayang di udara?",
                options = listOf(
                    "10 detik",
                    "45 detik tanpa mesin",
                    "5 menit dengan baling-baling baterai",
                    "2 jam melintasi bukit"
                ),
                correctIndex = 1,
                explanation = "Tersurat di paragraf 4: 'mampu melayang selama 45 detik tanpa mesin, berkat perhitungan gaya angkat sayap'."
            ),
            QuizQuestion(
                id = 503,
                questionType = QuestionType.VOCABULARY,
                prompt = "Apakah pengertian dari istilah 'aerodinamika'?",
                options = listOf(
                    "Ilmu tentang penangkapan ikan di laut dalam",
                    "Cabang fisika yang mempelajari gerak udara dan gaya pada benda bergerak di udara",
                    "Ilmu tentang struktur lapisan batuan bumi",
                    "Tata cara mengemudikan kapal selam"
                ),
                correctIndex = 1,
                explanation = "Aerodinamika adalah studi tentang aliran udara dan bagaimana gaya dorong serta gaya angkat memengaruhi penerbangan."
            ),
            QuizQuestion(
                id = 504,
                questionType = QuestionType.IMPLICIT_INFO,
                prompt = "Tokoh dirgantara Indonesia yang menjadi inspirasi besar Bagas dalam membaca adalah ...",
                options = listOf(
                    "B.J. Habibie",
                    "Ir. Soekarno",
                    "Ki Hajar Dewantara",
                    "Pangeran Diponegoro"
                ),
                correctIndex = 0,
                explanation = "Di paragraf 1 tersurat bahwa Bagas membaca rancang bangun pesawat terbang rancangan B.J. Habibie."
            ),
            QuizQuestion(
                id = 505,
                questionType = QuestionType.CONCLUSION,
                prompt = "Kesimpulan yang dapat ditarik dari perjuangan Bagas meraih juara adalah ...",
                options = listOf(
                    "Glider yang baik harus dibuat dari logam emas yang mahal",
                    "Keterbatasan ekonomi bukan halangan untuk berprestasi jika dibarengi ketekunan belajar",
                    "Membuat pesawat layang hanya membutuhkan keberuntungan semata",
                    "Bagas tidak perlu sekolah lagi karena sudah pandai"
                ),
                correctIndex = 1,
                explanation = "Meskipun dari keluarga sederhana, kerja keras dan kegemarannya membaca sains mengantarkan Bagas menjadi juara."
            ),
            QuizQuestion(
                id = 506,
                questionType = QuestionType.TRUE_FALSE,
                prompt = "Benar atau Salah: Bagas mendapatkan buku-buku sains penerbangan dengan cara meminjam dari Mobil Perpustakaan Keliling Daerah.",
                options = listOf(
                    "Benar",
                    "Salah"
                ),
                correctIndex = 0,
                explanation = "Benar, setiap hari Selasa Bagas meminjam buku-buku bermutu dari mobil perpustakaan keliling di dekat pasar."
            ),
            QuizQuestion(
                id = 507,
                questionType = QuestionType.STORY_MESSAGE,
                prompt = "Pesan moral dari kisah Bagas yang paling tepat untuk siswa kelas 6 SD adalah ...",
                options = listOf(
                    "Jangan pernah menyerah meraih cita-cita dan teruslah membaca untuk menambah ilmu",
                    "Pilihlah pekerjaan yang paling santai tanpa perlu banyak belajar",
                    "Menunggu bantuan orang kaya adalah cara terbaik menjadi sukses",
                    "Buku perpustakaan keliling tidak berguna bagi masa depan"
                ),
                correctIndex = 0,
                explanation = "Kisah Bagas mengajarkan pentingnya ketekunan, optimisme, dan gemar membaca dalam meraih mimpi masa depan."
            ),
            QuizQuestion(
                id = 508,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Apakah pekerjaan ayah Bagas di pinggir pasar kota?",
                options = listOf(
                    "Pilot pesawat komersial",
                    "Tukang sol sepatu",
                    "Penjual buku keliling",
                    "Montir mesin helikopter"
                ),
                correctIndex = 1,
                explanation = "Tersurat di paragraf 1: 'menunggu ayahnya yang sibuk menjahit sol sepatu pelanggan'."
            ),
            QuizQuestion(
                id = 509,
                questionType = QuestionType.MULTIPLE_CHOICE,
                prompt = "Mengapa dewan juri terpukau dengan presentasi Bagas dalam lomba karya ilmiah?",
                options = listOf(
                    "Karena glider Bagas menggunakan mesin turbo impor",
                    "Karena penjelasan logis Bagas dan penguasaan sains yang sangat matang",
                    "Karena Bagas memakai baju pilot sungguhan saat lomba",
                    "Karena Bagas membawa makanan untuk dewan juri"
                ),
                correctIndex = 1,
                explanation = "Para juri kagum karena pemahaman sains dan perhitungan gaya angkat glider Bagas sangat presisi dan masuk akal."
            ),
            QuizQuestion(
                id = 510,
                questionType = QuestionType.MATCHING,
                prompt = "Bahan yang digunakan Bagas untuk merancang rangka awal pesawat layang buatannya adalah ...",
                options = listOf(
                    "Bilah bambu tipis, kertas kalkir, dan kayu sengon",
                    "Besi baja dan kawat tembaga",
                    "Kaca serat dan plastik tebal",
                    "Tanah liat dan semen putih"
                ),
                correctIndex = 0,
                explanation = "Bagas memanfaatkan bilah bambu tipis, kertas kalkir, dan kayu sengon lokal yang ringan namun kokoh."
            )
        )
    )
}
