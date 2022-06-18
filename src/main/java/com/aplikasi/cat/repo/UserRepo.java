package com.aplikasi.cat.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aplikasi.cat.dto.User;

public interface UserRepo extends JpaRepository<User, Long> {
	@Query(value = "SELECT * FROM user_app WHERE username = :username AND password = :password", nativeQuery = true)
	User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	@Query(value = "SELECT * FROM user_app WHERE role <> 1 ORDER BY role, username, nama_lengkap", nativeQuery = true)
	List<User> findAllUser();
	
	@Query(value = "SELECT * FROM user_app WHERE id = :id", nativeQuery = true)
	User findUserById(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM user_app WHERE username = :username", nativeQuery = true)
	User findUserByUsername(@Param("username") String username);
}
