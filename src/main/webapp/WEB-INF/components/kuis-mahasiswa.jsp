<h1>Kuis</h1>
${kuis}
<div id="next-ujian">
    <h2>Kuis Tersedia</h2>
    <div class='wrapper-next' id="wrapper-next"></div>
</div>

<script>
    var idt = document.getElementById("idt");
    var wrapper = document.querySelector(".wrapper");
    var jenis = document.getElementById("jenis");
    var matkul = document.getElementById("matkul");
    var dosen = document.getElementById("dosen");
    var jumlahSoal = document.getElementById("jml-soal");
    var menit = document.getElementById("menit");
    var batasWaktu = document.getElementById("batas");
    var nextKuis = document.getElementById("next-ujian");
    var wrapperNextKuis = document.getElementById("wrapper-next");
    var matkulNext = document.getElementById("matkul-next");
    var batasNext = document.getElementById("batas-next");

    fetch("https://web-ujian.herokuapp.com/get-kuis-mahasiswa")
        .then(response => {return response.json()})
        .then(result => {saveKuis(result)})

    function saveKuis(data) {
        dataKuis = data;
    }

    fetch("https://web-ujian.herokuapp.com/get-kuis-mahasiswa")
        .then((response) => {
            return response.json();
        })
        .then((myJson) => {
            let dataKuis = myJson

            let batas = new Date(dataKuis[0].batasWaktu)
            batas = batas.toLocaleDateString('id-id', {weekday:'long', day:'2-digit',
                month:'short', year:'numeric', hour:'2-digit', minute:'2-digit'})
            batasWaktu.innerHTML = "Batas: " + batas;
            jenis.innerText = dataKuis[0].namaKuis;
            matkul.innerText = dataKuis[0].id_matkul.mataKuliah;
            dosen.innerHTML = "<img src='/assets/avatar.png' alt='avatar'>" + dataKuis[0].namaDosen;
            jumlahSoal.innerText = dataKuis[0].jumlahSoal + " Soal";
            menit.innerText = dataKuis[0].waktuPengerjaan + " Menit";
            wrapper.innerHTML += "<div class='cur-ujian-mulai'><a class='btn' href='kuis/peraturan/" + dataKuis[0].id + "' >Mulai Kuis</a></div>";

            if (dataKuis.length > 1) {
                let color;
                let innerColor;
                nextKuis.style.display = "block";
                for (i = 1; i < dataKuis.length; i++) {
                    if (i % 3 == 1) {
                        color = "var(--color1)";
                        innerColor = "var(--color1-inner)";
                    } else if (i % 3 == 2) {
                        color = "var(--color2)";
                        innerColor = "var(--color2-inner)";
                    } else {
                        color = "var(--color3)";
                        innerColor = "var(--color3-inner)";
                    }

                    batas = new Date(dataKuis[i].batasWaktu);
                    batas = batas.toLocaleDateString('id-id', {
                        weekday: 'long', day: '2-digit',
                        month: 'long', year: 'numeric'
                    })

                    wrapperNextKuis.innerHTML += "<div class='ujian-next kuis' onclick='document.location.href=\"/kuis/peraturan/" + dataKuis[i].id + "\"' style='background-color: " + color + "'>" +
                        "<div class='icon' style='background-color: " + innerColor + "'>" +
                        "<img src='/assets/soal.png' alt='icon ujian'>" +
                        "</div>" +
                        "<div class='ujian-next-wrapper'>" +
                        "<div id='matkul-next'>" + dataKuis[i].id_matkul.mataKuliah + "</div>" +
                        "<div id='batas-next'>" + batas + "</div>" +
                        "</div>" +
                        "</div>"
                }
            }
        })

</script>