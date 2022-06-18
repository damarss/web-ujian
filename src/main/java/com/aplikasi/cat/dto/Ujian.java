package com.aplikasi.cat.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aplikasi.cat.enums.JenisUjian;
import com.aplikasi.cat.enums.StatusUjian;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

@Entity
@Table(name = "ujian")
@Builder
public class Ujian {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private JenisUjian jenisUjian;
	private int jumlahSoal;
	private int waktuPengerjaan;
	private Date tanggalPelaksanaan;
	private String namaDosen;

	private StatusUjian status;

	private String peraturan;
	
	@ManyToOne
	@JoinColumn(name = "id_matkul", referencedColumnName = "id")
	private MataKuliah id_matkul;

	@OneToMany(mappedBy = "id_ujian", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Soal> soal;

	@OneToMany(mappedBy = "id_ujian", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Rekap> rekap;
	
	public MataKuliah getId_matkul() {
		return id_matkul;
	}

	public void setId_matkul(MataKuliah id_matkul) {
		this.id_matkul = id_matkul;
	}

	public Set<Soal> getSoal() {
		return soal;
	}

	public void setSoal(Set<Soal> soal) {
		this.soal = soal;
	}
	
	public Ujian() {
		
	}

	public Ujian(Long id, JenisUjian jenisUjian, int jumlahSoal, int waktuPengerjaan, Date tanggalPelaksanaan, String namaDosen, StatusUjian status, String peraturan, MataKuliah id_matkul, Set<Soal> soal, Set<Rekap> rekap) {
		this.id = id;
		this.jenisUjian = jenisUjian;
		this.jumlahSoal = jumlahSoal;
		this.waktuPengerjaan = waktuPengerjaan;
		this.tanggalPelaksanaan = tanggalPelaksanaan;
		this.namaDosen = namaDosen;
		this.status = status;
		this.peraturan = peraturan;
		this.id_matkul = id_matkul;
		this.soal = soal;
		this.rekap = rekap;
	}

	public void setNamaDosen(String namaDosen) {
		this.namaDosen = namaDosen;
	}

	public String getNamaDosen() {
		return namaDosen;
	}

	public void setJenisUjian(JenisUjian jenisUjian) {
		this.jenisUjian = jenisUjian;
	}

	public void setStatus(StatusUjian status) {
		this.status = status;
	}

	public Set<Rekap> getRekap() {
		return rekap;
	}

	public void setRekap(Set<Rekap> rekap) {
		this.rekap = rekap;
	}

	public String getPeraturan() {
		return peraturan;
	}

	public void setPeraturan(String peraturan) {
		this.peraturan = peraturan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JenisUjian getJenisUjian() {
		return jenisUjian;
	}

	public StatusUjian getStatus() {
		return status;
	}

	public int getJumlahSoal() {
		return jumlahSoal;
	}

	public void setJumlahSoal(int jumlahSoal) {
		this.jumlahSoal = jumlahSoal;
	}

	public int getWaktuPengerjaan() {
		return waktuPengerjaan;
	}

	public void setWaktuPengerjaan(int waktuPengerjaan) {
		this.waktuPengerjaan = waktuPengerjaan;
	}

	public Date getTanggalPelaksanaan() {
		return tanggalPelaksanaan;
	}

	public void setTanggalPelaksanaan(Date tanggalPelaksanaan) {
		this.tanggalPelaksanaan = tanggalPelaksanaan;
	}
}
