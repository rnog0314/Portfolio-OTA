package com.example.portfolio.repository;

import com.example.portfolio.model.dao.AdminRepository;
import com.example.portfolio.model.entity.Admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import javax.transaction.Transactional;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Sql
public class AdminRepositoryTest {

  @Autowired
  private AdminRepository adminRepos;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void testFindByAdminNameAndPassword() {
    String adminName = "testAdmin001";
    String password = "password001";
    Admin actual = adminRepos.findByAdminNameAndPassword(adminName, password);
    Admin expected = new Admin(1, "testAdmin001", "password001");
    assertThat(actual, is(samePropertyValuesAs(expected)));
  }

  @Test
  public void testUpdateUser() {
    int id = 1;
    String adminName = "testAdmin002";
    String password = "password002";
    adminRepos.updateAdmin(id, adminName, password);

    String sql = "SELECT * FROM admin";
    RowMapper<Admin> rowMapper = new BeanPropertyRowMapper<Admin>(Admin.class);
    List<Admin> admin = jdbcTemplate.query(sql, rowMapper);
    assertEquals(admin.get(0).getAdminName(), adminName);
  }

}
