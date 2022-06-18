package com.aplikasi.cat.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoalTest {

    @Test
    void getJawaban() {
        Jawaban jawaban = Jawaban.builder().build();
        Soal soal = Soal.builder()
                .jawaban(jawaban)
                .build();
        assertEquals(jawaban, soal.getJawaban());
    }

    @Test
    void getSoal() {
        Soal soal = Soal.builder()
                .soal("Apa?")
                .build();
        assertEquals("Apa?", soal.getSoal());
    }

    @Test
    void getId() {
        Soal soal = Soal.builder()
                .id(2L)
                .build();
        assertEquals(2, soal.getId());
    }

    @Test
    void getNomorSoal() {
        Soal soal = Soal.builder()
                .nomorSoal(31)
                .build();
        assertEquals(31, soal.getNomorSoal());
    }

    @Test
    void getOption1() {
        Soal soal = Soal.builder()
                .option1("Iya")
                .build();
        assertEquals("Iya", soal.getOption1());
    }
}