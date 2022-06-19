<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Soal - Aplikasi CAT</title>
        <link rel="stylesheet" href="../style.css"/>
        <link rel="icon" type="image/x-icon" href="../favicon.ico">
        <style>
            body {
                grid-template-areas: "main";
                grid-template-columns: 1fr;
            }
            
            .sidebar {
                display: none;
            }
            
            main {
                display: flex;
                justify-content: center;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <main>
            <form class="form-buat-soal" id="soal"  action="/edit-soal" method="post">

            </form>
        </main>
        
        <script src="./script.js"></script>
        <script>
            var soal = document.getElementById("soal");
            var currentSoal = null;
            const url = "https://web-ujian.herokuapp.com/<%=request.getAttribute("url") %>" ;
            fetch(url)
                .then((response) => {
                    return response.json();
                })
                .then((data) => {
                    currentSoal = data;
                    for (i in data) {
                        soal.innerHTML += "<div class='buat-soal'>" +
                            "<div class='nomor-soal'>" +
                                "<h1>Soal <span class='number'>" + data[i].nomorSoal + "</span></h1>" +
                            "</div>" +
                            "<textarea name='soal-" + data[i].nomorSoal + "' placeholder='Masukkan soal' required>" + data[i].soal + "</textarea>" +
                            "<div class='option'>" +
                                "<input type='radio' name='answer-" + data[i].nomorSoal + "' value='1'>" +
                                "<input type='text' name='option1-" + data[i].nomorSoal + "' placeholder='Opsi 1' value='" + data[i].option1 + "' required>" +
                            "</div>" +
                            "<div class='option'>" +
                            "<input type='radio' name='answer-" + data[i].nomorSoal + "' value='2'>" +
                            "<input type='text' name='option2-" + data[i].nomorSoal + "' placeholder='Opsi 2' value='" + data[i].option2 + "' required>" +
                            "</div>" +
                            "<div class='option'>" +
                            "<input type='radio' name='answer-" + data[i].nomorSoal + "' value='3'>" +
                            "<input type='text' name='option3-" + data[i].nomorSoal + "' placeholder='Opsi 3' value='" + data[i].option3 + "' required>" +
                            "</div>" +
                            "<div class='option'>" +
                            "<input type='radio' name='answer-" + data[i].nomorSoal + "' value='4'>" +
                            "<input type='text' name='option4-" + data[i].nomorSoal + "' placeholder='Opsi 4' value='" + data[i].option4 + "' required>" +
                            "</div>" +
                            "<div class='option'>" +
                            "<input type='radio' name='answer-" + data[i].nomorSoal + "' value='5'>" +
                            "<input type='text' name='option5-" + data[i].nomorSoal + "' placeholder='Opsi 5' value='" + data[i].option5 + "' required>" +
                            "</div>" +
                            "</div>"
                    }
                    soal.innerHTML += "<button class='btn' type='submit' style='max-width: 500px; height: 50px;'>Ajukan/Buat Soal</button>"
                })

            var jawaban;
            const url2 = "https://web-ujian.herokuapp.com/<%=request.getAttribute("url2") %>";
            fetch(url2)
                .then(async (response) => {
                    return response.json();
                })
                .then((data) => {
                    jawaban = data;
                    let option = document.querySelectorAll("[type = 'radio']");
                    let marker = 0;
                    for (i in jawaban) {
                        option[marker+jawaban[i].jawaban-1].checked = true;
                        marker += 5;
                    }
                })
        </script>
    </body>
</html>
