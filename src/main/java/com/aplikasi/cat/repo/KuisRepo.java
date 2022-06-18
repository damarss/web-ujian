package com.aplikasi.cat.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aplikasi.cat.dto.Kuis;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KuisRepo extends JpaRepository<Kuis, Long> {
    @Query(value = "SELECT * FROM kuis WHERE id = :id", nativeQuery = true)
    Kuis findKuisById(@Param("id") Long idKuis);

    @Query(value = "SELECT * FROM kuis WHERE batas_waktu > CURRENT_DATE ORDER BY batas_waktu", nativeQuery = true)
    List<Kuis> findKuisAvailable();
}
