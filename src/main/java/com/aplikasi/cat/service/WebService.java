package com.aplikasi.cat.service;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplikasi.cat.dto.*;
import com.aplikasi.cat.repo.*;

@Service("webService")
@Transactional
public class WebService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	MataKuliahRepo matkulRepo;
	@Autowired
	UjianRepo ujianRepo;
	@Autowired
	KuisRepo kuisRepo;
	@Autowired
	SoalRepo soalRepo;
	@Autowired
	JawabanRepo jawabanRepo;
	@Autowired
	JawabanUserRepo jawabanUserRepo;
	@Autowired
	RekapRepo rekapRepo;
	
	public User login(String username, String password) {
		User user = userRepo.findUserByUsernameAndPassword(username, password);
		return user;
	}
	
	public List<Ujian> findUjian() {
		return ujianRepo.findAll();
	}
	
	public List<Kuis> findKuis() {
		return kuisRepo.findAll();
	}
	
	public List<MataKuliah> findMatkul() {
		return matkulRepo.findAll();
	}

	public MataKuliah findMatkul(Long id) {
		return matkulRepo.findMataKuliahById(id);
	}
	
	public void buatUser(User newUser) {
		userRepo.save(newUser);
	}
	
	public List<User> findAllUser() {
		return userRepo.findAllUser();
	}
	
	public User findUser(Long id) {
		return userRepo.findUserById(id);
	}
	
	public User findUser(String username) {
		return userRepo.findUserByUsername(username);
	}
	
	public void editUser(User editUser) {
		userRepo.save(editUser);
	}
	
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}

	public Soal getSoalKuis(Long idKuis, int nomor) {
		return soalRepo.findSoalKuis(idKuis, nomor);
	}

	public Soal getSoalUjian(Long idUjian, int nomor) {
		return soalRepo.findSoalUjian(idUjian, nomor);
	}

	public List<Soal> getAllSoalUjian(Long idUjian) {
		return soalRepo.findAllSoalUjian(idUjian);
	}

	public List<Soal> getAllSoalKuis(Long idKuis) {
		return soalRepo.findAllSoalKuis(idKuis);
	}

	public void buatUjian(Ujian newUjian) {
		ujianRepo.save(newUjian);
	}

	public void buatKuis(Kuis newKuis) {
		kuisRepo.save(newKuis);
	}

	public Kuis findKuisById(Long idKuis) {
		return kuisRepo.findKuisById(idKuis);
	}

	public Ujian findUjian(Long idUjian) {
		return ujianRepo.findUjianById(idUjian);
	}

	public void buatSoal(Soal newSoal) {
		soalRepo.save(newSoal);
	}

	public void buatJawaban(Jawaban newJawaban) {
		jawabanRepo.save(newJawaban);
	}


	public void hapusKuis(Long idKuis) {
		kuisRepo.deleteById(idKuis);
	}

	public void tambahMataKuliah(MataKuliah newMataKuliah) {
		matkulRepo.save(newMataKuliah);
	}

	public void editMataKuliah(MataKuliah editMataKuliah) {
		matkulRepo.save(editMataKuliah);
	}
	public void hapusMataKuliah(Long idMatkul) {
		matkulRepo.deleteById(idMatkul);
	}

	public void hapusUjian(Long idUjian) {
		ujianRepo.deleteById(idUjian);
	}

	public List<Jawaban> getJawabanUjian(Long idUjian) {
		List<Soal> listSoal = soalRepo.findAllSoalUjian(idUjian);
		List<Jawaban> listJawaban = new ArrayList<Jawaban>();
		for (Soal soal: listSoal) {
			listJawaban.add(jawabanRepo.findJawaban(soal.getId()));
		}
		return listJawaban;
	}

	public List<Jawaban> getJawabanKuis(Long idKuis) {
		List<Soal> listSoal = soalRepo.findAllSoalKuis(idKuis);
		List<Jawaban> listJawaban = new ArrayList<Jawaban>();
		for (Soal soal: listSoal) {
			listJawaban.add(jawabanRepo.findJawaban(soal.getId()));
		}
		return listJawaban;
	}

	public void editUjian(Ujian editUjian) {
		ujianRepo.save(editUjian);
	}

	public void editKuis(Kuis editKuis) {
		kuisRepo.save(editKuis);
	}

	public Soal getSoal(Long idSoal) {
		return soalRepo.findSoalById(idSoal);
	}

	public Jawaban getJawaban(Long idSoal) {
		return jawabanRepo.findJawaban(idSoal);
	}

	public void editSoal(Soal editSoal) {
		soalRepo.save(editSoal);
	}

	public void editJawaban(Jawaban editJawaban) {
		jawabanRepo.save(editJawaban);
	}

	public List<Ujian> getUjianMahasiswa() {
		return ujianRepo.findUjianMahasiswa();
	}

	public List<Kuis> getKuisMahasiswa() {
		return kuisRepo.findKuisAvailable();
	}

	public void koreksiJawabanUjian(User user, Ujian ujian) {
		Map<Long, Integer> jawabanUser = new HashMap<Long, Integer>();
		Map<Long, Integer> jawabanSoal = new HashMap<Long, Integer>();
		List<Soal> soalList = soalRepo.findAllSoalUjian(ujian.getId());

		for (Soal soal: soalList) {
			System.out.println("soal = " +soal.getSoal());
			System.out.println("jawaban = " + soal.getJawaban().getJawaban());
			jawabanSoal.put(soal.getId(), soal.getJawaban().getJawaban());
			jawabanUser.put(soal.getId(), jawabanUserRepo.findJawabanUserByIdSoalAndIdUser(soal.getId(), user.getId()).getJawabanUser());
		}

		double nilai = 0;
		int jumlahSoal = ujian.getJumlahSoal();
		int soalTerjawab = 0;
		int soalTidakTerjawab = 0;
		int jawabanBenar = 0;
		int jawabanSalah = 0;

		for (Map.Entry<Long, Integer> jawaban: jawabanSoal.entrySet()) {
			if (jawabanUser.get(jawaban.getKey()) == 0) {
				soalTidakTerjawab++;
			} else if (jawaban.getValue() == jawabanUser.get(jawaban.getKey())) {
				jawabanBenar++;
				soalTerjawab++;
			} else {
				jawabanSalah++;
				soalTerjawab++;
			}
		}

		nilai = (jawabanBenar * 1.0 / jumlahSoal * 1.0) * 100;

		Rekap rekap = rekapRepo.findRekapByIdUserAndIdUjian(user.getId(), ujian.getId());
		rekap.setJawabanBenar(jawabanBenar);
		rekap.setJawabanSalah(jawabanSalah);
		rekap.setNilai(nilai);
		rekap.setSoalTerjawab(soalTerjawab);
		rekap.setSoalTidakTerjawab(soalTidakTerjawab);

		this.saveRekapUpdate(rekap);
	}

	public void koreksiJawabanKuis(User user, Kuis kuis) {
		Map<Long, Integer> jawabanUser = new HashMap<Long, Integer>();
		Map<Long, Integer> jawabanSoal = new HashMap<Long, Integer>();
		List<Soal> soalList = soalRepo.findAllSoalKuis(kuis.getId());

		for (Soal soal: soalList) {
			System.out.println("soal = " +soal.getSoal());
			System.out.println("jawaban = " + soal.getJawaban().getJawaban());
			jawabanSoal.put(soal.getId(), soal.getJawaban().getJawaban());
			jawabanUser.put(soal.getId(), jawabanUserRepo.findJawabanUserByIdSoalAndIdUser(soal.getId(), user.getId()).getJawabanUser());
		}

		double nilai = 0;
		int jumlahSoal = kuis.getJumlahSoal();
		int soalTerjawab = 0;
		int soalTidakTerjawab = 0;
		int jawabanBenar = 0;
		int jawabanSalah = 0;

		for (Map.Entry<Long, Integer> jawaban: jawabanSoal.entrySet()) {
			if (jawabanUser.get(jawaban.getKey()) == 0) {
				soalTidakTerjawab++;
			} else if (jawaban.getValue() == jawabanUser.get(jawaban.getKey())) {
				jawabanBenar++;
				soalTerjawab++;
			} else {
				jawabanSalah++;
				soalTerjawab++;
			}
		}

		nilai = (jawabanBenar * 1.0 / jumlahSoal * 1.0) * 100;

		Rekap rekap = rekapRepo.findRekapByIdUserAndIdKuis(user.getId(), kuis.getId());
		rekap.setJawabanBenar(jawabanBenar);
		rekap.setJawabanSalah(jawabanSalah);
		rekap.setNilai(nilai);
		rekap.setSoalTerjawab(soalTerjawab);
		rekap.setSoalTidakTerjawab(soalTidakTerjawab);

		this.saveRekapUpdate(rekap);
	}

	public void saveJawabanUser(JawabanUser jawabanUser) {
		jawabanUserRepo.save(jawabanUser);
	}

	public void createNewRekap(Rekap newRekap) {
		rekapRepo.save(newRekap);
	}

	public void saveRekapUpdate(Rekap saveRekap) {
		rekapRepo.save(saveRekap);
	}

	public JawabanUser getCurrentJawabanUser(Long idSoal, Long idUser) {
		return jawabanUserRepo.findJawabanUserByIdSoalAndIdUser(idSoal, idUser);
	}

	public Rekap getRekapUjian(Long idUser, Long idUjian) {
		return rekapRepo.findRekapByIdUserAndIdUjian(idUser, idUjian);
	}

	public Rekap getRekapKuis(Long idUser, Long idKuis) {
		return rekapRepo.findRekapByIdUserAndIdKuis(idUser, idKuis);
	}

	public List<Rekap> getRekapDosen() {
		return rekapRepo.findAll();
	}

	public List<Rekap> getRekapMahasiswa(Long idUser) {
		return rekapRepo.findRekapByIdUser(idUser);
	}
}