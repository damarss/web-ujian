<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rekap - Aplikasi CAT</title>
        <link rel="stylesheet" href="style.css"/>
        <link rel="icon" type="image/x-icon" href="favicon.ico">
    </head>
    <body>
        <jsp:include page="./components/sidebar.jsp" />
        <main>
            <div class="main-container">
                <h1>Rekap</h1>
                <h2>Hasil Ujian</h2>
                <table id="tbl-ujian">
                    <tr>
                        <th>No</th>
                        <th>Mata Kuliah</th>
                        <th>Jenis Ujian</th>
                        <th>Action</th>
                    </tr>
                </table>
                <h2>Hasil Kuis</h2>
                <table id="tbl-kuis">
                    <tr>
                        <th>No</th>
                        <th>Mata Kuliah</th>
                        <th>Nama Kuis</th>
                        <th>Action</th>
                    </tr>
                </table>
                <div id="rekap-mhs">
                    <h1>Rekap Mahasiswa</h1>
                    <button id="rekap-close" class="btn" onclick="hideRekap()">x</button>
                    <table id="tbl-rekap"></table>
                </div>
            </div>
        </main>
        
        <script>
            document.getElementById('rekap').classList.add('active');
            var rekap;
            var tabelUjian = document.getElementById("tbl-ujian");
            var tabelKuis = document.getElementById("tbl-kuis");
            var tabelRekap = document.getElementById("tbl-rekap");
            var rekapMahasiswa = document.getElementById("rekap-mhs")
            var dataUjian;
            var dataKuis;

            function showRekap(idUjian, idKuis) {
                rekapMahasiswa.style.display = "block";
                document.querySelector("body").style.overflow = "hidden";
                loadRekap(idUjian, idKuis);
            }

            function hideRekap() {
                rekapMahasiswa.style.display = "none";
                document.querySelector("body").style.overflow = "auto";
            }

            function loadUjian() {
                fetch("http://localhost:8080/get-ujian")
                    .then(async (response) => {
                        return response.json();
                    })
                    .then((myJson) => {
                        dataUjian = myJson
                        let no = 0;
                        for (i in dataUjian) {
                            tabelUjian.innerHTML += "<tr>" +
                                "<td>" + ++no + "</td>" +
                                "<td>" + dataUjian[i]['id_matkul']['mataKuliah'] + "</td>" +
                                "<td>" + dataUjian[i]['jenisUjian'] + "</td>" +
                                "<td><button class='btn' onclick='showRekap(" + dataUjian[i].id +")'>Lihat</button></td>" +
                                "</tr>"
                        }
                    })
            }
            loadUjian();

            function loadKuis() {
                fetch("http://localhost:8080/get-kuis")
                    .then(async (response) => {
                        return response.json();
                    })
                    .then((myJson) => {
                        dataKuis = myJson
                        let no = 0;
                        for (i in dataKuis) {
                            let date = new Date(dataKuis[i]['batasWaktu']);

                            tabelKuis.innerHTML += "<tr>" +
                                "<td>" + ++no + "</td>" +
                                "<td>" + dataKuis[i]['id_matkul']['mataKuliah'] + "</td>" +
                                "<td>" + dataKuis[i]['namaKuis'] + "</td>" +
                                "<td><button class='btn' onclick='showRekap(0," + dataKuis[i].id +")'>Lihat</button></td>" +
                                "</tr>"
                        }
                    })
                    .catch((ex) => {
                        console.log(ex);
                    })
            }
            loadKuis();

            function loadRekap(idUjian = 0, idKuis = 0) {
                const url = "http://localhost:8080/<%=request.getAttribute("url") %>"
                fetch(url)
                    .then((response) => {
                        return response.json();
                    })
                    .then((myJSON) => {
                        rekap = myJSON;
                        tabelRekap.innerHTML = "";
                        tabelRekap.innerHTML += "<tr>" +
                            "<th>No</th>" +
                            "<th>Nama Mahasiswa</th>" +
                            "<th>Jawaban Benar</th>" +
                            "<th>Jawaban Salah</th>" +
                            "<th>Nilai</th>" +
                            "</tr>";
                        let no = 0;
                        if (idUjian != 0) {
                            for (i in rekap) {
                                if (rekap[i].id_ujian != null && rekap[i].id_ujian.id == idUjian) {
                                    tabelRekap.innerHTML += "<tr>" +
                                        "<td>" + (++no) + "</td>" +
                                        "<td>" + rekap[i].id_user.namaLengkap + "</td>" +
                                        "<td>" + rekap[i].jawabanBenar + "</td>" +
                                        "<td>" + rekap[i].jawabanSalah + "</td>" +
                                        "<td>" + rekap[i].nilai.toFixed(2) + "</td>" +
                                    "</tr>";
                                }
                            }
                        } else {
                            for (i in rekap) {
                                if (rekap[i].id_kuis != null && rekap[i].id_kuis.id == idKuis) {
                                    tabelRekap.innerHTML += "<tr>" +
                                        "<td>" + (++no) + "</td>" +
                                        "<td>" + rekap[i].id_user.namaLengkap + "</td>" +
                                        "<td>" + rekap[i].jawabanBenar + "</td>" +
                                        "<td>" + rekap[i].jawabanSalah + "</td>" +
                                        "<td>" + rekap[i].nilai.toFixed(2) + "</td>" +
                                        "</tr>";
                                }
                            }
                        }
                    })
            }
            loadRekap();
        </script>
    </body>
</html>
