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

		.identitas-ujian {
			padding: 15px;
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
        		<th>Batas Pengerjaan</th>
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
			<tr>
				<th>Nama Kuis</th>
				<td id="nama-kuis"></td>
			</tr>
        </table>
        </div>
        <div class="peraturan-ujian">
			<div class="peraturan-text">
				<h2>Peraturan</h2>
				<p id="peraturan"></p>
			</div>
			<form action="/kuis/peraturan" method="POST">
				<button class="btn peraturan" href="/ujian/soal">Setuju</button>
			</form>
        </div>
    </main>

	<script>
		let currentKuis = null;
		let peraturan = document.getElementById("peraturan");
		let mataKuliah = document.getElementById("mata-kuliah");
		let dosen = document.getElementById("nama-dosen");
		let tanggal = document.getElementById("tanggal");
		let waktu = document.getElementById("waktu");
		let jumlahSoal = document.getElementById("jumlah-soal");
		let namaKuis = document.getElementById("nama-kuis");

		fetch("http://localhost:8080/get-current-kuis?id_kuis=<%=session.getAttribute("idKuis") %>")
			.then(async (response) => {
				return response.json();
			})
			.then((myJSON) => {
				currentKuis = myJSON;

				let tanggalBaru = new Date(currentKuis.batasWaktu);
				tanggalBaru = tanggalBaru.toLocaleDateString('id-id', {weekday:'long', day:'2-digit',
					month:'long', year:'numeric', hour: '2-digit', minute: '2-digit'})

				mataKuliah.innerText = currentKuis.id_matkul.mataKuliah;
				dosen.innerText = currentKuis.namaDosen;
				tanggal.innerText = tanggalBaru;
				waktu.innerText = currentKuis.waktuPengerjaan + " menit";
				peraturan.innerText = currentKuis.peraturan;
				jumlahSoal.innerText = currentKuis.jumlahSoal;
				namaKuis.innerText = currentKuis.namaKuis;
			})
			.catch((ex) => {
				console.log(ex)
			})
	</script>
</body>
</html>