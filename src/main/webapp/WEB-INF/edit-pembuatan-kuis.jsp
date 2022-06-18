<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Kuis- Aplikasi CAT</title>
        <link rel="stylesheet" href="style.css"/>
        <link rel="icon" type="image/x-icon" href="favicon.ico">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />

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
            <form class="form-buat" action="/edit-pembuatan-kuis" method="post">
                <h1>Edit Kuis</h1>
                <select name="matkul" required>
                	<option value="" selected disabled>-- Mata Kuliah --</option>
                	${matkul}
                </select>
                <input type="text" value="<%=request.getAttribute("namaKuis") %>" name="nama-kuis" id="nama-kuis" placeholder="Nama Kuis" required/>
                <input type="number" value=${jumlahSoal} name="jumlah-soal" id="jumlah-soal" placeholder="Jumlah Soal" disabled required/>
                <input type="number" value="${waktuPengerjaan}" name="waktu-pengerjaan" id="waktu-pengerjaan" placeholder="Waktu Pengerjaan (menit)" required/>
                <input class="datetime" type="datetime-local" name="batas-waktu" id="batas-waktu" placeholder="Batas Waktu Pengerjaan" required/>
                <textarea name="peraturan" placeholder="Peraturan">${peraturan}</textarea>
               	<button class="btn plus" type="submit"><span class="material-symbols-outlined">edit</span></button>
            </form>
        </main>
        
        <script src="./script.js"></script>
        <script>
            let select = document.querySelector('select');
            select.value = ${idMatkul}
            let batasWaktu = document.getElementById("batas-waktu");
            let tanggal = new Date("<%=request.getAttribute("batasWaktu")%>");
            let jam = (tanggal.getHours() < 10) ? "0" + tanggal.getHours(): tanggal.getHours();
            let menit = (tanggal.getMinutes() < 10) ? "0" + tanggal.getMinutes(): tanggal.getMinutes();
            tanggal = tanggal.getFullYear() + "-" + ("0" + (tanggal.getMonth() + 1)).slice(-2) + "-" + ("0" + (tanggal.getDate() )).slice(-2) + "T" + jam + ":" + menit;
            batasWaktu.value = tanggal;
        </script>
    </body>
</html>
