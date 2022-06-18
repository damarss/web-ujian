package com.aplikasi.cat.dto;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "jawaban")
@Builder
public class Jawaban {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private int jawaban;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_soal", referencedColumnName = "id")
    private Soal id_soal;

    public Jawaban() {
    }

    public Jawaban(Long id, int jawaban, Soal id_soal) {
        this.id = id;
        this.jawaban = jawaban;
        this.id_soal = id_soal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getJawaban() {
        return jawaban;
    }

    public void setJawaban(int jawaban) {
        this.jawaban = jawaban;
    }

    public Soal getId_soal() {
        return id_soal;
    }

    public void setId_soal(Soal id_soal) {
        this.id_soal = id_soal;
    }
}
