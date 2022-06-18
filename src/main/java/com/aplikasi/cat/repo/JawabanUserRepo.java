package com.aplikasi.cat.repo;

import com.aplikasi.cat.dto.JawabanUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JawabanUserRepo extends JpaRepository<JawabanUser, Long> {
    @Query(value = "SELECT * FROM jawaban_user WHERE id_soal = :id_soal AND id_user = :id_user", nativeQuery = true)
    JawabanUser findJawabanUserByIdSoalAndIdUser(@Param("id_soal") Long idSoal, @Param("id_user") Long idUser);

}
