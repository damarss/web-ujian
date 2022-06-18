package com.aplikasi.cat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.aplikasi.cat.dto.*;
import com.aplikasi.cat.enums.JenisUjian;
import com.aplikasi.cat.enums.StatusUjian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.aplikasi.cat.service.WebService;

@Controller
public class UjianController {
	@Autowired
	WebService webService;
	
	@GetMapping(value = "/ujian")
	public String ujian(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		
		User user = (User) session.getAttribute("user");
		if (user.getRole() == 1) {
			model.addAttribute("main", "./components/ujian-operator.jsp");
			model.addAttribute("navigation", "./navigation-operator.jsp");
		} else if (user.getRole() == 2) {
			model.addAttribute("main", "./components/ujian-dosen.jsp");
			model.addAttribute("navigation", "./navigation.jsp");
		} else {
			model.addAttribute("main", "./components/ujian-mahasiswa.jsp");
			model.addAttribute("navigation", "./navigation.jsp");
			List<Ujian> daftarUjian = webService.getUjianMahasiswa();
			if (daftarUjian.size() == 0) {
				String tidakTersedia = "<div class='tidak-tersedia'>"
						+ "<div class='image'><img src='assets/ujian.png' alt='Ujian tidak tersedia'></div>"
						+ "<div class='content'>Tidak ada ujian tersedia</div>"
						+ "</div>";
				model.addAttribute("ujian", tidakTersedia);
			} else {
				String tersedia = "<div id=\"cur-ujian\" class=\"cur-ujian\">\n" +
						"    <div class=\"wrapper\">\n" +
						"        <div class=\"cur-ujian-jenis\" id=\"jenis\"></div>\n" +
						"        <div class=\"cur-ujian-matkul\" id=\"matkul\"></div>\n" +
						"        <div class=\"cur-ujian-dosen\" id=\"dosen\"></div>\n" +
						"        <div class=\"cur-ujian-idt\" id=\"idt\">\n" +
						"            <img src='/assets/soal.png'>\n" +
						"            <div>\n" +
						"                <span id=\"jml-soal\"></span> | <span id=\"menit\"></span> | <span id=\"batas\"></span>\n" +
						"            </div>\n" +
						"        </div>\n" +
						"    </div>\n" +
						"    <div class=\"cur-ujian-image\">\n" +
						"        <img src=\"/assets/soal-ujian-1.jpg\" alt=\"Ujian\">\n" +
						"    </div>\n" +
						"</div>";
				model.addAttribute("ujian", tersedia);
			}
		}
		
		return "ujian";
	}
	
	@GetMapping(value = "pengajuan-soal")
	public String pengajuanSoal(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("navigation", "./navigation-operator.jsp");
		
		if (user.getRole() != 2) {
			return "redirect:/";
		}
		
		List<MataKuliah> listMatkul = webService.findMatkul();
		String matkul = "";
		for (MataKuliah mk: listMatkul) {
			matkul += "<option value=\"" + mk.getId() + "\">" + mk.getMataKuliah() +"</option>";
		}
		
		model.addAttribute("matkul", matkul);
		
		return "pengajuan-soal";
	}
	
