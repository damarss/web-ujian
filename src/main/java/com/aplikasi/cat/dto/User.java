package com.aplikasi.cat.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_app")
@Builder
public class User {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private String username;
	private String password;
	private String namaLengkap;
	private int role;

	@OneToMany(mappedBy = "id_user", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Rekap> rekap;

	@OneToMany(mappedBy = "id_user", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<JawabanUser> jawabanUser;

	public User() {
	}

	public User(Long id, String username, String password, String namaLengkap, int role, Set<Rekap> rekap,
			Set<JawabanUser> jawabanUser) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.namaLengkap = namaLengkap;
		this.role = role;
		this.rekap = rekap;
		this.jawabanUser = jawabanUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNamaLengkap() {
		return namaLengkap;
	}

	public void setNamaLengkap(String namaLengkap) {
		this.namaLengkap = namaLengkap;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", namaLengkap=" + namaLengkap
				+ ", role=" + role + "]";
	}

}
