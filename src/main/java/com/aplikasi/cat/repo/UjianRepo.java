package com.aplikasi.cat.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aplikasi.cat.dto.Ujian;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UjianRepo extends JpaRepository<Ujian, Long> {
    @Query(value = "SELECT * FROM Ujian WHERE id = :id", nativeQuery = true)
    Ujian findUjianById(@Param("id") Long idUjian);

    @Query(value = "SELECT * FROM Ujian WHERE status = 0 AND tanggal_pelaksanaan > LOCALTIME ORDER BY tanggal_pelaksanaan", nativeQuery = true)
    List<Ujian> findUjianMahasiswa();
}
