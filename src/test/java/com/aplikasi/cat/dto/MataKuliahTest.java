package com.aplikasi.cat.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MataKuliahTest {

    @Test
    void getId() {
        MataKuliah mataKuliah = MataKuliah.builder()
                .id(1L)
                .build();
        assertEquals(1, mataKuliah.getId());
    }

    @Test
    void getMataKuliah() {
        MataKuliah mataKuliah = MataKuliah.builder()
                .mataKuliah("PBO")
                .build();
        assertEquals("PBO", mataKuliah.getMataKuliah());
    }
}