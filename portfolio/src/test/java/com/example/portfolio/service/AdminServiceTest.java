// package com.example.portfolio.service;

// import com.example.portfolio.model.dao.AdminRepository;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.jdbc.Sql;
// import org.springframework.transaction.annotation.Transactional;

// import static org.hamcrest.MatcherAssert.assertThat;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;



// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @Transactional
// @Sql
// public class AdminServiceTest {

//   @Autowired
//   private AdminRepository adminRepos;

//   // @Autowired
//   // private AdminService adminService;

//   @Test
//   public void testFindByAdminNameAndPassword() {
//     String adminName = "testAdmin1";
//     String password = "password001";
//     // System.out.println("Admin : " + adminService.findAll());
//     // System.out.println(adminRepos.findByAdminNameAndPassword(adminName, password));
//     assertTrue(adminRepos.findByAdminNameAndPassword(adminName, password) != null);

//     adminName = "testAdmin2";
//     password = "";
//     assertTrue(adminRepos.findByAdminNameAndPassword(adminName, password) == null);

//   }

// }
