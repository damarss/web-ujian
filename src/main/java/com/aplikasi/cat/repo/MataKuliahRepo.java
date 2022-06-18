package com.aplikasi.cat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aplikasi.cat.dto.MataKuliah;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MataKuliahRepo extends JpaRepository<MataKuliah, Long> {
    @Query(value = "SELECT * FROM mata_kuliah WHERE id = :id", nativeQuery = true)
    MataKuliah findMataKuliahById(@Param("id") Long id);
}