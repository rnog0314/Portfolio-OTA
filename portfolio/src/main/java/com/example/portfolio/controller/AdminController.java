package com.example.portfolio.controller;

import com.example.portfolio.model.entity.Admin;
import com.example.portfolio.model.form.AdminForm;
import com.example.portfolio.model.session.AdminSession;
import com.example.portfolio.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/portfolio/admin")
public class AdminController {

  @Autowired
  private AdminSession adminSession;

  @Autowired
  private AdminService adminService;

  @GetMapping(value = "")
  public String goLoginPage() {
    return "admin/login";
  }

  @GetMapping(value = "/home")
  public String index(Model m) {
    if (!adminSession.isLogined()) {
      return "redirect:/portfolio/admin";
    }
    Admin admin = adminService.findBy(adminSession.getId());
    m.addAttribute("admin", admin);
    m.addAttribute("adminSession", adminSession);
    return "admin/index";
  }

  @PostMapping(value = "/login")
  @ResponseBody
  public Boolean goHomePage(@RequestBody AdminForm f) {
    Boolean bool = false;
    Admin admin = adminService.findByAdminNameAndPassword(f.getAdminName(), f.getPassword());
    if (admin != null) {
      adminSession.setId(admin.getId());
      adminSession.setPassword(admin.getPassword());
      adminSession.setAdminName(admin.getAdminName());
      adminSession.setLogined(true);
      bool = true;
    }
    return bool;
  }

}
