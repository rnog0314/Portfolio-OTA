package com.example.portfolio.controller;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.Mockito.doReturn;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.when;

import javax.transaction.Transactional;

// import javax.transaction.Transactional;

import com.example.portfolio.model.entity.Admin;
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

  /**
   * 管理者画面ログイン
   */
  @Test
  public void testHome() {

    // 管理者アカウントが存在する場合
    String  adminName = "testAdmin1";
    String password = "password001";
    Admin admin1 = adminService.findByAdminNameAndPassword(adminName, password);
    assertTrue(admin1 != null);

    // 管理者アカウントが存在しない場合
    adminName = "adminNotExsit";
    password = "passwordNotExsit";
    Admin admin2 = adminService.findByAdminNameAndPassword(adminName, password);
    assertNull(admin2);

  }
}
// import com.example.portfolio.model.entity.Admin;
// import com.example.portfolio.model.session.AdminSession;
// // import com.example.portfolio.model.entity.Admin;
// import com.example.portfolio.service.AdminService;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.jdbc.Sql;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @Transactional
// // @ExtendWith(MockitoExtension.class)
// @Sql
// public class AdminControllerTest {

//   // @InjectMocks
//   // private AdminController adminController = new AdminController();

//   // @Mock
//   // private AdminService adminService;

//   @Autowired
//   private AdminService adminService;

//   /**
//    * 管理者画面ログイン
//    */
//   @Test
//   public void testHome() {

//     /** 管理者の存在チェック */
//     // 管理者アカウントが存在する場合
//     // String adminName = "testAdmin1";
//     // String password = "password001";
//     // Admin expected = new Admin(1, "testAdmin1", "password001");
//     // when(adminService.findByAdminNameAndPassword(anyString(), anyString())).thenReturn(expected);
//     // Admin actual = adminService.findByAdminNameAndPassword(adminName, password);
//     // assertEquals(expected.getAdminName(), adminName);


//     // Declare variables
//     // String adminName;
//     // String password;
//     // String actual;
//     // String expected;

//     // // Provide initial data
//     // @BeforeEach
//     // public void setup() {

//     // }

//     // // Do unit test
//     // @Test
//     // public void testFindByAdminNameAndPassword() {

//     // }
//     // // clean heap data
//     // @AfterEach
//     // public void clean() {

//     // }

//     /** 管理者の存在チェック */
//     // 管理者アカウントが存在する場合
//     String adminName = "testAdmin1";
//     String password = "password001";
//     Admin admin1 = adminService.findByAdminNameAndPassword(adminName, password);
//     assertTrue(admin1 != null);

//     // 管理者アカウントが存在しない場合
//     // adminName = "adminNotExsit";
//     // password = "passwordNotExsit";
//     // Admin admin2 = adminService.findByAdminNameAndPassword(adminName, password);
//     // assertTrue(admin2 == null);

//     /** adminSessionへの登録 */

//     // Admin admin3 = new Admin(1, "testAdmin2", "password002");
//     // adminService.setAdminSession(admin3);

//     // AdminSession adminSession = mock(AdminSession.class);
//   }

// }
