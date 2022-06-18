package com.aplikasi.cat.dto;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class KuisTest {

    @Test
    void getNamaDosen() {
        Kuis kuis = Kuis.builder()
                .namaDosen("Dosen")
                .build();
        assertEquals("Dosen", kuis.getNamaDosen());
    }

    @Test
    void getPeraturan() {
        Kuis kuis = Kuis.builder()
                .peraturan("Tidak ada")
                .build();
        assertEquals("Tidak ada", kuis.getPeraturan());
    }

    @Test
    void getId() {
        Kuis kuis = Kuis.builder()
                .id(4L)
                .build();
        assertEquals(4, kuis.getId());
    }

    @Test
    void getNamaKuis() {
        Kuis kuis = Kuis.builder()
                .namaKuis("Coba")
                .build();
        assertEquals("Coba", kuis.getNamaKuis());
    }

    @Test
    void getJumlahSoal() {
        Kuis kuis = Kuis.builder()
                .jumlahSoal(50)
                .build();
        assertEquals(50, kuis.getJumlahSoal());
    }

    @Test
    void getWaktuPengerjaan() {
        Kuis kuis = Kuis.builder()
                .waktuPengerjaan(60)
                .build();
        assertEquals(60, kuis.getWaktuPengerjaan());
    }

    @Test
    void getBatasWaktu() {
        Date date = new Date();
        Kuis kuis = Kuis.builder()
                .batasWaktu(date)
                .build();
        assertEquals(date, kuis.getBatasWaktu());
    }
}