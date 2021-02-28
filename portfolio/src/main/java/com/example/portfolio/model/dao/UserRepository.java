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

	/**
	 * メアドとパスワードを条件にユーザーレコードを取得
	 * @param email
	 * @param password
	 * @return User
	 */
	User findByEmailAndPassword(String email, String password);

	/**
	 * ユーザーIDを条件にユーザーレコードを取得
	 * @param userId
	 * @return User
	 */
	User findById(int userId);

	/**
	 * ユーザー名とパスワードを条件にユーザーレコードを取得
	 * @param userName
	 * @param password
	 * @return User
	 */
	User findByUserNameAndPassword(String userName, String password);

	/**
	 * 新規で登録するユーザーのユーザー名に重複がないかチェックする
	 * @param newUserName
	 * @return 1(重複あり) or 0(重複なし)
	 */
	@Query(value = "SELECT count(user_name) FROM users WHERE user_name = :newUserName", nativeQuery = true)
	int findByUserName(@Param("newUserName") String newUserName);

	/**
	 * 画面の新規ユーザー入力フォームに入力された内容をusersテーブルに挿入する
	 * @param newUser
	 * @return 1
	 */
	@Modifying
	@Query(value = "INSERT INTO users (user_name, family_name, first_name, email, password, gender) VALUES (:#{#u.userName}, :#{#u.familyName}, :#{#u.firstName}, :#{#u.email}, :#{#u.password}, :#{#u.gender})", nativeQuery = true)
	int insertUser(@Param("u") User newUser);

	/**
	 * ユーザープロフィール画像の更新
	 * @param bytes
	 * @param userId
	 * @return 1
	 */
	@Modifying
	@Query(value = "UPDATE users SET user_img = :bytes WHERE id = :userId", nativeQuery = true)
	int updateUserImage( @Param("bytes") byte[] bytes,
												@Param("userId") int userId);

	/**
	 * ユーザー情報の更新
	 * @param userForm
	 */
	@Modifying
	@Query(value = "UPDATE users SET user_name = :#{#u.userName}, family_name = :#{#u.familyName}, first_name = :#{#u.firstName}, email = :#{#u.email}, password = :#{#u.password} WHERE id = :#{#u.userId}", nativeQuery = true)
	void updateUser(@Param("u") UserForm userForm);

	/**
	 * ユーザーIDを条件にユーザ名を取得する
	 * @param userId
	 * @return ユーザー名
	 */
	@Query(value = "SELECT user_name FROM users WHERE id = :userId", nativeQuery = true)
	String findUserNameById(@Param("userId") int userId);

	/**
	 * ユーザーIDを条件にメールアドレスを取得する
	 * @param userId
	 * @return メールアドレス
	 */
	@Query(value = "SELECT email FROM users WHERE id = :userId", nativeQuery = true)
	String findEmailById(@Param("userId") int userId);

}
