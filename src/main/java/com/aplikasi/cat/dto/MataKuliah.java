package com.aplikasi.cat.dto;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

@Entity
@Table(name = "mata_kuliah")
@Builder
public class MataKuliah {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private String mataKuliah;
	
	@OneToMany(mappedBy = "id_matkul", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Ujian> ujian;
	
	@OneToMany(mappedBy = "id_matkul", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Kuis> kuis;


	public MataKuliah() {
	}

	public MataKuliah(Long id, String mataKuliah, Set<Ujian> ujian, Set<Kuis> kuis) {
		this.id = id;
		this.mataKuliah = mataKuliah;
		this.ujian = ujian;
		this.kuis = kuis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMataKuliah() {
		return mataKuliah;
	}

	public void setMataKuliah(String mataKuliah) {
		this.mataKuliah = mataKuliah;
	}

	@Override
	public String toString() {
		return "MataKuliah{" +
				"id=" + id +
				", mataKuliah='" + mataKuliah + '\'' +
				", ujian=" + ujian +
				", kuis=" + kuis +
				'}';
	}
}
