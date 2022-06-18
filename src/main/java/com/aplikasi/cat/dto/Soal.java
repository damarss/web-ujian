package com.aplikasi.cat.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

@Entity
@Table(name = "soal")
@Builder
public class Soal {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private int nomorSoal;

	private String soal;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String option5;
	
	@ManyToOne
	@JoinColumn(name = "id_ujian", referencedColumnName = "id")
	private Ujian id_ujian;
	
	@ManyToOne
	@JoinColumn(name = "id_kuis", referencedColumnName = "id")
	private Kuis id_kuis;

	@OneToOne(mappedBy = "id_soal", cascade = CascadeType.ALL)
	@JsonIgnore
	private Jawaban jawaban;

	@OneToMany(mappedBy = "id_soal", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<JawabanUser> jawabanUser;
	
	public Soal() {
	}

	public Soal(Long id, int nomorSoal, String soal, String option1, String option2, String option3, String option4, String option5, Ujian id_ujian, Kuis id_kuis, Jawaban jawaban, Set<JawabanUser> jawabanUser) {
		this.id = id;
		this.nomorSoal = nomorSoal;
		this.soal = soal;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.option5 = option5;
		this.id_ujian = id_ujian;
		this.id_kuis = id_kuis;
		this.jawaban = jawaban;
		this.jawabanUser = jawabanUser;
	}

	public Set<JawabanUser> getJawabanUser() {
		return jawabanUser;
	}

	public void setJawabanUser(Set<JawabanUser> jawabanUser) {
		this.jawabanUser = jawabanUser;
	}

	public Jawaban getJawaban() {
		return jawaban;
	}

	public void setJawaban(Jawaban jawaban) {
		this.jawaban = jawaban;
	}

	public String getSoal() {
		return soal;
	}

	public void setSoal(String soal) {
		this.soal = soal;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public int getNomorSoal() {
		return nomorSoal;
	}

	public void setNomorSoal(int nomorSoal) {
		this.nomorSoal = nomorSoal;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getOption5() {
		return option5;
	}

	public void setOption5(String option5) {
		this.option5 = option5;
	}

	public Ujian getId_ujian() {
		return id_ujian;
	}

	public void setId_ujian(Ujian id_ujian) {
		this.id_ujian = id_ujian;
	}

	public Kuis getId_kuis() {
		return id_kuis;
	}

	public void setId_kuis(Kuis id_kuis) {
		this.id_kuis = id_kuis;
	}

	@Override
	public String toString() {
		return "Soal{" +
				"id=" + id +
				", nomorSoal=" + nomorSoal +
				", soal='" + soal + '\'' +
				", option1='" + option1 + '\'' +
				", option2='" + option2 + '\'' +
				", option3='" + option3 + '\'' +
				", option4='" + option4 + '\'' +
				", option5='" + option5 + '\'' +
				", id_ujian=" + id_ujian +
				", id_kuis=" + id_kuis +
				'}';
	}
}
