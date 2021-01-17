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

import java.util.ArrayList;
import java.util.List;


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

  @Test
  public void testSetAdminSession() {
    Admin testAdmin1 = new Admin(3, "testAdmin", "password");
    doNothing().when(adminSession).setId(testAdmin1.getId());
    doNothing().when(adminSession).setAdminName(testAdmin1.getAdminName());
    doNothing().when(adminSession).setPassword(testAdmin1.getPassword());
    doNothing().when(adminSession).setLogined(true);
    adminService.setAdminSession(testAdmin1);
  }

  @Test
  public void testFindAll() {
    List<Admin> expected = new ArrayList<Admin>();
    expected.add(new Admin(1, "testAdmin", "password"));
    expected.add(new Admin(2, "testAdmin2", "password2"));
    when(adminReposMock.findAll()).thenReturn(expected);
    List<Admin> actual = adminService.findAll();
    assertEquals(expected, actual);
  }


  @AfterEach
  public void clean() {
    actual = null;
    adminName = null;
    password = null;
  }

}
