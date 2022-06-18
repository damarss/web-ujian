<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pengajuan Soal - Aplikasi CAT</title>
        <link rel="stylesheet" href="style.css"/>
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
            <form class="form-buat" action="/pengajuan-soal" method="post">
                <h1>Pengajuan Soal</h1>
                <select name="matkul" required>
                	<option value="" selected disabled>-- Mata Kuliah --</option>
                	${matkul}
                </select>
                <select name="jenis-ujian" required>
                	<option value="" selected disabled>-- Jenis Ujian --</option>
                	<option value="0">Ujian Tengah Semester</option>
                	<option value="1">Ujian Akhir Semester</option>
                </select>
                <input type="number" name="jumlah-soal" id="jumlah-soal" placeholder="Jumlah Soal" required/>
                <input type="number" name="waktu-pengerjaan" id="waktu-pengerjaan" placeholder="Waktu Pengerjaan (menit)" required/>
                <textarea name="peraturan" placeholder="Peraturan"></textarea>
               	<button class="btn plus" type="submit">+</button>
            </form>
        </main>
        
        <script src="./script.js"></script>
    </body>
</html>