	@PostMapping(value = "pengajuan-soal")
	public String pengajuanSoal(@RequestParam Map<String, Object> params, HttpSession session) {
		try {
			Long idMatkul = Long.parseLong(params.get("matkul").toString());
			JenisUjian jenisUjian = JenisUjian.values()[Integer.parseInt(params.get("jenis-ujian").toString())];
			int jumlahSoal = Integer.parseInt(params.get("jumlah-soal").toString());
			int waktuPengerjaan = Integer.parseInt(params.get("waktu-pengerjaan").toString());
			String peraturan = params.get("peraturan").toString();
			MataKuliah mataKuliah = webService.findMatkul(idMatkul);

			User user = (User) session.getAttribute("user");

			if (user.getRole() != 2) {
				return "redirect:/";
			}

			Ujian newUjian = Ujian.builder()
					.id_matkul(mataKuliah)
					.jenisUjian(jenisUjian)
					.jumlahSoal(jumlahSoal)
					.waktuPengerjaan(waktuPengerjaan)
					.namaDosen(user.getNamaLengkap())
					.peraturan(peraturan)
					.status(StatusUjian.MENUNGGU)
					.build();

			System.out.println(newUjian);
			webService.buatUjian(newUjian);

			session.setAttribute("id_ujian", newUjian.getId());
			session.setAttribute("jumlahSoal", jumlahSoal);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return "redirect:/buat-soal";
	}
	
	@GetMapping(value = "/buat-soal")
	public String buatKuis(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("navigation", "./navigation-operator.jsp");
		
		if (user.getRole() != 2) {
			return "redirect:/";
		}

		try {
			int jumlahSoal = Integer.parseInt(session.getAttribute("jumlahSoal").toString());

			String buatSoal = "";

			for (int i = 1; i <= jumlahSoal; i++) {
				buatSoal += "<div class=\"buat-soal\">"
						+ "<div class=\"nomor-soal\">\r\n"
						+ "		<h1>Soal <span class=\"number\">"+ i +"</span></h1>\r\n"
						+ "</div>"
						+ "<textarea name=\"soal-" + i + "\" placeholder=\"Masukkan soal\" required></textarea>"
						+ "<div class=\"option\">\r\n"
						+ "     <input type=\"radio\" name=\"answer-" + i + "\" value=\"1\">\r\n"
						+ "     <input type=\"text\" name=\"option1-" + i + "\" placeholder=\"Opsi 1\" required>\r\n"
						+ "</div>"
						+ "<div class=\"option\">\r\n"
						+ "     <input type=\"radio\" name=\"answer-" + i + "\" value=\"2\">\r\n"
						+ "     <input type=\"text\" name=\"option2-" + i + "\" placeholder=\"Opsi 2\" required>\r\n"
						+ "</div>"
						+ "<div class=\"option\">\r\n"
						+ "     <input type=\"radio\" name=\"answer-" + i + "\" value=\"3\">\r\n"
						+ "     <input type=\"text\" name=\"option3-" + i + "\" placeholder=\"Opsi 3\" required>\r\n"
						+ "</div>"
						+ "<div class=\"option\">\r\n"
						+ "     <input type=\"radio\" name=\"answer-" + i + "\" value=\"4\">\r\n"
						+ "     <input type=\"text\" name=\"option4-" + i + "\" placeholder=\"Opsi 4\" required>\r\n"
						+ "</div>"
						+ "<div class=\"option\">\r\n"
						+ "     <input type=\"radio\" name=\"answer-" + i + "\" value=\"5\">\r\n"
						+ "     <input type=\"text\" name=\"option5-" + i + "\" placeholder=\"Opsi 5\" required>\r\n"
						+ "</div>"
						+ "</div>";
			}
			model.addAttribute("buatSoal", buatSoal);
		} catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
		}

		return "buat-soal";
	}

