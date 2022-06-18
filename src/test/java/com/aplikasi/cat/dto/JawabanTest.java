package com.aplikasi.cat.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JawabanTest {
    @Test
    void getId() {
        Jawaban jawaban = Jawaban.builder()
                .id(1L)
                .build();
        assertEquals(1, jawaban.getId());
        assertNotEquals(2, jawaban.getId());
    }

    @Test
    void setId() {
        Jawaban jawaban = Jawaban.builder().build();
        jawaban.setId(3L);
        assertEquals(3, jawaban.getId());
        assertNotEquals(1, jawaban.getId());
    }

    @Test
    void getJawaban() {
        Jawaban jawaban = Jawaban.builder()
                .jawaban(2)
                .build();
        assertEquals(2, jawaban.getJawaban());
    }

    @Test
    void setJawaban() {
        Jawaban jawaban = Jawaban.builder().build();
        jawaban.setJawaban(4);
        assertEquals(4, jawaban.getJawaban());
    }

    @Test
    void getId_soal() {
        Soal soal = Soal.builder().build();
        Jawaban jawaban = Jawaban.builder()
                .id_soal(soal)
                .build();
        assertEquals(soal, jawaban.getId_soal());
    }

    @Test
    void setId_soal() {
        Jawaban jawaban = Jawaban.builder().build();
        Soal soal = Soal.builder().build();
        jawaban.setId_soal(soal);
        assertEquals(soal, jawaban.getId_soal());
    }
}