package com.aplikasi.cat.controller;

import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;

import com.aplikasi.cat.repo.MataKuliahRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aplikasi.cat.dto.*;
import com.aplikasi.cat.service.*;

@Controller
public class WebController {
	@Autowired
	WebService webService;

	@GetMapping(value = {"/", "/dashboard"})
	public String home(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");
		if (user.getRole() == 1) {
			model.addAttribute("main", "./components/dashboard-operator.jsp");
			model.addAttribute("navigation", "./navigation-operator.jsp");
		} else if (user.getRole() == 2) {
			model.addAttribute("main", "./components/dashboard-dosen.jsp");
			model.addAttribute("navigation", "./navigation.jsp");
		} else {
			model.addAttribute("main", "./components/dashboard-mahasiswa.jsp");
			model.addAttribute("navigation", "./navigation.jsp");
		}


		return "index";
	}

	@GetMapping(value = "/login")
	public String login(HttpSession session) {
		if (session.getAttribute("user") != null) {
			return "redirect:/";
		}
		return "login";
	}

	@PostMapping(value = "/login")
	public String login(@RequestParam Map<String, Object> params, HttpSession session, Model model) {

		String username = params.get("username").toString();
		String password = params.get("password").toString();

		User user = webService.login(username, password);


		if (user != null) {
			session.setAttribute("user", user);
			return "redirect:/";
		}

		model.addAttribute("error", "Username/password yang Anda input salah!");

		return "login";
	}

	@GetMapping(value = "/rekap")
	public String rekap(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");
		if (user.getRole() == 1) {
			model.addAttribute("main", "./components/dashboard-operator.jsp");
			model.addAttribute("navigation", "./navigation-operator.jsp");
		} else if (user.getRole() == 2) {
			model.addAttribute("main", "./components/dashboard-dosen.jsp");
			model.addAttribute("navigation", "./navigation.jsp");
			model.addAttribute("url", "get-rekap-dosen");
			return "rekap-dosen";
		} else {
			model.addAttribute("main", "./components/dashboard-mahasiswa.jsp");
			model.addAttribute("navigation", "./navigation.jsp");
			model.addAttribute("url", "get-rekap-mahasiswa");
			return "rekap-mahasiswa";
		}

		return "redirect:/";
	}

	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		session.invalidate();
		return "redirect:/login";
	}

	@PostMapping(value = "/error")
	public String error(Model model) {
		return "error";
	}

	@GetMapping(value = "/error-mulai")
	public String errorMulai() {
		return "error-mulai";
	}

	@GetMapping(value = "/get-rekap-dosen", produces = "application/json")
	public @ResponseBody List<Rekap> getRekapDosen(HttpSession session) {
		return webService.getRekapDosen();
	}

	@GetMapping(value = "/get-rekap-mahasiswa", produces = "application/json")
	public @ResponseBody List<Rekap> getRekapMahasiswa(HttpSession session) {
		try	{
			User user = (User) session.getAttribute("user");
			return webService.getRekapMahasiswa(user.getId());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
}


