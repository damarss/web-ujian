<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Rekap Ujian</title>
	<link rel="stylesheet" href="../style.css">
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
		    "hasil nilai-txt"
		    "hasil nilai"
			"hasil back";
		    grid-template-rows: 80px 350px auto;
		    grid-template-columns: 1fr 500px;
		    padding: 10px;
			gap: 15px;
		}

		.hasil {
			grid-area: hasil;
			padding: 10px;
			height: 100%;
		}

		.hasil h1 {
			font-size: 48px;
			margin-bottom: 50px;
		}

		.hasil .wrapper {
			display: flex;
			justify-content: center;
			align-items: center;
		}

		.hasil, .nilai-txt, #nilai {
			background-color: #fff;
			border-radius: 10px;
		}

		.nilai-txt, #nilai, .back {
			display: flex;
			justify-content: center;
			align-items: center;
		}

		.nilai-txt {
			grid-area: nilai-txt;
			font-size: 36px;
			font-weight: 600;
		}

		#nilai {
			grid-area: nilai;
			font-size: 96px;
			font-weight: 600;
		}

		.back {
			grid-area: back;
		}

		table {
			width: 100%;
		}

		table th {
			width: 250px;
		}

		table td {
			width: 350px;
		}
	</style>
</head>
<body>
	<main>
		<div class="hasil">
			<h1>Hasil ujian</h1>
			<table>
				<tr>
					<th>Soal Terjawab</th>
					<td id="terjawab">${terjawab}</td>
				</tr>
				<tr>
					<th>Soal Tidak Terjawab</th>
					<td id="tidak-terjawab">${tidakTerjawab}</td>
				</tr>
				<tr>
					<th>Jawaban Benar</th>
					<td id="benar">${benar}</td>
				</tr>
				<tr>
					<th>Jawaban Salah</th>
					<td id="salah">${salah}</td>
				</tr>
				<tr>
					<th>Jumlah Soal</th>
					<td id="jumlah-soal">${jumlahSoal}</td>
				</tr>
			</table>
		</div>
		<div class="nilai-txt">Nilai</div>
		<div id="nilai">${nilai}</div>
		<div class="back">
			<form action="/ujian/rekap" method="POST">
				<button class="btn">Kembali</button>
			</form>
		</div>
    </main>
</body>
</html>