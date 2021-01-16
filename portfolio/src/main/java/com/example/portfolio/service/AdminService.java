package com.example.portfolio.service;

import java.util.List;

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

  /**
   * 管理者ログイン
   * @param adminName 管理者名
   * @param password パスワード
   * @return Admin
   */
  public Admin findByAdminNameAndPassword(String adminName, String password) {
    return adminRepos.findByAdminNameAndPassword(adminName, password);
  }

  /**
   * 管理者取得
   * @param id 管理者ID
   * @return Admin
   */
  public Admin findById(int id) {
    return adminRepos.findById(id).get();
  }

  /**
   * 管理者情報更新service
   * @param f AdminForm
   */
  public void updateAdmin(AdminForm f) {
    int id = adminSession.getId();
    String adminName = f.getAdminName();
    String password = f.getPassword();
    adminRepos.updateAdmin(id, adminName, password);
  }

  /**
   * 管理者セッション更新
   * @param admin Admin
   */
  public void setAdminSession(Admin admin) {
    adminSession.setId(admin.getId());
    adminSession.setPassword(admin.getPassword());
    adminSession.setAdminName(admin.getAdminName());
    adminSession.setLogined(true);
  }

  public List<Admin> findAll() {
    return adminRepos.findAll();
  }

}