	@PostMapping(value = "buat-soal")
	public String buatSoal(@RequestParam Map<String, Object> params, HttpSession session) {
		try {
			Object idKuis = session.getAttribute("id_kuis");
			Object idUjian = session.getAttribute("id_ujian");
			System.out.println(idKuis);
			System.out.println(idUjian);
			int jumlahSoal = Integer.parseInt(session.getAttribute("jumlahSoal").toString());
			System.out.println(session.getAttribute("jumlahSoal"));
			if (idKuis != null) {
				Long id_kuis = Long.parseLong(idKuis.toString());
				Soal newSoal;
				Jawaban newJawaban;
				for (int i = 1; i <= jumlahSoal; i++) {
					newSoal = Soal.builder()
						.nomorSoal(i)
						.soal(params.get("soal-" + i).toString())
						.option1(params.get("option1-" + i).toString())
						.option2(params.get("option2-" + i).toString())
						.option3(params.get("option3-" + i).toString())
						.option4(params.get("option4-" + i).toString())
						.option5(params.get("option5-" + i).toString())
						.id_kuis(webService.findKuisById(id_kuis))
						.build();
					webService.buatSoal(newSoal);
					newJawaban = Jawaban.builder()
							.id_soal(newSoal)
							.jawaban(Integer.parseInt(params.get("answer-" + i).toString()))
							.build();
					webService.buatJawaban(newJawaban);
				}
			} else if (idUjian != null) {
				Long id_ujian = Long.parseLong(idUjian.toString());
				Soal newSoal;
				Jawaban newJawaban;
				for (int i = 1; i <= jumlahSoal; i++) {
					newSoal = Soal.builder()
							.nomorSoal(i)
							.soal(params.get("soal-" + i).toString())
							.option1(params.get("option1-" + i).toString())
							.option2(params.get("option2-" + i).toString())
							.option3(params.get("option3-" + i).toString())
							.option4(params.get("option4-" + i).toString())
							.option5(params.get("option5-" + i).toString())
							.id_ujian(webService.findUjian(id_ujian))
							.build();
					webService.buatSoal(newSoal);
					newJawaban = Jawaban.builder()
							.id_soal(newSoal)
							.jawaban(Integer.parseInt(params.get("answer-" + i).toString()))
							.build();
					webService.buatJawaban(newJawaban);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			System.out.println(session.getAttribute("jumlahSoal"));
			session.removeAttribute("id_ujian");
			session.removeAttribute("id_kuis");
			session.removeAttribute("jumlahSoal");
			System.out.println(session.getAttribute("jumlahSoal"));
		}
		return "redirect:/";
	}
	
	@GetMapping(value = "/ujian/peraturan/{id_ujian}")
	public String peraturanUjian(@PathVariable("id_ujian") Long idUjian, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}

		User user = (User) session.getAttribute("user");

		if (user.getRole() != 3) {
			return "redirect:/";
		}

		Rekap currentRekap = webService.getRekapUjian(user.getId(), idUjian);

		if (currentRekap != null) {
			return "redirect:/error-mulai";
		}

		session.setAttribute("idUjian", idUjian);
		return "ujian/peraturan";
	}

	@PostMapping(value = "/ujian/peraturan")
	public String setujuPeraturan(HttpSession session) {
		try {
			User user = (User) session.getAttribute("user");
			Long idUjian = Long.parseLong(session.getAttribute("idUjian").toString());

			List<Soal> soalList = webService.getAllSoalUjian(idUjian);
			JawabanUser jawabanUser;
			for (Soal soal: soalList) {
				jawabanUser = JawabanUser.builder()
						.id_soal(soal)
						.id_user(user)
						.jawabanUser(0)
						.build();
				webService.saveJawabanUser(jawabanUser);
			}

			Rekap newRekap = Rekap.builder()
					.id_user(user)
					.id_ujian(webService.findUjian(idUjian))
					.build();
			webService.createNewRekap(newRekap);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return "redirect:/ujian/soal";
	}
	
	@GetMapping(value = "/ujian/soal")
	public String soalUjian(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}

		User user = (User) session.getAttribute("user");

		if (user.getRole() != 3) {
			return "redirect:/";
		}

		return "ujian/soal";
	}

	@PostMapping(value = "/ujian/soal")
	public String akhiriUjian(HttpSession session) {
		try {
			User user = (User) session.getAttribute("user");
			Long idUjian = Long.parseLong(session.getAttribute("idUjian").toString());
			Ujian ujian = webService.findUjian(idUjian);
			webService.koreksiJawabanUjian(user, ujian);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return "redirect:/ujian/rekap";
	}

	@GetMapping(value = "/ujian/rekap")
	public String rekapUjian(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}

		User user = (User) session.getAttribute("user");
		Long idUjian = Long.parseLong(session.getAttribute("idUjian").toString());

		Rekap rekap = webService.getRekapUjian(user.getId(), idUjian);

		model.addAttribute("terjawab", rekap.getSoalTerjawab());
		model.addAttribute("tidakTerjawab", rekap.getSoalTidakTerjawab());
		model.addAttribute("benar", rekap.getJawabanBenar());
		model.addAttribute("salah", rekap.getJawabanSalah());
		model.addAttribute("jumlahSoal", rekap.getId_ujian().getJumlahSoal());
		model.addAttribute("nilai", String.format("%.2f", rekap.getNilai()));

		return "ujian/rekap";
	}

	@PostMapping(value = "/ujian/rekap")
	public String backRekapUjian(HttpSession session) {
		session.removeAttribute("idUjian");
		return "redirect:/";
	}

	@GetMapping(value = "/get-soal-ujian", produces = "application/json")
	public @ResponseBody Soal getSoalUjian(@RequestParam("id_ujian") Long idUjian, @RequestParam("nomor") int nomor) {
		return webService.getSoalUjian(idUjian, nomor);
	}

	@GetMapping(value = "/get-all-soal-ujian", produces = "application/json")
	public @ResponseBody List<Soal> getAllSoalUjian(@RequestParam("id_ujian") Long idUjian) {
		return webService.getAllSoalUjian(idUjian);
	}

	@GetMapping(value = "/get-ujian", produces = "application/json")
	public @ResponseBody List<Ujian> getUjian() {
		return webService.findUjian();
	}

	@GetMapping(value = "/get-current-ujian", produces = "application/json")
	public @ResponseBody Ujian getCurrentUjian(@RequestParam("id_ujian") Long idUjian) {
		return webService.findUjian(idUjian);
	}

	@GetMapping(value = "/get-ujian-mahasiswa")
	public @ResponseBody List<Ujian> getUjianMahasiswa() {
		return webService.getUjianMahasiswa();
	}

	@GetMapping(value = "/edit-pengajuan-soal")
	public String editUjian(@RequestParam("id_ujian") Long idUjian, HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}

		User user = (User) session.getAttribute("user");
		if (user.getRole() != 2) {
			return "redirect:/";
		}

		Ujian editUjian = webService.findUjian(idUjian);
		model.addAttribute("idMatkul", editUjian.getId_matkul().getId());
		int jenisUjian = (editUjian.getJenisUjian().toString() == "UTS" ? 0: 1);
		model.addAttribute("jenisUjian", jenisUjian);
		model.addAttribute("jumlahSoal", editUjian.getJumlahSoal());
		model.addAttribute("waktuPengerjaan", editUjian.getWaktuPengerjaan());
		model.addAttribute("peraturan", editUjian.getPeraturan());

		List<MataKuliah> listMatkul = webService.findMatkul();
		String matkul = "";
		for (MataKuliah mk: listMatkul) {
			matkul += "<option value=\"" + mk.getId() + "\">" + mk.getMataKuliah() +"</option>";
		}

		session.setAttribute("id_ujian", editUjian.getId());
		model.addAttribute("matkul", matkul);

		return "edit-pengajuan-soal";
	}

