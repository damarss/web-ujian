package com.aplikasi.cat.repo;

import com.aplikasi.cat.dto.Rekap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RekapRepo extends JpaRepository<Rekap, Long> {

    @Query(value = "SELECT * FROM Rekap WHERE id_user = :id_user AND id_ujian = :id_ujian", nativeQuery = true)
    Rekap findRekapByIdUserAndIdUjian(@Param("id_user") Long idUser, @Param("id_ujian") Long idUjian);

    @Query(value = "SELECT * FROM Rekap WHERE id_user = :id_user AND id_kuis = :id_kuis", nativeQuery = true)
    Rekap findRekapByIdUserAndIdKuis(@Param("id_user") Long idUser, @Param("id_kuis") Long idKuis);

    @Query(value = "SELECT * FROM Rekap WHERE id_user = :id_user", nativeQuery = true)
    List<Rekap> findRekapByIdUser(@Param("id_user") Long idUser);
}
