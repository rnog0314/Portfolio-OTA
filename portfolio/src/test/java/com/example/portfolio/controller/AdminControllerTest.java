package com.example.portfolio.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

// import com.example.portfolio.model.entity.Admin;
import com.example.portfolio.service.AdminService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Sql
public class AdminControllerTest {

  @Autowired
  private AdminService adminService;

  @Test
  public void testHome() {

    String adminName = "testAdmin1";
    String password = "password001";
    assertTrue(adminService.findByAdminNameAndPassword(adminName, password) != null);

  }
}
