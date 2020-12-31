package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);

	@Query(value = "SELECT count(user_name) FROM users WHERE user_name = :newUserName", nativeQuery = true)
	int findByUserName(String newUserName);

	@Modifying
	@Query(value = "INSERT INTO users (user_name, family_name, first_name, email, password, gender) VALUES (:#{#user.userName}, :#{#user.familyName}, :#{#user.firstName}, :#{#user.email}, :#{#user.password}, :#{#user.gender})", nativeQuery = true)
	int insertUser(@Param("user") User newUser);

	User findByUserId(Integer userId);

	User findByUserNameAndPassword(String userName, String password);

}
