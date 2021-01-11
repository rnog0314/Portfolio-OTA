package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.form.UserForm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);

	User findByUserId(int userId);

	User findByUserNameAndPassword(String userName, String password);

	@Query(value = "SELECT count(user_name) FROM users WHERE user_name = :newUserName", nativeQuery = true)
	int findByUserName(@Param("newUserName") String newUserName);

	@Modifying
	@Query(value = "INSERT INTO users (user_name, family_name, first_name, email, password, gender) VALUES (:#{#user.userName}, :#{#user.familyName}, :#{#user.firstName}, :#{#user.email}, :#{#user.password}, :#{#user.gender})", nativeQuery = true)
	int insertUser(@Param("user") User newUser);

	@Modifying
	@Query(value = "UPDATE users SET user_name = :userName, family_name = :familyName, first_name = :firstName, email = :email, password = :password, user_img = :imgPath WHERE user_id = :userId", nativeQuery = true)
	int update(	@Param("userName") String userName,
							@Param("familyName") String familyName,
							@Param("firstName") String firstName,
							@Param("email") String email,
							@Param("password") String password,
							@Param("imgPath") String imgPath,
							@Param("userId") int userId);

	@Modifying
	@Query(value = "UPDATE users SET user_img = :bytes WHERE user_id = :userId", nativeQuery = true)
	int updateUserImage( @Param("bytes") byte[] bytes,
												@Param("userId") int userId);

	@Modifying
	@Query(value = "UPDATE users SET user_name = :#{#u.userName}, family_name = :#{#u.familyName}, first_name = :#{#u.firstName}, email = :#{#u.email}, password = :#{#u.password} WHERE user_id = :#{#u.userId}", nativeQuery = true)
	void updateUser(@Param("u") UserForm userForm);

	@Query(value = "SELECT user_name from users WHERE user_id = :userId", nativeQuery = true)
	String findUserNameByUserId(@Param("userId") int userId);

	@Query(value = "SELECT email from users WHERE user_id = :userId", nativeQuery = true)
	String findEmailByUserId(@Param("userId") int userId);

}
