package com.aplikasi.cat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.aplikasi.cat.dto.MataKuliah;
import com.aplikasi.cat.dto.Ujian;
import com.aplikasi.cat.enums.StatusUjian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.aplikasi.cat.dto.User;
import com.aplikasi.cat.service.WebService;

@Controller
public class AdminController {
	@Autowired
	WebService webService;
	
	@GetMapping(value = "/kelola")
	public String kelolaUser(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("navigation", "./navigation-operator.jsp");
		
		if (user.getRole() != 1) {
			return "redirect:/";
		}
		
		List<User> listUser = webService.findAllUser();
		String output = "";
		int no = 0;
		for (User u: listUser) {
			output += "<tr><td>" + (++no) +"</td>";
			output += "<td>" + u.getUsername() +"</td>";
			output += "<td>" + u.getNamaLengkap() +"</td>";
			output += "<td>" + (u.getRole()==2 ? "Dosen":"Mahasiswa") + "</td>"; 
			output += "<td><div class='action-wrapper'><a class='btn edit' href=\"edit-user/" + u.getId() + "\">Edit</a><a class='btn cons' href=\"hapus-user/" + u.getId() + "\">Hapus</a></div></td></tr>";
		}
		model.addAttribute("userList", output);

		List<MataKuliah> listMatkul = webService.findMatkul();
		output = "";
		no = 0;
		for (MataKuliah mk: listMatkul) {
			output += "<tr>" +
					"<td>" + (++no) + "</td>" +
					"<td>" + mk.getMataKuliah() + "</td>" +
					"<td><div class='action-wrapper'><a class='btn edit' href='edit-matkul/" + mk.getId() + "'>Edit</a><a class='btn cons' href='hapus-matkul/" + mk.getId() + "'>Hapus</a></div></td>" +
					"</tr>";
		}
		model.addAttribute("matkulList", output);
		return "kelola";
	}

	@GetMapping(value = "get-user", produces = "application/json")
	public @ResponseBody Map<String, Integer> getUser() {
		Map<String, Integer> roleUser = new HashMap<String, Integer>();
		List<User> userList = webService.findAllUser();
		Integer jumlahDosen = 0;
		Integer jumlahMahasiswa = 0;
		for (User user: userList) {
			if (user.getRole() == 2) {
				jumlahDosen++;
			} else {
				jumlahMahasiswa++;
			}
		}
		roleUser.put("dosen", jumlahDosen);
		roleUser.put("mahasiswa",jumlahMahasiswa);
		return roleUser;
	}
	
	@GetMapping(value = "buat-user")
	public String buatUser(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("navigation", "./navigation-operator.jsp");
		
		if (user.getRole() != 1) {
			return "redirect:/";
		}
		
		return "buat-user";
	}
	
	@PostMapping(value = "buat-user")
	public String buatUser(@RequestParam Map<String, Object> params, HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		
		String username = params.get("username").toString();
		String password = params.get("password").toString();
		String nama = params.get("nama").toString();
		int role = Integer.parseInt(params.get("role").toString());
		
		User existUser = webService.findUser(username);
		if (existUser == null) {
			User newUser = User.builder()
						.username(username)
						.password(password)
						.namaLengkap(nama)
						.role(role)
						.build();
			webService.buatUser(newUser);
		} else {
			model.addAttribute("errMsg", "Username sudah digunakan");
			model.addAttribute("nama", nama);
			model.addAttribute("password", password);
			model.addAttribute("username", username);
			model.addAttribute("role", role);
			return "buat-user";
		}
		return "redirect:/kelola";
	}
	
	@GetMapping(value = "edit-user/{id}")
	public String editUser(@PathVariable("id") Long id, HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("navigation", "./navigation-operator.jsp");
		
		if (user.getRole() != 1) {
			return "redirect:/";
		}
		
		User editUser = webService.findUser(id);
		model.addAttribute("id", id);
		model.addAttribute("nama", editUser.getNamaLengkap());
		model.addAttribute("username", editUser.getUsername());
		model.addAttribute("password", editUser.getPassword());
		model.addAttribute("role", editUser.getRole());
		
		return "edit-user";
	}
	
