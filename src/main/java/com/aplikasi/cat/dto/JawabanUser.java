package com.aplikasi.cat.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "jawaban_user")
@Builder
public class JawabanUser {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private int jawabanUser;

    @ManyToOne
    @JoinColumn(name = "id_soal", referencedColumnName = "id")
    private Soal id_soal;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User id_user;

    public JawabanUser() {
    }

    public JawabanUser(Long id, int jawabanUser, Soal id_soal, User id_user) {
        this.id = id;
        this.jawabanUser = jawabanUser;
        this.id_soal = id_soal;
        this.id_user = id_user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getJawabanUser() {
        return jawabanUser;
    }

    public void setJawabanUser(int jawabanUser) {
        this.jawabanUser = jawabanUser;
    }

    public Soal getId_soal() {
        return id_soal;
    }

    public void setId_soal(Soal id_soal) {
        this.id_soal = id_soal;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }
}
