package com.example.portfolio.service;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.AdminRepository;
import com.example.portfolio.model.entity.Admin;
import com.example.portfolio.model.form.AdminForm;
import com.example.portfolio.model.session.AdminSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class AdminService {

  @Autowired
  private AdminRepository adminRepos;

  @Autowired
  private AdminSession adminSession;

  public Admin findByAdminNameAndPassword(String adminName, String password) {
    return adminRepos.findByAdminNameAndPassword(adminName, password);
  }

  public Admin findById(Integer id) {
    return adminRepos.findById(id).get();
  }

  public void updateAdmin(AdminForm adminForm) {
    int id = adminSession.getId();
    String adminName = adminForm.getAdminName();
    String password = adminForm.getPassword();
    adminRepos.updateAdmin(id, adminName, password);
  }

  public void setAdminSession(Admin admin) {

    adminSession.setId(admin.getId());
    adminSession.setPassword(admin.getPassword());
    adminSession.setAdminName(admin.getAdminName());
    adminSession.setLogined(true);
  }

}