	@PostMapping(value = "edit-user/{id}")
	public String editUser(@PathVariable("id") Long id, @RequestParam Map<String, Object> params, HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		
		String username = params.get("username").toString();
		String password = params.get("password").toString();
		String nama = params.get("nama").toString();
		int role = Integer.parseInt(params.get("role").toString());
		
		User existUser = webService.findUser(username);
		
		if (existUser == null) {
			User editedUser = User.builder()
					.username(username)
					.password(password)
					.namaLengkap(nama)
					.role(role)
					.id(id)
					.build();
			webService.editUser(editedUser);
		} else {
			model.addAttribute("errMsg", "Username sudah digunakan");
			model.addAttribute("nama", nama);
			model.addAttribute("username", username);
			model.addAttribute("password", password);
			model.addAttribute("role", role);
			return "edit-user";
		}
		
		
		return "redirect:/kelola";
	}
	
	@GetMapping(value = "hapus-user/{id}")
	public String hapusUser(@PathVariable("id") Long id, HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		
		User user = (User) session.getAttribute("user");

		if (user.getRole() != 1) {
			return "redirect:/";
		}
		
		webService.deleteUser(id);
		
		return "redirect:/kelola";
	}

	@GetMapping(value = "tambah-matkul")
	public String mataKuliah(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");

		if (user.getRole() != 1) {
			return "redirect:/";
		}

		return "tambah-matkul";
	}

	@PostMapping(value = "tambah-matkul")
	public String tambahMataKuliah(@RequestParam Map<String, Object> params) {
		String mataKuliah = params.get("mata-kuliah").toString();
		MataKuliah newMataKuliah = MataKuliah.builder()
				.mataKuliah(mataKuliah)
				.build();
		webService.tambahMataKuliah(newMataKuliah);
		return "redirect:/kelola";
	}

	@GetMapping(value = "edit-matkul/{id_matkul}")
	public String editMataKuliah(@PathVariable("id_matkul") Long idMatkul, HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");

		if (user.getRole() != 1) {
			return "redirect:/";
		}

		MataKuliah editMataKuliah = webService.findMatkul(idMatkul);
		model.addAttribute("mataKuliah", editMataKuliah.getMataKuliah());
		model.addAttribute("id", editMataKuliah.getId());
		return "edit-matkul";
	}

	@PostMapping(value = "edit-matkul/{id_matkul}")
	public String editMataKuliahAction(@PathVariable("id_matkul") Long idMatkul, @RequestParam Map<String, Object> params, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}

		try {
			MataKuliah editMataKuliah = webService.findMatkul(idMatkul);
			editMataKuliah.setMataKuliah(params.get("mata-kuliah").toString());
			webService.editMataKuliah(editMataKuliah);
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return "redirect:/kelola";
	}




	@GetMapping(value = "/hapus-matkul/{id_matkul}")
	public String hapusMataKuliah(@PathVariable("id_matkul") Long idMatkul) {
		webService.hapusMataKuliah(idMatkul);
		return "redirect:/kelola";
	}

	@GetMapping(value = "/lihat-soal/{id_ujian}")
	public String lihatSoal(@PathVariable("id_ujian") Long idUjian, HttpSession session, Model model) {
		Object userObj = session.getAttribute("user");
		if (userObj == null) {
			return "redirect:/";
		}

		User user = (User) userObj;
		if (user.getRole() != 1) {
			return "redirect:/";
		}

		model.addAttribute("url", "get-all-soal-ujian?id_ujian=" + idUjian);
		model.addAttribute("url2", "get-jawaban-ujian?id_ujian=" + idUjian);

		return "lihat-soal";
	}

	@GetMapping(value ="tolak-pengajuan/{id_ujian}")
	public String tolakPengajuan(@PathVariable("id_ujian") Long idUjian) {
		Ujian tolakUjian = webService.findUjian(idUjian);
		tolakUjian.setStatus(StatusUjian.DITOLAK);
		webService.editUjian(tolakUjian);

		return "redirect:/ujian";
	}

	@GetMapping(value = "approve-pengajuan/{id_ujian}")
	public String approvePengajuan(@PathVariable("id_ujian") Long idUjian) {
		Ujian approveUjian = webService.findUjian(idUjian);
		approveUjian.setStatus(StatusUjian.DISETUJUI);
		webService.editUjian(approveUjian);

		return "redirect:/ujian";
	}

	@GetMapping(value = "/atur-jadwal/{id_ujian}")
	public String aturJadwal(@PathVariable("id_ujian") Long idUjian, @RequestParam Map<String, Object> params) {
//		convert datetime local to java.util.Date
		try {
			Ujian editUjian = webService.findUjian(idUjian);
			String strDate = params.get("tanggal-ujian").toString();
			strDate = strDate.replace("T", " ");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date tanggalUjian = dateFormat.parse(strDate);
			editUjian.setTanggalPelaksanaan(tanggalUjian);
			webService.editUjian(editUjian);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return "redirect:/ujian";
	}
}
