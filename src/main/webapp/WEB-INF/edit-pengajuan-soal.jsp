<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Ujian - Aplikasi CAT</title>
        <link rel="stylesheet" href="style.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
        <link rel="icon" type="image/x-icon" href="favicon.ico">
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
            <form class="form-buat" action="/edit-pengajuan-soal" method="post">
                <h1>Edit Ujian</h1>
                <select name="matkul" required>
                	<option value="" selected disabled>-- Mata Kuliah --</option>
                	${matkul}
                </select>
                <select name="jenis-ujian" required>
                	<option value="" selected disabled>-- Jenis Ujian --</option>
                	<option value="0">Ujian Tengah Semester</option>
                	<option value="1">Ujian Akhir Semester</option>
                </select>
                <input type="number" value="<%=request.getAttribute("jumlahSoal") %>" name="jumlah-soal" id="jumlah-soal" placeholder="Jumlah Soal" disabled required/>
                <input type="number" name="waktu-pengerjaan" value=${waktuPengerjaan} id="waktu-pengerjaan" placeholder="Waktu Pengerjaan (menit)" required/>
                <textarea name="peraturan" placeholder="Peraturan">${peraturan}</textarea>
               	<button class="btn plus" type="submit"><span class="material-symbols-outlined">edit</span></button>
            </form>
        </main>



        <script>
            let select = document.querySelectorAll("select");
            select[0].value = ${idMatkul};
            select[1].value = ${jenisUjian};
        </script>
        <script src="./script.js"></script>
    </body>
</html>
