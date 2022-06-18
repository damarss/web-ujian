<h1>Ujian</h1>
${ujian}
<div id="next-ujian">
    <h2>Ujian Selanjutnya</h2>
    <div class='wrapper-next' id="wrapper-next"></div>
</div>

<script>
    var dataUjian;
    var idt = document.getElementById("idt");
    var wrapper = document.querySelector(".wrapper");
    var jenis = document.getElementById("jenis");
    var matkul = document.getElementById("matkul");
    var dosen = document.getElementById("dosen");
    var jumlahSoal = document.getElementById("jml-soal");
    var menit = document.getElementById("menit");
    var batasWaktu = document.getElementById("batas");
    var nextUjian = document.getElementById("next-ujian");
    var wrapperNextUjian = document.getElementById("wrapper-next");
    var matkulNext = document.getElementById("matkul-next");
    var batasNext = document.getElementById("batas-next");

    fetch("web-ujian.herokuapp.com/get-ujian-mahasiswa")
        .then(async (response) => {
            return response.json();
        })
        .then((myJson) => {
            dataUjian = myJson
            let batas = new Date(dataUjian[0].tanggalPelaksanaan);
            batas = batas.toLocaleDateString('id-id', {weekday:'long', day:'2-digit',
                month:'short', year:'numeric', hour:'2-digit', minute:'2-digit'})
            batasWaktu.innerText += "Batas: " + batas;
            jenis.innerText = dataUjian[0].jenisUjian == "UTS" ? "Ujian Tengah Semester" : "Ujian Akhir Semester";
            matkul.innerText = dataUjian[0].id_matkul.mataKuliah;
            dosen.innerHTML = "<img src='/assets/avatar.png' alt='avatar'>" + dataUjian[0].namaDosen;
            jumlahSoal.innerText = dataUjian[0].jumlahSoal + " Soal";
            menit.innerText = dataUjian[0].waktuPengerjaan + " Menit";
            wrapper.innerHTML += "<div class='cur-ujian-mulai'><a class='btn' href='/ujian/peraturan/" + dataUjian[0].id + "' >Mulai Ujian</a></div>";

            if (dataUjian.length > 1) {
                let color;
                let innerColor;
                nextUjian.style.display = "block";
                for (i = 1; i < dataUjian.length; i++) {
                    if (i%3 == 1) {
                        color = "var(--color1)";
                        innerColor = "var(--color1-inner)";
                    } else if (i%3 == 2) {
                        color = "var(--color2)";
                        innerColor = "var(--color2-inner)";
                    } else {
                        color = "var(--color3)";
                        innerColor = "var(--color3-inner)";
                    }

                    batas = new Date(dataUjian[i].tanggalPelaksanaan);
                    batas = batas.toLocaleDateString('id-id', {weekday:'long', day:'2-digit',
                        month:'long', year:'numeric'})

                    wrapperNextUjian.innerHTML += "<div class='ujian-next' style='background-color: " + color +"'>" +
                        "<div class='icon' style='background-color: " + innerColor + "'>" +
                            "<img src='/assets/soal.png' alt='icon ujian'>" +
                        "</div>" +
                        "<div class='ujian-next-wrapper'>" +
                            "<div id='matkul-next'>" + dataUjian[i].id_matkul.mataKuliah +"</div>" +
                            "<div id='batas-next'>" + batas + "</div>" +
                        "</div>" +
                    "</div>"
                }
            }
        })

</script>