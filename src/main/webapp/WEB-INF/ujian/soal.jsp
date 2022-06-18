<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Soal Ujian</title>
	<link rel="stylesheet" href="../style.css"/>
	<link rel="stylesheet" href="../soal.css"/>
	<style>
		body {
		    display: grid;
		    grid-template-areas: "main";
		    grid-template-columns: 1fr;
		}
	</style>
	<script type = "text/javascript">
		history.pushState(null, null, location.href);
		window.onpopstate = function () {
			history.go(1);
		};
	</script>
</head>
<body>
	<main>
		<div class="soal-container">
			<div class="nomor-soal">
				<h1>Soal <span class="number" id="number"></span></h1>
			</div>
			<div class="soal">
				<span id="soal-text"></span>
			<div class="option-wrapper">
				<div class="option">
					<input type="radio" value="1" name="option" id="option1">
					<label id="lbl-option1" for="option1"></label>
				</div>
				<div class="option">
					<input type="radio" value="2" name="option" id="option2">
					<label id="lbl-option2" for="option2"></label>
				</div>
				<div class="option">
					<input type="radio" value="3" name="option" id="option3">
					<label id="lbl-option3" for="option3"></label>
				</div>
				<div class="option">
					<input type="radio" value="4" name="option" id="option4">
					<label id="lbl-option4" for="option4"></label>
				</div>
				<div class="option">
					<input type="radio" value="5" name="option" id="option5">
					<label id="lbl-option5" for="option5"></label>
				</div>
				
			</div>
		</div>
		<div class="navigation">
			<div class="btn prev" onclick="prev()">&lt; Soal Sebelumnya</div>
			<div class="btn next" onclick="next()">Soal Selanjutnya &gt;</div>
		</div>
		</div>
		<div class="label-no">
			Nomor Soal
		</div>
		<div class="nomor" id="nomor"></div>
		<div class="submit">
			<div id="time"></div>
			<form action="/ujian/soal" method="POST">
				<button id="akhiri" type="submit" class="btn cons">Akhiri Ujian</button>
			</form>
		</div>
	</main>

	<script>
		var nomor = 1;
		var currentSoal = null;
		var number = document.getElementById("number");
		var textSoal = document.getElementById("soal-text");
		var option1 = document.getElementById("lbl-option1");
		var option2 = document.getElementById("lbl-option2");
		var option3 = document.getElementById("lbl-option3");
		var option4 = document.getElementById("lbl-option4");
		var option5 = document.getElementById("lbl-option5");
		var display = document.querySelector('#time');
		var option = document.querySelectorAll("input");
		var first = true;
		var nomorContainer = document.getElementById("nomor");

		for (i = 0; i < option.length; i++) {
			option[i].addEventListener("click", function () {
				saveJawaban(this.value);
			})
		}

		window.onbeforeunload = function () {
			return "nope";
		}

		function loadSoal() {
			number.innerText = nomor;
			const url = "https://web-ujian.herokuapp.com/get-soal-ujian?id_ujian=<%=session.getAttribute("idUjian") %>&nomor=" + nomor;
			fetch(url)
					.then(async (response) => {
						return response.json();
					})
					.then((myJSON) => {
						currentSoal = myJSON
						textSoal.innerText = currentSoal.soal;
						option1.innerText = currentSoal.option1;
						option2.innerText = currentSoal.option2;
						option3.innerText = currentSoal.option3;
						option4.innerText = currentSoal.option4;
						option5.innerText = currentSoal.option5;

						if (first) {
							startTimer(currentSoal.id_ujian.waktuPengerjaan * 60, display);
							setTimeout(function () {
								window.onbeforeunload = function () {};
								alert("Waktu pengerjaan berakhir");
								document.getElementById("akhiri").click();
							}, currentSoal.id_ujian.waktuPengerjaan * 60000)
							for (i = 1; i <= currentSoal.id_ujian.jumlahSoal; i++) {
								nomorContainer.innerHTML += "<div class='no' id='no-" + i +"'>" + i + "</div>";
							}
							first = false;
						} else {
							updateJawaban();
						}
					})
					.catch((ex) => {
						console.log(ex)
					})
		}

		function updateJawaban() {
			fetch("https://web-ujian.herokuapp.com/get-jawaban-user?id_soal=" + currentSoal.id)
					.then((response) => {
						return response.json();
					})
					.then((myJSON) => {
						for (i = 0; i < option.length; i++) {
							if (myJSON.jawabanUser == 0) {
								option[i].checked = false;
							} else {
								option[myJSON.jawabanUser - 1].checked = true;
								document.getElementById("no-" + myJSON.id_soal.nomorSoal).classList.add("answered");
							}
						}
					})
		}

		loadSoal();

		function saveJawaban(jawaban) {
			fetch("https://web-ujian.herokuapp.com/save-jawaban-ujian", {
				method: "POST",
				body: JSON.stringify({
					jawabanUser: jawaban,
					id_soal: currentSoal,
					id_user: null,
				}),
				headers: {
					"Content-type": "application/json; charset=UTF-8"
				}
			}).then(function () {
				updateJawaban();
			})
		}

		function prev() {
			if (nomor <= 1) {
				return;

			}
			nomor--
			loadSoal();
		}

		function next() {
			if (currentSoal.id_kuis != null) {
				if (nomor >= currentSoal.id_kuis.jumlahSoal) {
					return;
				}
				nomor++;
			} else if (currentSoal.id_ujian != null) {
				if (nomor >= currentSoal.id_ujian.jumlahSoal) {
					return;
				}
				nomor++;
			}
			loadSoal();
		}

		function akhiriUjian() {
			document.getElementById("akhiri").click();
		}

		function startTimer(duration, display) {
			var start = Date.now(),
					diff,
					minutes,
					seconds;
			function timer() {
				// get the number of seconds that have elapsed since
				// startTimer() was called
				diff = duration - (((Date.now() - start) / 1000) | 0);

				// does the same job as parseInt truncates the float
				minutes = (diff / 60) | 0;
				seconds = (diff % 60) | 0;

				minutes = minutes < 10 ? "0" + minutes : minutes;
				seconds = seconds < 10 ? "0" + seconds : seconds;

				display.textContent = minutes + ":" + seconds;

				if (diff <= 0) {
					// add one second so that the count down starts at the full duration
					// example 05:00 not 04:59
					start = Date.now() + 1000;
				}
			};
			// we don't want to wait a full second before the timer starts
			timer();
			setInterval(timer, 1000);
		}

	</script>
</body>
</html>