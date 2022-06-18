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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;

@Entity
@Table(name = "kuis")
@Builder
public class Kuis {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private String namaKuis;
	private int jumlahSoal;
	private int waktuPengerjaan;
	private Date batasWaktu;
	private String peraturan;
	private String namaDosen;
	
	@ManyToOne
	@JoinColumn(name = "id_matkul", referencedColumnName = "id")
	private MataKuliah id_matkul;
	
	@OneToMany(mappedBy = "id_kuis", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Soal> soal;

	@OneToMany(mappedBy = "id_kuis", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Rekap> rekap;

	public Kuis() {
	}

	public Kuis(Long id, String namaKuis, int jumlahSoal, int waktuPengerjaan, Date batasWaktu, String peraturan, String namaDosen, MataKuliah id_matkul, Set<Soal> soal, Set<Rekap> rekap) {
		this.id = id;
		this.namaKuis = namaKuis;
		this.jumlahSoal = jumlahSoal;
		this.waktuPengerjaan = waktuPengerjaan;
		this.batasWaktu = batasWaktu;
		this.peraturan = peraturan;
		this.namaDosen = namaDosen;
		this.id_matkul = id_matkul;
		this.soal = soal;
		this.rekap = rekap;
	}

	public String getNamaDosen() {
		return namaDosen;
	}

	public void setNamaDosen(String namaDosen) {
		this.namaDosen = namaDosen;
	}

	public String getPeraturan() {
		return peraturan;
	}

	public void setPeraturan(String peraturan) {
		this.peraturan = peraturan;
	}

	public Set<Rekap> getRekap() {
		return rekap;
	}

	public void setRekap(Set<Rekap> rekap) {
		this.rekap = rekap;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getNamaKuis() {
		return namaKuis;
	}

	public void setNamaKuis(String namaKuis) {
		this.namaKuis = namaKuis;
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

	public Date getBatasWaktu() {
		return batasWaktu;
	}

	public void setBatasWaktu(Date batasWaktu) {
		this.batasWaktu = batasWaktu;
	}


}
