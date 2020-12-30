package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);

	@Query(value = "SELECT count(user_name) FROM users WHERE user_name = :newUserName", nativeQuery = true)
	int findByUserName(String newUserName);

}
