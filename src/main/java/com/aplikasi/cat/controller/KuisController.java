package com.aplikasi.cat.controller;

import com.aplikasi.cat.dto.*;
import com.aplikasi.cat.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class KuisController {
    @Autowired
    WebService webService;

    @GetMapping(value = "/kuis")
    public String kuis(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        User user = (User) session.getAttribute("user");
        if (user.getRole() == 1) {
            model.addAttribute("navigation", "./navigation-operator.jsp");
        } else if (user.getRole() == 2) {
            model.addAttribute("main", "./components/kuis-dosen.jsp");
            model.addAttribute("navigation", "./navigation.jsp");
        } else {
            model.addAttribute("main", "./components/kuis-mahasiswa.jsp");
            model.addAttribute("navigation", "./navigation.jsp");
            List<Kuis> daftarKuis = webService.getKuisMahasiswa();
            if (daftarKuis.size() == 0) {
                String tidakTersedia = "<div class='tidak-tersedia'>"
                        + "<div class='image'><img src='assets/ujian.png' alt='Kuis tidak tersedia'></div>"
                        + "<div class='content'>Tidak ada kuis tersedia</div>"
                        + "</div>";
                model.addAttribute("kuis", tidakTersedia);
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
                model.addAttribute("kuis", tersedia);
            }
        }

        return "kuis";
    }

    @GetMapping(value = "pembuatan-kuis")
    public String pembuatanKuis(HttpSession session, Model model) {
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

        return "pembuatan-kuis";
    }

    @PostMapping(value = "pembuatan-kuis")
    public String pembuatanKuis(@RequestParam Map<String, Object> params, HttpSession session) {
        try {
            Long idMatkul = Long.parseLong(params.get("matkul").toString());
            String namaKuis = params.get("nama-kuis").toString();
            int jumlahSoal = Integer.parseInt(params.get("jumlah-soal").toString());
            int waktuPengerjaan = Integer.parseInt(params.get("waktu-pengerjaan").toString());

//			convert datetime local to java.util.Date
            String strDate = params.get("batas-waktu").toString();
            strDate = strDate.replace("T", " ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date batasWaktu = dateFormat.parse(strDate);
            String peraturan = params.get("peraturan").toString();

            MataKuliah mataKuliah = webService.findMatkul(idMatkul);

            User dosen = (User) session.getAttribute("user");

            Kuis newKuis = Kuis.builder()
                    .id_matkul(mataKuliah)
                    .namaDosen(dosen.getNamaLengkap())
                    .namaKuis(namaKuis)
                    .jumlahSoal(jumlahSoal)
                    .waktuPengerjaan(waktuPengerjaan)
                    .batasWaktu(batasWaktu)
                    .peraturan(peraturan)
                    .build();

            webService.buatKuis(newKuis);

            session.setAttribute("jumlahSoal", jumlahSoal);
            session.setAttribute("id_kuis", newKuis.getId());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return "redirect:/buat-soal";
    }

    @GetMapping(value = "/get-soal-kuis", produces = "application/json")
    public @ResponseBody Soal getSoalKuis(@RequestParam("id_kuis") Long idKuis, @RequestParam("nomor") int nomor) {
        return webService.getSoalKuis(idKuis, nomor);
    }

    @GetMapping(value = "/get-all-soal-kuis", produces = "application/json")
    public @ResponseBody List<Soal> getAllSoalKuis(@RequestParam("id_kuis") Long idKuis) {
        return webService.getAllSoalKuis(idKuis);
    }

    @GetMapping(value = "/get-current-kuis", produces = "application/json")
    public @ResponseBody Kuis getCurrentKuis(@RequestParam("id_kuis") Long idKuis) {
        return webService.findKuisById(idKuis);
    }

    @GetMapping(value = "/get-kuis", produces = "application/json")
    public @ResponseBody List<Kuis> getKuis() {
        return webService.findKuis();
    }

    @GetMapping(value = "/edit-pembuatan-kuis")
    public String editKuis(@RequestParam("id_kuis") Long idKuis, HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        User user = (User) session.getAttribute("user");
        if (user.getRole() != 2) {
            return "redirect:/";
        }

        session.setAttribute("id_kuis", idKuis);

        Kuis editKuis = webService.findKuisById(idKuis);
        model.addAttribute("idMatkul", editKuis.getId_matkul().getId());
        model.addAttribute("namaKuis", editKuis.getNamaKuis());
        model.addAttribute("jumlahSoal", editKuis.getJumlahSoal());
        model.addAttribute("waktuPengerjaan", editKuis.getWaktuPengerjaan());
        model.addAttribute("peraturan", editKuis.getPeraturan());
        model.addAttribute("batasWaktu", editKuis.getBatasWaktu());

        List<MataKuliah> listMatkul = webService.findMatkul();
        String matkul = "";
        for (MataKuliah mk: listMatkul) {
            matkul += "<option value=\"" + mk.getId() + "\">" + mk.getMataKuliah() +"</option>";
        }
        model.addAttribute("matkul", matkul);

        return "edit-pembuatan-kuis";
    }

    @PostMapping(value = "/edit-pembuatan-kuis")
    public String editPembuatanKuis(@RequestParam Map<String, Object> params, HttpSession session) {
        try {
            Kuis editKuis = webService.findKuisById(Long.parseLong(session.getAttribute("id_kuis").toString()));

            Long idMatkul = Long.parseLong(params.get("matkul").toString());
            String namaKuis = params.get("nama-kuis").toString();
            int jumlahSoal = editKuis.getJumlahSoal();
            int waktuPengerjaan = Integer.parseInt(params.get("waktu-pengerjaan").toString());

//			convert datetime local to java.util.Date
            String strDate = params.get("batas-waktu").toString();
            strDate = strDate.replace("T", " ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date batasWaktu = dateFormat.parse(strDate);
            String peraturan = params.get("peraturan").toString();

            MataKuliah mataKuliah = webService.findMatkul(idMatkul);
            editKuis.setId_matkul(mataKuliah);
            editKuis.setNamaKuis(namaKuis);
            editKuis.setJumlahSoal(jumlahSoal);
            editKuis.setBatasWaktu(batasWaktu);
            editKuis.setWaktuPengerjaan(waktuPengerjaan);
            editKuis.setPeraturan(peraturan);

            webService.editKuis(editKuis);
            session.setAttribute("jumlahSoal", jumlahSoal);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return "redirect:/edit-soal";
    }

    @GetMapping("/hapus-kuis")
    public String hapusKuis(@RequestParam("id_kuis") Long idKuis, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        User user = (User) session.getAttribute("user");
        if (user.getRole() != 2) {
            return "redirect:/";
        }

        webService.hapusKuis(idKuis);
        return "redirect:/kuis";
    }

    @GetMapping(value = "get-jawaban-kuis", produces = "application/json")
    public @ResponseBody List<Jawaban> getJawabanKuis(@RequestParam("id_kuis") Long idKuis) {
        return webService.getJawabanKuis(idKuis);
    }

    @GetMapping(value = "get-kuis-mahasiswa", produces = "application/json")
    public @ResponseBody List<Kuis> getKuisMahasiswa() {
        return webService.getKuisMahasiswa();
    }

    @GetMapping(value = "/kuis/peraturan/{id_kuis}")
    public String peraturanKuis(@PathVariable("id_kuis") Long idKuis, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        User user = (User) session.getAttribute("user");

        if (user.getRole() != 3) {
            return "redirect:/";
        }

        Rekap currentRekap = webService.getRekapKuis(user.getId(), idKuis);

        if (currentRekap != null) {
            return "redirect:/error-mulai";
        }

        session.setAttribute("idKuis", idKuis);
        return "kuis/peraturan";
    }

    @PostMapping(value = "/kuis/peraturan")
    public String setujuPeraturanKuis(HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            Long idKuis = Long.parseLong(session.getAttribute("idKuis").toString());

            List<Soal> soalList = webService.getAllSoalKuis(idKuis);
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
                    .id_kuis(webService.findKuisById(idKuis))
                    .build();
            webService.createNewRekap(newRekap);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return "redirect:/kuis/soal";
    }

    @GetMapping(value = "/kuis/soal")
    public String soalKuis(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        User user = (User) session.getAttribute("user");

        if (user.getRole() != 3) {
            return "redirect:/";
        }

        return "kuis/soal";
    }

    @PostMapping(value = "/kuis/soal")
    public String akhiriKuis(HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            Long idKuis = Long.parseLong(session.getAttribute("idKuis").toString());
            Kuis kuis = webService.findKuisById(idKuis);
            webService.koreksiJawabanKuis(user, kuis);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return "redirect:/kuis/rekap";
    }

    @GetMapping(value = "/kuis/rekap")
    public String rekapKuis(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        User user = (User) session.getAttribute("user");
        Long idKuis = Long.parseLong(session.getAttribute("idKuis").toString());

        Rekap rekap = webService.getRekapKuis(user.getId(), idKuis);

        model.addAttribute("terjawab", rekap.getSoalTerjawab());
        model.addAttribute("tidakTerjawab", rekap.getSoalTidakTerjawab());
        model.addAttribute("benar", rekap.getJawabanBenar());
        model.addAttribute("salah", rekap.getJawabanSalah());
        model.addAttribute("jumlahSoal", rekap.getId_kuis().getJumlahSoal());
        model.addAttribute("nilai", String.format("%.2f", rekap.getNilai()));

        return "kuis/rekap";
    }

    @PostMapping(value = "/save-jawaban-kuis")
    public @ResponseBody Map<String, String> saveJawabanKuis(@RequestBody JawabanUser entriJawaban, HttpSession session) {
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
