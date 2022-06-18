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
                        <th>Dosen</th>
                        <th>Jenis Ujian</th>
                        <th>Nilai</th>
                    </tr>
                </table>
                <h2>Hasil Kuis</h2>
                <table id="tbl-kuis">
                    <tr>
                        <th>No</th>
                        <th>Mata Kuliah</th>
                        <th>Dosen</th>
                        <th>Nama Kuis</th>
                        <th>Nilai</th>
                    </tr>
                </table>
            </div>
        </main>
        
        <script>
            document.getElementById('rekap').classList.add('active');
            var rekap;
            var tabelUjian = document.getElementById("tbl-ujian");
            var tabelKuis = document.getElementById("tbl-kuis");

            function loadRekap() {
                const url = "https://web-ujian.herokuapp.com/<%=request.getAttribute("url") %>"
                fetch(url)
                    .then((response) => {
                        return response.json();
                    })
                    .then((myJSON) => {
                        rekap = myJSON;
                        let noUjian = 0;
                        let noKuis = 0;
                        for (i in rekap) {
                            if (rekap[i].id_kuis == null) {
                                tabelUjian.innerHTML += "<tr>" +
                                    "<td>" + (++noUjian) + "</td>" +
                                    "<td>" + rekap[i].id_ujian.id_matkul.mataKuliah +"</td>" +
                                    "<td>" + rekap[i].id_ujian.namaDosen + "</td>" +
                                    "<td>" + rekap[i].id_ujian.jenisUjian + "</td>" +
                                    "<td>" + rekap[i].nilai.toFixed(2) + "</td>" +
                                    "</tr>"
                            } else {
                                tabelKuis.innerHTML += "<tr>" +
                                    "<td>" + (++noKuis) + "</td>" +
                                    "<td>" + rekap[i].id_kuis.id_matkul.mataKuliah + "</td>" +
                                    "<td>" + rekap[i].id_kuis.namaDosen + "</td>" +
                                    "<td>" + rekap[i].id_kuis.namaKuis + "</td>" +
                                    "<td>" + rekap[i].nilai.toFixed(2) + "</td>" +
                                    "</tr>"
                            }
                        }
                        noUjian = 0;
                        noKuis = 0;
                    })
            }
            loadRekap();
        </script>
    </body>
</html>
