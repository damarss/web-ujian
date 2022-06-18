package com.aplikasi.cat.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RekapTest {

    @Test
    void getSoalTidakTerjawab() {
        Rekap rekap = Rekap.builder()
                .soalTidakTerjawab(3)
                .build();
        assertEquals(3, rekap.getSoalTidakTerjawab());
    }

    @Test
    void getJawabanBenar() {
        Rekap rekap = Rekap.builder()
                .jawabanBenar(40)
                .build();
        assertEquals(40, rekap.getJawabanBenar());
    }

    @Test
    void getJawabanSalah() {
        Rekap rekap = Rekap.builder()
                .jawabanSalah(4)
                .build();
        assertEquals(4, rekap.getJawabanSalah());
    }

    @Test
    void getSoalTerjawab() {
        Rekap rekap = Rekap.builder()
                .soalTerjawab(49)
                .build();
        assertEquals(49, rekap.getSoalTerjawab());
    }

    @Test
    void getId() {
        Rekap rekap = Rekap.builder()
                .id(1L)
                .build();
        assertEquals(1, rekap.getId());
    }

    @Test
    void getNilai() {
        Rekap rekap = Rekap.builder()
                .nilai(98.0)
                .build();
        assertEquals(98.0, rekap.getNilai());
        assertEquals(98, rekap.getNilai());
        assertNotEquals(98.001, rekap.getNilai());
    }
}