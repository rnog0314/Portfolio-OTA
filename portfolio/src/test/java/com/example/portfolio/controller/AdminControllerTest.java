package com.example.portfolio.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import javax.transaction.Transactional;

import com.example.portfolio.model.entity.Admin;
import com.example.portfolio.model.session.AdminSession;
// import com.example.portfolio.model.entity.Admin;
import com.example.portfolio.service.AdminService;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Sql
public class AdminControllerTest {

  @Autowired
  private AdminService adminService;

  @Mock
  AdminSession adminSession;

  /**
   * 管理者画面ログイン
   */
  @Test
  public void testHome() {

    /** 管理者の存在チェック */
    // 管理者アカウントが存在する場合
    String adminName = "testAdmin1";
    String password = "password001";
    Admin admin1 = adminService.findByAdminNameAndPassword(adminName, password);
    assertTrue(admin1 != null);

    // 管理者アカウントが存在しない場合
    adminName = "adminNotExsit";
    password = "passwordNotExsit";
    Admin admin2 = adminService.findByAdminNameAndPassword(adminName, password);
    assertTrue(admin2 == null);

    /** adminSessionへの登録 */

    Admin admin3 = new Admin(1, "testAdmin2", "password002");
    adminService.setAdminSession(admin3);

    adminSession.setId(1);
    adminSession.setAdminName("testAdmin");
    adminSession.setPassword("password2");
    adminSession.setLogined(true);
  }

}
