package com.aplikasi.cat.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getId() {
        User user = User.builder()
                .id(1L)
                .build();
        assertEquals(1, user.getId());
    }

    @Test
    void getUsername() {
        User user = User.builder()
                .username("admin")
                .build();
        assertEquals("admin", user.getUsername());
    }

    @Test
    void getPassword() {
        User user = User.builder()
                .password("admin")
                .build();
        assertEquals("admin", user.getPassword());
    }

    @Test
    void getNamaLengkap() {
        User user = User.builder()
                .namaLengkap("Admin")
                .build();
        assertEquals("Admin", user.getNamaLengkap());
    }

    @Test
    void getRole() {
        User user = User.builder()
                .role(1)
                .build();
        assertEquals(1, user.getRole());
    }
}