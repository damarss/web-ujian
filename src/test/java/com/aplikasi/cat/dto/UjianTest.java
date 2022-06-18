package com.aplikasi.cat.dto;

import com.aplikasi.cat.enums.JenisUjian;
import com.aplikasi.cat.enums.StatusUjian;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UjianTest {

    @Test
    void getNamaDosen() {
        Ujian ujian = Ujian.builder()
                .namaDosen("Dosen")
                .build();
        assertEquals("Dosen", ujian.getNamaDosen());
    }

    @Test
    void getPeraturan() {
        Ujian ujian = Ujian.builder()
                .peraturan("Tidak ada")
                .build();
        assertEquals("Tidak ada", ujian.getPeraturan());
    }

    @Test
    void getId() {
        Ujian ujian = Ujian.builder()
                .id(1L)
                .build();
        assertEquals(1, ujian.getId());
    }

    @Test
    void getStatus() {
        Ujian ujian = Ujian.builder()
                .status(StatusUjian.MENUNGGU)
                .build();
        assertEquals(StatusUjian.MENUNGGU, ujian.getStatus());
    }

    @Test
    void getJumlahSoal() {
        Ujian ujian = Ujian.builder()
                .jumlahSoal(3)
                .build();
        assertEquals(3, ujian.getJumlahSoal());
    }

    @Test
    void getWaktuPengerjaan() {
        Ujian ujian = Ujian.builder()
                .waktuPengerjaan(15)
                .build();
        assertEquals(15, ujian.getWaktuPengerjaan());
    }

    @Test
    void getTanggalPelaksanaan() {
        Date date = new Date();
        Ujian ujian = Ujian.builder()
                .tanggalPelaksanaan(date)
                .build();
        assertEquals(date, ujian.getTanggalPelaksanaan());
    }


    @Test
    void getJenisUjian() {
        JenisUjian jenisUjian = JenisUjian.UTS;
        Ujian ujian = Ujian.builder()
                .jenisUjian(jenisUjian)
                .build();
        assertEquals(JenisUjian.UTS, ujian.getJenisUjian());
        assertNotEquals(JenisUjian.UAS, ujian.getJenisUjian());
        assertEquals(JenisUjian.UAS, JenisUjian.values()[1]);
    }
}