	@PostMapping(value = "/edit-pengajuan-soal")
	public String editPengajuanSoal(@RequestParam Map<String, Object> params, HttpSession session) {
		try {
			Long idUjian = Long.parseLong(session.getAttribute("id_ujian").toString());
			Long idMatkul = Long.parseLong(params.get("matkul").toString());
			JenisUjian jenisUjian = JenisUjian.values()[Integer.parseInt(params.get("jenis-ujian").toString())];
			int waktuPengerjaan = Integer.parseInt(params.get("waktu-pengerjaan").toString());
			String peraturan = params.get("peraturan").toString();
			MataKuliah matkul = webService.findMatkul(idMatkul);

			Ujian editUjian = webService.findUjian(idUjian);
			int jumlahSoal = editUjian.getJumlahSoal();
			editUjian.setId_matkul(matkul);
			editUjian.setJenisUjian(jenisUjian);
			editUjian.setPeraturan(peraturan);
			editUjian.setWaktuPengerjaan(waktuPengerjaan);
			editUjian.setStatus(StatusUjian.MENUNGGU);

			webService.editUjian(editUjian);
			session.setAttribute("jumlahSoal", jumlahSoal);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}


		return "redirect:/edit-soal";
	}

	@GetMapping(value = "/edit-soal")
	public String editSoal(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}

		Long id;
		if (session.getAttribute("id_ujian") != null) {
			id = Long.parseLong(session.getAttribute("id_ujian").toString());
			model.addAttribute("url", "get-all-soal-ujian?id_ujian=" + id);
			model.addAttribute("url2", "get-jawaban-ujian?id_ujian=" + id);
		} else if (session.getAttribute("id_kuis") != null) {
			id = Long.parseLong(session.getAttribute("id_kuis").toString());
			model.addAttribute("url", "get-all-soal-kuis?id_kuis=" + id);
			model.addAttribute("url2", "get-jawaban-kuis?id_kuis=" + id);
		}

