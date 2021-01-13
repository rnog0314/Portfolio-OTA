package com.example.portfolio.service;

import com.example.portfolio.model.dao.AdminRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;

public class AdminServiceTest {

  @Autowired
  private AdminRepository adminRepos;
  @Test
  public void testFindByAdminNameAndPassword() {
    String adminName = "testAdmin1";
    String password = "password001";
    assertThat(adminRepos.findByAdminNameAndPassword(adminName, password), isNotNull());

    adminName = "testAdmin2";
    password = "";
    assertThat(adminRepos.findByAdminNameAndPassword(adminName, password), isNull());

  }

}
