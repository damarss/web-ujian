package com.aplikasi.cat.service;

import com.aplikasi.cat.dto.MataKuliah;
import com.aplikasi.cat.dto.User;
import com.aplikasi.cat.repo.MataKuliahRepo;
import com.aplikasi.cat.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebServiceTest {
    @Autowired
    UserRepo userRepo;
    @Autowired
    MataKuliahRepo mataKuliahRepo;

    @Test
    void login() {
        String username = "admin";
        String password = "admin";
        User userLogin = User.builder()
                .username(username)
                .password(password)
                .build();
        User user = userRepo.findUserByUsernameAndPassword(username, password);
        assertEquals(userLogin.getUsername(), user.getUsername());
        assertEquals(userLogin.getPassword(), user.getPassword());
    }

    @Test
    void findMatkul() {
        MataKuliah mataKuliah = mataKuliahRepo.findMataKuliahById(1L);
        assertEquals("Pemrograman Berorientasi Objek", mataKuliah.getMataKuliah());
    }
}