		return "edit-soal";
	}

	@PostMapping(value = "edit-soal")
	public String editSoalJawaban(@RequestParam Map<String, Object> params, HttpSession session) {
		try {
			int jumlahSoal = Integer.parseInt(session.getAttribute("jumlahSoal").toString());

			if (session.getAttribute("id_kuis") != null) {
				Long idKuis = Long.parseLong(session.getAttribute("id_kuis").toString());
				List<Soal> listSoal = webService.getAllSoalKuis(idKuis);
				List<Jawaban> listJawaban = webService.getJawabanKuis(idKuis);
				Soal editSoal;
				Jawaban editJawaban;
				for (int i = 0; i < jumlahSoal; i++) {
					editSoal = listSoal.get(i);
					editJawaban = listJawaban.get(i);
					editSoal.setSoal(params.get("soal-" + (i+1)).toString());
					editSoal.setOption1(params.get("option1-" + (i+1)).toString());
					editSoal.setOption2(params.get("option2-" + (i+1)).toString());
					editSoal.setOption3(params.get("option3-" + (i+1)).toString());
					editSoal.setOption4(params.get("option4-" + (i+1)).toString());
					editSoal.setOption5(params.get("option5-" + (i+1)).toString());
					webService.editSoal(editSoal);

					editJawaban.setJawaban(Integer.parseInt(params.get("answer-" + (i+1)).toString()));
					webService.editJawaban(editJawaban);
				}
			} else if (session.getAttribute("id_ujian") != null) {
				Long idUjian = Long.parseLong(session.getAttribute("id_ujian").toString());
				List<Soal> listSoal = webService.getAllSoalUjian(idUjian);
				List<Jawaban> listJawaban = webService.getJawabanUjian(idUjian);
				Soal editSoal;
				Jawaban editJawaban;
				for (int i = 0; i < jumlahSoal; i++) {
					editSoal = listSoal.get(i);
					editJawaban = listJawaban.get(i);
					editSoal.setSoal(params.get("soal-" + (i+1)).toString());
					editSoal.setOption1(params.get("option1-" + (i+1)).toString());
					editSoal.setOption2(params.get("option2-" + (i+1)).toString());
					editSoal.setOption3(params.get("option3-" + (i+1)).toString());
					editSoal.setOption4(params.get("option4-" + (i+1)).toString());
					editSoal.setOption5(params.get("option5-" + (i+1)).toString());
					webService.editSoal(editSoal);

					editJawaban.setJawaban(Integer.parseInt(params.get("answer-" + (i+1)).toString()));
					webService.editJawaban(editJawaban);
				}
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		session.removeAttribute("id_ujian");
		session.removeAttribute("id_kuis");
		session.removeAttribute("jumlahSoal");
		return "redirect:/";
	}

	@GetMapping(value = "/hapus-ujian")
	public String hapusUjian(@RequestParam("id_ujian") Long idUjian, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}

		User user = (User) session.getAttribute("user");
		if (user.getRole() != 2) {
			return "redirect:/";
		}

		webService.hapusUjian(idUjian);
		return "redirect:/ujian";
	}

	@GetMapping(value = "get-jawaban-ujian", produces = "application/json")
	public @ResponseBody List<Jawaban> getJawabanUjian(@RequestParam("id_ujian") Long idUjian) {
		return webService.getJawabanUjian(idUjian);
	}

	@GetMapping(value = "get-jawaban-user", produces = "application/json")
	public @ResponseBody JawabanUser getJawabanUser(@RequestParam("id_soal") Long idSoal, HttpSession session) {
		User user = (User) session.getAttribute("user");
		return webService.getCurrentJawabanUser(idSoal, user.getId());
	}

	@PostMapping(value = "/save-jawaban-ujian")
	public @ResponseBody Map<String, String> saveJawabanUjian(@RequestBody JawabanUser entriJawaban, HttpSession session) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			User user = (User) session.getAttribute("user");

			if (user != null && user.getRole() != 3) {
				session.setAttribute("user", user);
				response.put("status", "401");
				response.put("message", "Unauthorized");
				return response;
			}

			JawabanUser saveJawaban = webService.getCurrentJawabanUser(entriJawaban.getId_soal().getId(), user.getId());
			saveJawaban.setJawabanUser(entriJawaban.getJawabanUser());

			webService.saveJawabanUser(saveJawaban);

			response.put("status", "202");
			response.put("message", "Accepted");
			return response;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		response.put("status", "400");
		response.put("message", "Bad Request");

		return response;
	}
}
