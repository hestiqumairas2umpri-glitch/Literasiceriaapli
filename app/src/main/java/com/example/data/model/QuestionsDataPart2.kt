package com.example.data.model

object QuestionsDataPart2 {
    val questionsMap = mapOf(
        6 to listOf(
            QuizQuestion(
                id = 601,
                questionType = QuestionType.MAIN_IDEA,
                prompt = "Ide pokok paragraf pertama pada cerita 'Desa yang Kembali Hijau' adalah ...",
                options = listOf(
                    "Ancaman abrasi ombak laut yang mengikis pantai dan merusak desa nelayan lima tahun lalu",
                    "Pesta rakyat memperingati hari ulang tahun desa",
                    "Pembangunan pelabuhan kapal pesiar internasional di laut Jawa",
                    "Musim penangkapan cumi-cumi terbesar di Muara Baru"
                ),
                correctIndex = 0,
                explanation = "Paragraf 1 menguraikan kondisi kritis pesisir Desa Muara Baru yang terancam abrasi ombak laut."
            ),
            QuizQuestion(
                id = 602,
                questionType = QuestionType.VOCABULARY,
                prompt = "Apakah makna kata 'abrasi' sesuai dengan teks bacaan?",
                options = listOf(
                    "Pengikisan daratan pantai akibat hantaman ombak dan arus laut",
                    "Pencemaran udara oleh asap pabrik",
                    "Kebakaran hutan akibat kemarau panjang",
                    "Penyumbatan saluran air karena sampah rumah tangga"
                ),
                correctIndex = 0,
                explanation = "Abrasi adalah peristiwa pengikisan daratan tepi pantai oleh gelombang laut."
            ),
            QuizQuestion(
                id = 603,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Apa nama gerakan penghijauan yang diprakarsai kepala desa dan guru?",
                options = listOf(
                    "'Satu Jiwa Satu Bakau'",
                    "'Laut Bersih Nelayan Makmur'",
                    "'Desa Emas Nusantara'",
                    "'Taman Bahari Lestari'"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 2: 'melakukan gerakan besar pemulihan lingkungan bertajuk Satu Jiwa Satu Bakau'."
            ),
            QuizQuestion(
                id = 604,
                questionType = QuestionType.IMPLICIT_INFO,
                prompt = "Mengapa akar tunjang pohon bakau sangat efektif menahan hantaman ombak badai?",
                options = listOf(
                    "Karena akarnya beracun bagi hewan laut",
                    "Karena struktur akarnya yang rapat, kokoh, dan mencengkeram lumpur pantai dengan kuat",
                    "Karena akarnya terapung mengikuti arah angin",
                    "Karena daunnya sangat lebar seperti payung"
                ),
                correctIndex = 1,
                explanation = "Akar tunjang pohon bakau saling menjalin kuat, meredam energi gelombang ombak dan menahan sedimentasi lumpur."
            ),
            QuizQuestion(
                id = 605,
                questionType = QuestionType.CONCLUSION,
                prompt = "Kesimpulan dampak positif jangka panjang dari hutan bakau bagi masyarakat desa adalah ...",
                options = listOf(
                    "Desa terbebas dari abrasi pantai dan perekonomian meningkat melalui ekowisata bahari",
                    "Warga desa dilarang melaut selamanya",
                    "Pantai menjadi sepi dan tidak ada wisatawan yang datang",
                    "Harga ikan menjadi sangat mahal karena nelayan pindah"
                ),
                correctIndex = 0,
                explanation = "Hutan bakau tidak hanya menyelamatkan pemukiman dari abrasi, tetapi juga menjadi sentra ekowisata dan pembiakan ikan."
            ),
            QuizQuestion(
                id = 606,
                questionType = QuestionType.TRUE_FALSE,
                prompt = "Benar atau Salah: Burung kuntul putih dan kepiting bakau enggan hidup di hutan bakau karena airnya terlalu asin.",
                options = listOf(
                    "Benar",
                    "Salah"
                ),
                correctIndex = 1,
                explanation = "Salah, paragraf 4 menyatakan kawanan burung kuntul kembali bersarang dan kepiting berkembang biak dengan aman."
            ),
            QuizQuestion(
                id = 607,
                questionType = QuestionType.STORY_MESSAGE,
                prompt = "Amanat utama dari keberhasilan restorasi Desa Muara Baru adalah ...",
                options = listOf(
                    "Persatuan dan kerja sama gotong royong seluruh warga mampu memulihkan kelestarian alam",
                    "Biarkan alam rusak sendiri karena manusia tidak berdaya melawannya",
                    "Menyerahkan seluruh pekerjaan lingkungan hanya kepada anak-anak sekolah",
                    "Membangun tanggul beton adalah satu-satunya solusi mengatasi abrasi laut"
                ),
                correctIndex = 0,
                explanation = "Kerja sama tulus seluruh elemen masyarakat dan generasi muda membuktikan kekuatan pelestarian bumi pertiwi."
            ),
            QuizQuestion(
                id = 608,
                questionType = QuestionType.EVENT_ORDER,
                prompt = "Alur perubahan kondisi Desa Muara Baru yang tepat adalah ...",
                options = listOf(
                    "Abrasi mengikis pantai -> Gerakan Satu Jiwa Satu Bakau -> Bakau tumbuh kokoh -> Menjadi sentra ekowisata",
                    "Menjadi sentra ekowisata -> Terjadi abrasi -> Menanam bakau -> Pohon ditebang",
                    "Menanam bakau -> Pantai gersang -> Wisatawan datang -> Abrasi terjadi",
                    "Ombak badai hilang -> Menanam pohon kelapa -> Pantai terancam abrasi"
                ),
                correctIndex = 0,
                explanation = "Kisah dimulai dari ancaman abrasi, aksi gotong royong menanam bakau, pertumbuhan hutan bakau, hingga menjadi desa ekowisata."
            ),
            QuizQuestion(
                id = 609,
                questionType = QuestionType.MATCHING,
                prompt = "Peran siswa kelas 6 SD dalam gerakan penanaman bakau adalah ...",
                options = listOf(
                    "Membawa dan menancapkan ajir bambu penopang bibit bakau",
                    "Mengemudikan traktor besar di laut",
                    "Memasak di dapur restoran hotel",
                    "Menjual tiket masuk perahu nelayan"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 3: 'Anak-anak dengan celana digulung hingga lutut membawa ajir bambu penopang'."
            ),
            QuizQuestion(
                id = 610,
                questionType = QuestionType.MULTIPLE_CHOICE,
                prompt = "Jenis tanaman bakau yang ditanam warga dalam cerita tersebut adalah marga ...",
                options = listOf(
                    "Rhizophora",
                    "Pinus merkusii",
                    "Kaktus saguaro",
                    "Kelapa sawit"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 3: 'bibit pohon bakau jenis Rhizophora ke dalam lumpur sedalam tiga puluh sentimeter'."
            )
        ),
        7 to listOf(
            QuizQuestion(
                id = 701,
                questionType = QuestionType.MAIN_IDEA,
                prompt = "Gagasan pokok paragraf ketiga pada bacaan 'Ekspedisi ke Pulau Kecil' adalah ...",
                options = listOf(
                    "Keanekaragaman hayati terumbu karang sebagai rumah bagi berbagai biota laut",
                    "Mesin kapal motor rusak di tengah samudra",
                    "Penyu hijau tidak mau bertelur di pasir putih",
                    "Mahasiswa universitas kehilangan kacamata renang"
                ),
                correctIndex = 0,
                explanation = "Paragraf 3 memaparkan fungsi koloni terumbu karang sebagai habitat anemon, ikan badut, dan bintang laut."
            ),
            QuizQuestion(
                id = 702,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Berapa butir perkiraan telur yang diletakkan oleh penyu hijau di pantai pada malam hari?",
                options = listOf(
                    "Sekitar seratus butir telur",
                    "Sepuluh butir telur",
                    "Seribu butir telur",
                    "Dua butir telur"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 4: 'meletakkan seratus butir telur di tempat yang hangat dan aman'."
            ),
            QuizQuestion(
                id = 703,
                questionType = QuestionType.VOCABULARY,
                prompt = "Terumbu karang tersusun dari senyawa kapur yang disebut ...",
                options = listOf(
                    "Kalsium karbonat",
                    "Natrium klorida",
                    "Besi oksida",
                    "Gas nitrogen"
                ),
                correctIndex = 0,
                explanation = "Kerangka keras karang dibentuk oleh hewan koral melalui pengendapan kalsium karbonat."
            ),
            QuizQuestion(
                id = 704,
                questionType = QuestionType.IMPLICIT_INFO,
                prompt = "Mengapa para siswa dilarang menginjak atau mematahkan terumbu karang saat snorkeling?",
                options = listOf(
                    "Karena karang membutuhkan waktu bertahun-tahun untuk tumbuh hanya beberapa sentimeter",
                    "Karena karang terbuat dari kaca tajam yang mudah pecah",
                    "Karena karang akan meledak jika disentuh manusia",
                    "Karena karang bisa menggigit kaki penyelam"
                ),
                correctIndex = 0,
                explanation = "Karang adalah hewan hidup berpori yang sangat rentan rusak dan membutuhkan waktu sangat lama untuk tumbuh."
            ),
            QuizQuestion(
                id = 705,
                questionType = QuestionType.CONCLUSION,
                prompt = "Kesimpulan yang dapat dirumuskan dari kegiatan ekspedisi ilmiah ini adalah ...",
                options = listOf(
                    "Laut Indonesia kaya akan biota laut indah yang membutuhkan perlindungan dari sampah plastik dan eksploitasi",
                    "Semua pulau karang harus ditimbun pasir agar menjadi daratan luas",
                    "Hewan laut tidak membutuhkan perlindungan manusia",
                    "Penyu hijau lebih baik dipelihara di akuarium rumah"
                ),
                correctIndex = 0,
                explanation = "Ekosistem laut Indonesia merupakan kekayaan alam bernilai tinggi yang wajib dilestarikan bersama."
            ),
            QuizQuestion(
                id = 706,
                questionType = QuestionType.TRUE_FALSE,
                prompt = "Benar atau Salah: Terumbu karang adalah sekumpulan batu mati yang tidak memiliki kehidupan sama sekali.",
                options = listOf(
                    "Benar",
                    "Salah"
                ),
                correctIndex = 1,
                explanation = "Salah, peneliti menjelaskan terumbu karang terbentuk dari ribuan hewan koral mungil yang hidup berkoloni."
            ),
            QuizQuestion(
                id = 707,
                questionType = QuestionType.STORY_MESSAGE,
                prompt = "Aksi nyata yang dapat dilakukan siswa untuk menjaga laut setelah membaca kisah ini adalah ...",
                options = listOf(
                    "Mengurangi penggunaan kantong plastik sekali pakai agar tidak mencemari laut",
                    "Membuang bungkus makanan ke pantai saat bertamasya",
                    "Membeli cinderamata dari karang laut yang diawetkan",
                    "Menangkap ikan badut untuk dijual di pasar ikan hias"
                ),
                correctIndex = 0,
                explanation = "Mengurangi sampah plastik adalah langkah nyata mencegah satwa laut seperti penyu memakan limbah beracun."
            ),
            QuizQuestion(
                id = 708,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Dua siswa yang disebutkan dalam cerita saat mengamati geladak kapal adalah ...",
                options = listOf(
                    "Arga dan Laras",
                    "Reno dan Dania",
                    "Made dan Fajar",
                    "Bagas dan Zahra"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 1: 'Arga, Laras, dan teman-teman sekelasnya memandang takjub'."
            ),
            QuizQuestion(
                id = 709,
                questionType = QuestionType.MATCHING,
                prompt = "Hewan laut bertubuh lunak dengan tentakel yang menjadi sahabat ikan badut adalah ...",
                options = listOf(
                    "Anemon laut",
                    "Kepiting bakau",
                    "Penyu hijau",
                    "Burung kuntul"
                ),
                correctIndex = 0,
                explanation = "Anemon laut bersimbiosis mutualisme dengan ikan badut, saling melindungi di terumbu karang."
            ),
            QuizQuestion(
                id = 710,
                questionType = QuestionType.MULTIPLE_CHOICE,
                prompt = "Kapan penyu hijau biasanya naik ke daratan pantai untuk bertelur?",
                options = listOf(
                    "Pada malam hari di tempat berpasir yang tenang dan hangat",
                    "Pada tengah hari saat matahari terik membakar",
                    "Saat terjadi badai topan besar di laut",
                    "Ketika air laut sedang surut di pagi buta"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 4 bahwa penyu hijau naik pada malam hari agar telurnya aman dari pemangsa dan terik matahari."
            )
        ),
        8 to listOf(
            QuizQuestion(
                id = 801,
                questionType = QuestionType.MAIN_IDEA,
                prompt = "Pokok pikiran yang termaktub dalam surat Raka Pramudya adalah ...",
                options = listOf(
                    "Di era kecanggihan teknologi, ketekunan membaca dan integritas nurani tetap menjadi kunci keberhasilan sejati",
                    "Pohon kenari sekolah harus ditebang untuk memperluas lapangan basket",
                    "OSIS tahun 2004 meminta kiriman telepon pintar terbaru",
                    "Kapsul waktu terbuat dari besi yang mudah berkarat"
                ),
                correctIndex = 0,
                explanation = "Pesan utama surat mengingatkan generasi mendatang untuk memegang teguh kebijaksanaan berpikir melalui membaca dan kejujuran."
            ),
            QuizQuestion(
                id = 802,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Kapan kapsul waktu tersebut pertama kali ditanam di bawah pohon kenari?",
                options = listOf(
                    "2 Mei 2004",
                    "17 Agustus 1945",
                    "28 Oktober 1928",
                    "10 November 2020"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 1: 'Ditanam 2 Mei 2004, Buka 20 Tahun Kemudian'."
            ),
            QuizQuestion(
                id = 803,
                questionType = QuestionType.VOCABULARY,
                prompt = "Kata 'integritas' memiliki arti yang setara dengan ...",
                options = listOf(
                    "Kejujuran dan keteguhan memegang prinsip moral yang benar",
                    "Kemampuan berlari sangat cepat di lapangan",
                    "Kekayaan harta benda yang melimpah ruah",
                    "Keinginan untuk selalu dipuji orang lain"
                ),
                correctIndex = 0,
                explanation = "Integritas bermakna keselarasan antara pikiran, perkataan, dan tindakan terpuji berlandaskan kejujuran."
            ),
            QuizQuestion(
                id = 804,
                questionType = QuestionType.IMPLICIT_INFO,
                prompt = "Mengapa penanaman kapsul waktu dilakukan pada tanggal 2 Mei?",
                options = listOf(
                    "Karena tanggal 2 Mei diperingati sebagai Hari Pendidikan Nasional",
                    "Karena hari itu adalah hari libur akhir semester",
                    "Karena terjadi gerhana matahari di sekolah",
                    "Karena guru-guru sedang mengadakan rapat tahunan"
                ),
                correctIndex = 0,
                explanation = "Tanggal 2 Mei merupakan Hari Pendidikan Nasional (kelahiran Ki Hajar Dewantara), lambang semangat belajar."
            ),
            QuizQuestion(
                id = 805,
                questionType = QuestionType.CONCLUSION,
                prompt = "Kesimpulan yang dapat dipetik oleh Radit dan kawan-kawan kelas 6 adalah ...",
                options = listOf(
                    "Mereka terinspirasi untuk tekun membaca dan menanam kapsul waktu berisi impian bagi generasi penerus",
                    "Surat masa lalu sudah ketinggalan zaman dan tidak perlu dibaca",
                    "Pohon kenari sekolah harus segera dipagari kawat berduri",
                    "Disket komputer kuno harus dibuang ke tempat sampah"
                ),
                correctIndex = 0,
                explanation = "Para siswa bertekad menjaga api semangat belajar dan melanjutkan estafet kebajikan dengan membuat kapsul waktu baru."
            ),
            QuizQuestion(
                id = 806,
                questionType = QuestionType.TRUE_FALSE,
                prompt = "Benar atau Salah: Kapsul waktu tersebut rusak dan basah kuyup karena tabungnya bocor terkena air hujan.",
                options = listOf(
                    "Benar",
                    "Salah"
                ),
                correctIndex = 1,
                explanation = "Salah, tersurat di paragraf 2 bahwa tabung silinder stainless steel tersebut terkunci rapat dan kedap air."
            ),
            QuizQuestion(
                id = 807,
                questionType = QuestionType.STORY_MESSAGE,
                prompt = "Amanat cerita yang berkaitan dengan perkembangan zaman adalah ...",
                options = listOf(
                    "Manfaatkan teknologi secara cerdas tanpa melupakan nilai budi pekerti dan buku bacaan bermutu",
                    "Tinggalkan buku cetak sepenuhnya dan bermain game sepanjang hari",
                    "Jangan pernah belajar dari pengalaman orang-orang di masa lalu",
                    "Menjadi juara kelas hanya penting jika dipuji di media sosial"
                ),
                correctIndex = 0,
                explanation = "Teknologi adalah alat, tetapi fondasi kepribadian dan wawasan dibangun lewat membaca dan budi pekerti luhur."
            ),
            QuizQuestion(
                id = 808,
                questionType = QuestionType.MATCHING,
                prompt = "Barang-barang bersejarah yang ditemukan di dalam kapsul waktu antara lain ...",
                options = listOf(
                    "Foto lama, disket komputer, kliping koran, dan surat bersegel lilin",
                    "Baterai lithium, drone terbang, dan tablet layar sentuh",
                    "Batu permata merah delima dan pedang antik",
                    "Uang kertas edisi terbaru tahun 2024"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 2: 'foto-foto lama, disket komputer, kliping koran kuno, dan sejumlah pucuk surat bersegel lilin merah'."
            ),
            QuizQuestion(
                id = 809,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Siapakah nama mantan ketua OSIS yang menulis sepucuk surat penuh inspirasi tersebut?",
                options = listOf(
                    "Raka Pramudya",
                    "Radit Santoso",
                    "Made Arimbawa",
                    "Bagas Dirgantara"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 3: 'ditulis oleh mantan ketua OSIS angkatan tersebut, yang bernama Raka Pramudya'."
            ),
            QuizQuestion(
                id = 810,
                questionType = QuestionType.MULTIPLE_CHOICE,
                prompt = "Bagaimana suasana aula sekolah saat Radit selesai membacakan surat dari masa lalu tersebut?",
                options = listOf(
                    "Hening sejenak meresapi nasihat penuh makna dari dua puluh tahun silam",
                    "Gaduh dan siswa saling berebut tabung kapsul",
                    "Banyak siswa yang tertawa mengejek isi surat",
                    "Semua guru meninggalkan ruangan tanpa berkata sepatah katapun"
                ),
                correctIndex = 0,
                explanation = "Suasana aula menjadi hening dan khidmat karena semua hadirin tersentuh oleh pesan mendalam dari para pendahulu mereka."
            )
        ),
        9 to listOf(
            QuizQuestion(
                id = 901,
                questionType = QuestionType.MAIN_IDEA,
                prompt = "Ide pokok cerita fantasi 'Ketika Buku Berbicara' adalah ...",
                options = listOf(
                    "Petualangan imajinatif Mentari ke Negeri Aksara yang menyadarkan pentingnya membaca bagi peradaban",
                    "Hujan lebat yang membanjiri loteng rumah kakek Wiryo",
                    "Sepeda Mentari mogok di jalan raya",
                    "Burung hantu Nawa mencari makanan di malam hari"
                ),
                correctIndex = 0,
                explanation = "Cerita ini menggambarkan pengalaman fantasi Mentari yang belajar bahwa membaca adalah nyawa peradaban manusia."
            ),
            QuizQuestion(
                id = 902,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Siapakah nama Sang Penjaga Kata berwujud burung hantu bijaksana bermata permata biru?",
                options = listOf(
                    "Nawa",
                    "Mentari",
                    "Wiryo",
                    "Barong"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 4: 'seekor burung hantu bijaksana bermata permata biru bernama Nawa'."
            ),
            QuizQuestion(
                id = 903,
                questionType = QuestionType.VOCABULARY,
                prompt = "Apakah arti dari kata 'metafora' dalam pembelajaran bahasa Indonesia?",
                options = listOf(
                    "Gaya bahasa kiasan yang membandingkan dua hal secara langsung tanpa kata pembanding",
                    "Tanda baca titik dua pada dialog naskah drama",
                    "Daftar pustaka di halaman belakang kamus",
                    "Susunan bait dalam puisi empat baris"
                ),
                correctIndex = 0,
                explanation = "Metafora adalah majas perbandingan langsung (misalnya: buku adalah jendela dunia, singa padang pasir)."
            ),
            QuizQuestion(
                id = 904,
                questionType = QuestionType.IMPLICIT_INFO,
                prompt = "Apa akibat tersirat jika generasi muda manusia berhenti membaca buku?",
                options = listOf(
                    "Ilmu pengetahuan dan peradaban manusia akan redup serta mundur ditelan ketidaktahuan",
                    "Udara di bumi akan menjadi lebih dingin",
                    "Pohon-pohon di hutan akan berbuah emas murni",
                    "Hewan-hewan akan mulai belajar menulis surat"
                ),
                correctIndex = 0,
                explanation = "Nawa menegaskan bahwa tanpa membaca, khazanah kecerdasan manusia akan lenyap ditelan kebodohan."
            ),
            QuizQuestion(
                id = 905,
                questionType = QuestionType.CONCLUSION,
                prompt = "Kesimpulan perubahan sikap Mentari setelah mengalami petualangan magis tersebut adalah ...",
                options = listOf(
                    "Membaca bukan lagi sekadar tugas paksaan, melainkan petualangan menyenangkan membuka gerbang semesta ilmu",
                    "Mentari tidak mau lagi membuka buku karena takut tersedot",
                    "Mentari memutuskan untuk menjual kitab tua kakek ke pasar loak",
                    "Mentari menolak pergi ke perpustakaan sekolah"
                ),
                correctIndex = 0,
                explanation = "Mentari menyadari keagungan membaca dan menjadikannya kegemaran penuh antusiasme seumur hidup."
            ),
            QuizQuestion(
                id = 906,
                questionType = QuestionType.TRUE_FALSE,
                prompt = "Benar atau Salah: Negeri Aksara memiliki sungai yang mengalirkan air warna pelangi dan jalan setapak dari peribahasa bijak.",
                options = listOf(
                    "Benar",
                    "Salah"
                ),
                correctIndex = 0,
                explanation = "Benar, tersurat jelas di paragraf 3 sebagai gambaran imajinasi dunia bahasa dan sastra."
            ),
            QuizQuestion(
                id = 907,
                questionType = QuestionType.STORY_MESSAGE,
                prompt = "Amanat yang ingin disampaikan pengarang melalui petualangan Mentari adalah ...",
                options = listOf(
                    "Membaca melatih daya imajinasi kreatif dan memperkaya perbendaharaan kata",
                    "Jangan pernah berteduh di rumah kakek saat hujan turun",
                    "Semua burung hantu di hutan bisa berbicara bahasa manusia",
                    "Membaca buku tebal hanya akan membuat kepala pusing"
                ),
                correctIndex = 0,
                explanation = "Kisah fantasi ini mengajak anak-anak mencintai buku sebagai sumber imajinasi kreatif dan ilmu abadi."
            ),
            QuizQuestion(
                id = 908,
                questionType = QuestionType.EVENT_ORDER,
                prompt = "Urutan petualangan Mentari yang benar adalah ...",
                options = listOf(
                    "Meneduh di loteng -> Membuka kitab tua -> Masuk Negeri Aksara -> Bertemu Nawa -> Sadar pentingnya membaca",
                    "Bertemu Nawa -> Naik sepeda -> Membaca buku -> Masuk ke sungai pelangi",
                    "Masuk Negeri Aksara -> Menutup buku -> Bermain sepeda di tengah hujan",
                    "Membaca peribahasa -> Tidur di sekolah -> Membuka lemari buku tua"
                ),
                correctIndex = 0,
                explanation = "Alur berawal dari meneduh di loteng, membuka buku bercahaya, masuk gerbang Negeri Aksara, berdialog dengan Nawa, dan terbangun dengan tekad baru."
            ),
            QuizQuestion(
                id = 909,
                questionType = QuestionType.MATCHING,
                prompt = "Di manakah Mentari menemukan kitab berpinggiran perak menyala tersebut?",
                options = listOf(
                    "Di atas peti kayu berukir mawar di loteng rumah Kakek Wiryo",
                    "Di laci meja belajar kelas 6 SD",
                    "Di bawah pohon beringin lapangan desa",
                    "Di dalam loker stasiun kereta api"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 1: 'Di atas peti kayu berukir mawar, tergeletak sebuah kitab tebal dengan pinggiran kertas berwarna perak'."
            ),
            QuizQuestion(
                id = 910,
                questionType = QuestionType.MULTIPLE_CHOICE,
                prompt = "Suara bisikan dari halaman pertama buku menyatakan bahwa buku adalah suara dari ...",
                options = listOf(
                    "Setiap penulis yang menuangkan mimpinya ke atas kertas",
                    "Robot masa depan yang sedang mengisi baterai",
                    "Angin badai yang merobohkan atap rumah",
                    "Rekaman kaset radio zaman dulu"
                ),
                correctIndex = 0,
                explanation = "Buku adalah rekaman buah pikiran, mimpi, dan cita-cita para penulis dari generasi ke generasi."
            )
        ),
        10 to listOf(
            QuizQuestion(
                id = 1001,
                questionType = QuestionType.MAIN_IDEA,
                prompt = "Gagasan utama paragraf ketiga bacaan 'Misi Besar Tim Literasi' adalah ...",
                options = listOf(
                    "Kreativitas tim literasi mendekorasi lorong sekolah dengan rak buku gantung dan kotak Buku Kejutan",
                    "Siswa SD bermain gim ponsel di halaman kantin",
                    "Guru-guru membersihkan aula sekolah untuk ujian akhir",
                    "SD Teladan Harapan pindah ke gedung baru"
                ),
                correctIndex = 0,
                explanation = "Paragraf 3 mengulas langkah nyata tim literasi menghias lorong sekolah menjadi pojok baca nyaman dan menarik."
            ),
            QuizQuestion(
                id = 1002,
                questionType = QuestionType.EXPLICIT_INFO,
                prompt = "Apa nama kegiatan festival puncak yang diadakan untuk memeriahkan gerakan membaca?",
                options = listOf(
                    "'Pekan Parade Karakter Buku'",
                    "'Lomba Cerdas Cermat Matematika'",
                    "'Karnaval Sepeda Hias'",
                    "'Bazar Makanan Tradisional'"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 4: 'Puncaknya, mereka menyelenggarakan Pekan Parade Karakter Buku'."
            ),
            QuizQuestion(
                id = 1003,
                questionType = QuestionType.VOCABULARY,
                prompt = "Kata 'persepsi' dalam kalimat 'mengubah persepsi bahwa membaca itu membosankan' berarti ...",
                options = listOf(
                    "Pandangan atau anggapan seseorang mengenai suatu hal",
                    "Harga tiket masuk gedung pertunjukan",
                    "Jumlah halaman buku cerita bergambar",
                    "Warna cat dinding ruangan kelas"
                ),
                correctIndex = 0,
                explanation = "Persepsi adalah cara seseorang memandang, menafsirkan, atau memahami suatu hal."
            ),
            QuizQuestion(
                id = 1004,
                questionType = QuestionType.IMPLICIT_INFO,
                prompt = "Mengapa buku-buku di pojok baca dibungkus dengan kertas kado bertajuk 'Buku Kejutan'?",
                options = listOf(
                    "Untuk memancing rasa penasaran siswa agar tertarik membaca isi buku tanpa melihat sampulnya dulu",
                    "Karena buku-buku tersebut basah terkena air hujan",
                    "Agar siswa harus membayar uang sewa sebelum membuka kado",
                    "Supaya buku tidak dibaca oleh guru"
                ),
                correctIndex = 0,
                explanation = "Konsep Buku Kejutan membangkitkan rasa ingin tahu dan sensasi petualangan seru saat memilih buku."
            ),
            QuizQuestion(
                id = 1005,
                questionType = QuestionType.CONCLUSION,
                prompt = "Kesimpulan dari gerakan literasi yang digagas oleh Anisa dan kawan-kawan adalah ...",
                options = listOf(
                    "Inisiatif kepemimpinan siswa dapat mengubah budaya sekolah menjadi gemar membaca secara menyenangkan",
                    "Membaca buku hanya bermanfaat bagi siswa kelas 6 saja",
                    "Telepon pintar harus disita selamanya dari anak-anak sekolah",
                    "Pojok baca membuat lorong sekolah menjadi kotor dan sempit"
                ),
                correctIndex = 0,
                explanation = "Semangat gotong royong dan pendekatan kreatif siswa berhasil menumbuhkan budaya literasi yang kuat di sekolah."
            ),
            QuizQuestion(
                id = 1006,
                questionType = QuestionType.TRUE_FALSE,
                prompt = "Benar atau Salah: Sebelum ada tim literasi, kebanyakan siswa lebih suka membaca buku di perpustakaan daripada bermain gim ponsel.",
                options = listOf(
                    "Benar",
                    "Salah"
                ),
                correctIndex = 1,
                explanation = "Salah, paragraf 1 menyatakan minat baca sebelumnya rendah dan siswa lebih banyak bermain gim saat istirahat."
            ),
            QuizQuestion(
                id = 1007,
                questionType = QuestionType.STORY_MESSAGE,
                prompt = "Amanat kepemimpinan yang dapat diteladani dari sosok Anisa adalah ...",
                options = listOf(
                    "Berani mengambil inisiatif positif dan mengajak teman berkolaborasi memecahkan masalah bersama",
                    "Menunggu perintah dari kepala sekolah tanpa berbuat apa-apa",
                    "Menyuruh siswa lain bekerja sementara dirinya sendiri beristirahat",
                    "Menyerah ketika melihat minat baca teman-temannya rendah"
                ),
                correctIndex = 0,
                explanation = "Anisa menunjukkan jiwa kepemimpinan pelayan yang kreatif, solutif, dan menggerakkan orang lain demi kebaikan."
            ),
            QuizQuestion(
                id = 1008,
                questionType = QuestionType.EVENT_ORDER,
                prompt = "Urutan aksi tim literasi sekolah dalam cerita di atas adalah ...",
                options = listOf(
                    "Mengajukan ide gerakan -> Menghias pojok baca -> Membuat Buku Kejutan -> Mengadakan Parade Karakter",
                    "Mengadakan Parade Karakter -> Menghias pojok baca -> Membeli gim ponsel -> Mengajukan ide",
                    "Menutup perpustakaan -> Menjual buku kado -> Membuat spanduk lomba",
                    "Menghias pojok baca -> Menghentikan kegiatan literasi -> Mengeluh kepada guru"
                ),
                correctIndex = 0,
                explanation = "Tim mengusulkan ide, membuat pojok baca kreatif, menyediakan Buku Kejutan, dan menggelar Parade Karakter Buku."
            ),
            QuizQuestion(
                id = 1009,
                questionType = QuestionType.MATCHING,
                prompt = "Semboyan gerakan literasi yang digagas oleh Anisa dan sahabat-sahabatnya adalah ...",
                options = listOf(
                    "'Dari Siswa, Oleh Siswa, Untuk Siswa'",
                    "'Tiada Hari Tanpa Menang Lomba'",
                    "'Buku Mahal Pasti Juara'",
                    "'Belajar Keras Tanpa Istirahat'"
                ),
                correctIndex = 0,
                explanation = "Tersurat di paragraf 2: 'Gerakan Sahabat Buku: Dari Siswa, Oleh Siswa, Untuk Siswa'."
            ),
            QuizQuestion(
                id = 1010,
                questionType = QuestionType.MULTIPLE_CHOICE,
                prompt = "Aktivitas apa saja yang dilakukan siswa saat 'Pekan Parade Karakter Buku' berlangsung?",
                options = listOf(
                    "Memakai kostum tokoh buku favorit, bercerita di panggung, dan bertukar buku cerita",
                    "Membeli buku baru dari penerbit luar negeri secara daring",
                    "Mengerjakan soal ujian tertulis selama empat jam penuh",
                    "Menonton pertunjukan kembang api di lapangan sekolah"
                ),
                correctIndex = 0,
                explanation = "Siswa mengekspresikan karakter buku kesayangan mereka dengan kostum, berbagi sinopsis cerita, dan bertukar buku."
            )
        )
    )
}
