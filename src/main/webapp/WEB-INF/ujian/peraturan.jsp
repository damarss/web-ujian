<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Peraturan Ujian</title>
	<link rel="stylesheet" href="/style.css">
	<style>
		body {
		    display: grid;
		    grid-template-areas: "main";
		    grid-template-columns: 1fr;
		}
		
	    main {
		    background-color: #F8F8F8;
		    display: grid;
		    grid-template-areas: 
		    "identitas-ujian"
		    "peraturan-ujian";
		    grid-template-rows: auto 1fr;
		    grid-template-columns: 1fr;
		    padding: 10px;
		    gap: 15px;
		}
	</style>
</head>
<body>
	<main>
        <div class="identitas-ujian">
        <table class="identitas">
        	<tr>
        		<th>Mata Kuliah</th>
        		<td id="mata-kuliah"></td>
        	</tr>
        	<tr>
        		<th>Dosen</th>
        		<td id="nama-dosen"></td>
        	</tr>
        	<tr>
        		<th>Tanggal</th>
        		<td id="tanggal"></td>
        	</tr>
        	<tr>
        		<th>Waktu Pengerjaan</th>
        		<td id="waktu"></td>
    		</tr>
			<tr>
				<th>Jumlah Soal</th>
				<td id="jumlah-soal"></td>
			</tr>
        </table>
        </div>
        <div class="peraturan-ujian">
			<div class="peraturan-text">
				<h2>Peraturan</h2>
				<p id="peraturan"></p>
			</div>
			<form action="/ujian/peraturan" method="POST">
				<button class="btn peraturan" href="/ujian/soal">Setuju</button>
			</form>
        </div>
    </main>

	<script>
		let currentUjian = null;
		let peraturan = document.getElementById("peraturan");
		let mataKuliah = document.getElementById("mata-kuliah");
		let dosen = document.getElementById("nama-dosen");
		let tanggal = document.getElementById("tanggal");
		let waktu = document.getElementById("waktu");
		let jumlahSoal = document.getElementById("jumlah-soal");

		fetch("https://web-ujian.herokuapp.com/get-current-ujian?id_ujian=<%=session.getAttribute("idUjian") %>")
			.then(async (response) => {
				return response.json();
			})
			.then((myJSON) => {
				currentUjian = myJSON;

				let tanggalBaru = new Date(currentUjian.tanggalPelaksanaan);
				tanggalBaru = tanggalBaru.toLocaleDateString('id-id', {weekday:'long', day:'2-digit',
					month:'long', year:'numeric'})

				mataKuliah.innerText = currentUjian.id_matkul.mataKuliah;
				dosen.innerText = currentUjian.namaDosen;
				tanggal.innerText = tanggalBaru;
				waktu.innerText = currentUjian.waktuPengerjaan + " menit";
				peraturan.innerText = currentUjian.peraturan;
				jumlahSoal.innerText = currentUjian.jumlahSoal;
			})
			.catch((ex) => {
				console.log(ex)
			})
	</script>
</body>
</html>