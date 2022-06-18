package com.aplikasi.cat.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aplikasi.cat.dto.Soal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SoalRepo extends JpaRepository<Soal, Long> {
    @Query(value = "SELECT * FROM Soal WHERE id_kuis = :id_kuis AND nomor_soal = :no", nativeQuery = true)
    Soal findSoalKuis(@Param("id_kuis") Long idKuis, @Param("no") int nomor);

    @Query(value = "SELECT * FROM Soal WHERE id_ujian = :id_ujian AND nomor_soal = :no", nativeQuery = true)
    Soal findSoalUjian(@Param("id_ujian") Long idUjian, @Param("no") int no);

    @Query(value = "SELECT * FROM Soal WHERE id_ujian = :id_ujian", nativeQuery = true)
    List<Soal> findAllSoalUjian(@Param("id_ujian") Long idUjian);

    @Query(value = "SELECT * FROM Soal WHERE id_kuis = :id_kuis", nativeQuery = true)
    List<Soal> findAllSoalKuis(@Param("id_kuis") Long idKuis);

    @Query(value = "SELECT * FROM Soal WHERE id = :id_soal", nativeQuery = true)
    Soal findSoalById(@Param("id_soal") Long idSoal);
}
