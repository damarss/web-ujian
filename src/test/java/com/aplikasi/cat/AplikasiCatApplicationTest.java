package com.aplikasi.cat;

import com.aplikasi.cat.controller.UjianController;
import com.aplikasi.cat.service.WebService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AplikasiCatApplicationTest {

    @Autowired
    UjianController ujianKuisController;
    @Autowired
    WebService webService;

    @Test
    void testGetUjian() {
        System.out.println("HA");
    }
}