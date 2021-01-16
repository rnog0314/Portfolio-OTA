package com.example.portfolio.service;

import com.example.portfolio.model.dao.AdminRepository;
import com.example.portfolio.model.entity.Admin;
import com.example.portfolio.model.form.AdminForm;
import com.example.portfolio.model.session.AdminSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class AdminServiceTest {

  @Mock
  private AdminRepository adminReposMock;

  @Mock
  private AdminSession adminSession;

  @InjectMocks
  private AdminService adminService;

  // Declare variables
  Admin actual;
  Admin expected;
  String adminName;
  String password;
  int adminId;

  @Test
  public void testFindByAdminNameAndPassword() {
    expected = new Admin(1, "testAdmin1", "password001");
    adminName = "testAdmin1";
    password = "password001";
    // Mockの動作を定義
    when(adminReposMock.findByAdminNameAndPassword(adminName, password)).thenReturn(expected);
    /* execute */
    actual = adminService.findByAdminNameAndPassword(adminName, password);
    /* verify */
    assertEquals(expected, actual);
    /* execute */
    adminName = "dummyAdmin";
    password = "dummyPass";
    actual = adminService.findByAdminNameAndPassword(adminName, password);
    /* verify */
    assertNull(actual);
  }

  @Test
  public void testUpdateAdmin() {
    AdminForm f = new AdminForm("testAdmin", "password");
    when(adminSession.getId()).thenReturn(1);
    doNothing().when(adminReposMock).updateAdmin(1, "testAdmin", "password");
    adminService.updateAdmin(f);
    assertEquals(1 , adminSession.getId());
    assertEquals("testAdmin", f.getAdminName());
    assertEquals("password", f.getPassword());
  }


  @AfterEach
  public void clean() {
    actual = null;
    adminName = null;
    password = null;
  }

}
