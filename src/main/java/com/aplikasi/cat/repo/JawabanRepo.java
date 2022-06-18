package com.aplikasi.cat.repo;

import com.aplikasi.cat.dto.Jawaban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface JawabanRepo extends JpaRepository<Jawaban, Long> {

    @Query(value = "SELECT * FROM Jawaban WHERE id_soal = :id_soal", nativeQuery = true)
    Jawaban findJawaban(@Param("id_soal") Long idSoal);
}
