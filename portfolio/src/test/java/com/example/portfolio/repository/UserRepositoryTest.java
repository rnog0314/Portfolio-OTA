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

}
