package com.example.portfolio.repository;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.UserRepository;
import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.form.UserForm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Sql(scripts = "classpath:UserRepositoryTest.sql")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class UserRepositoryTest {

  @Autowired
  private UserRepository repos;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void testFindByEmailAndPassword() {
    String email = "example@gmail.com";
    String password = "password";
    User actual = repos.findByEmailAndPassword(email, password);
    assertTrue(actual != null);
  }

  @Test
  public void testFindById() {
    int id = 1;
    User actual = repos.findById(id);
    assertTrue(actual != null);
  }

  @Test
  public void testFindByUserName() {
    String userName = "testUser";
    int actual = repos.findByUserName(userName);
    assertEquals(1, actual);
  }

  @Test
  public void testInsertUser() {
      User user = new User();
      user.setId(6);
      user.setUserName("insertedUser");
      user.setPassword("password");
      user.setFamilyName("Jones");
      user.setFirstName("Adam");
      user.setEmail("example@gmail.com");
      user.setGender('F');
      user.setDeleteFlag(false);
      int actual = repos.insertUser(user);
      assertEquals(1, actual);
  }

  @Test
  public void testUpdateUserImage() {
    byte[] bytes = "dummyBytes".getBytes();
    int userId = 1;
    int actual = repos.updateUserImage(bytes, userId);
    assertEquals(1, actual);
  }

  @Test
  public void testFindUserNameById() {
    int id = 1;
    String actual = repos.findUserNameById(id);
    assertEquals("testUser", actual);
  }

  @Test
  public void testFindEmailById() {
    int id = 1;
    String actual = repos.findEmailById(id);
    assertEquals("example@gmail.com", actual);
  }

  @Test
  public void testUpdateUser() {
    UserForm f = new UserForm(1, "updatedName", "updatedEmail", "updatedPassword", "updatedFamilyName", "updatedFirstName");
    repos.updateUser(f);
    String sql = "SELECT * FROM users WHERE id = ?";
    RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
    int id = 1;
    User user = jdbcTemplate.queryForObject(sql, rowMapper, id);
    String userName = user.getUserName();
    String email = user.getEmail();
    String password = user.getPassword();
    String familyName = user.getFamilyName();
    String firstName = user.getFirstName();
    assertEquals("updatedName", userName);
    assertEquals("updatedEmail", email);
    assertEquals("updatedPassword", password);
    assertEquals("updatedFamilyName", familyName);
    assertEquals("updatedFirstName", firstName);
  }



}
