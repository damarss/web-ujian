package com.aplikasi.cat.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "rekap")
@Builder
public class Rekap {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private double nilai;
    private int soalTerjawab;
    private int soalTidakTerjawab;
    private int jawabanBenar;
    private int jawabanSalah;


    @ManyToOne
    @JoinColumn(name = "id_ujian", referencedColumnName = "id")
    private Ujian id_ujian;

    @ManyToOne
    @JoinColumn(name = "id_kuis", referencedColumnName = "id")
    private Kuis id_kuis;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User id_user;

    public Rekap() {
    }

    public Rekap(Long id, double nilai, int soalTerjawab, int soalTidakTerjawab, int jawabanBenar, int jawabanSalah, Ujian id_ujian, Kuis id_kuis, User id_user) {
        this.id = id;
        this.nilai = nilai;
        this.soalTerjawab = soalTerjawab;
        this.soalTidakTerjawab = soalTidakTerjawab;
        this.jawabanBenar = jawabanBenar;
        this.jawabanSalah = jawabanSalah;
        this.id_ujian = id_ujian;
        this.id_kuis = id_kuis;
        this.id_user = id_user;
    }

    public int getSoalTidakTerjawab() {
        return soalTidakTerjawab;
    }

    public void setSoalTidakTerjawab(int soalTidakTerjawab) {
        this.soalTidakTerjawab = soalTidakTerjawab;
    }

    public int getJawabanBenar() {
        return jawabanBenar;
    }

    public void setJawabanBenar(int jawabanBenar) {
        this.jawabanBenar = jawabanBenar;
    }

    public int getJawabanSalah() {
        return jawabanSalah;
    }

    public void setJawabanSalah(int jawabanSalah) {
        this.jawabanSalah = jawabanSalah;
    }

    public int getSoalTerjawab() {
        return soalTerjawab;
    }

    public void setSoalTerjawab(int soalTerjawab) {
        this.soalTerjawab = soalTerjawab;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
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

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }
}
