package com.example.portfolio.repository;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.UserRepository;
import com.example.portfolio.model.entity.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Sql
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
      user.setUserName("testUser");
      user.setPassword("password");
      user.setFamilyName("Jones");
      user.setFirstName("Adam");
      user.setEmail("example@gmail.com");
      user.setGender('F');
      user.setDeleteFlag(false);
      int actual = repos.insertUser(user);
      assertEquals(1, actual);
  }

}
