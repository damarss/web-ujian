package com.aplikasi.cat.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JawabanUserTest {

    @Test
    void getId() {
        JawabanUser jawabanUser = JawabanUser.builder()
                .id(3L)
                .build();
        assertEquals(3, jawabanUser.getId());
    }

    @Test
    void setId() {
        JawabanUser jawabanUser = JawabanUser.builder().build();
        jawabanUser.setId(2L);
        assertEquals(2, jawabanUser.getId());
        assertNotEquals(3, jawabanUser.getId());
    }

    @Test
    void getJawabanUser() {
        JawabanUser jawabanUser = JawabanUser.builder()
                .jawabanUser(2)
                .build();
        assertEquals(2, jawabanUser.getJawabanUser());
    }

    @Test
    void setJawabanUser() {
        JawabanUser jawabanUser = JawabanUser.builder().build();
        jawabanUser.setJawabanUser(3);
        assertEquals(3, jawabanUser.getJawabanUser());
    }

    @Test
    void getId_soal() {
        Soal soal = Soal.builder().build();
        JawabanUser jawabanUser = JawabanUser.builder()
                .id_soal(soal)
                .build();
        assertEquals(soal, jawabanUser.getId_soal());
    }

    @Test
    void setId_soal() {
        Soal soal = Soal.builder().build();
        JawabanUser jawabanUser = JawabanUser.builder().build();
        jawabanUser.setId_soal(soal);
        assertEquals(soal, jawabanUser.getId_soal());
    }

    @Test
    void getId_user() {
        User user = User.builder().build();
        JawabanUser jawabanUser = JawabanUser.builder()
                .id_user(user)
                .build();
        assertEquals(user, jawabanUser.getId_user());
    }

    @Test
    void setId_user() {
        User user = User.builder().build();
        JawabanUser jawabanUser = JawabanUser.builder().build();
        jawabanUser.setId_user(user);
        assertEquals(user, jawabanUser.getId_user());
    }
}