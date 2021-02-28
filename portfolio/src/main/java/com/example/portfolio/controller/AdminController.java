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

  /**
   * 管理者ログインページ初期表示
   * @return admin/login.html 管理者画面ログインページ
   */
  @GetMapping(value = "")
  public String init() {
    return "admin/login";
  }

  /**
   * 管理者ログイン ajazx使用
   * @param f AdminForm
   * @return 管理者情報照合成功/失敗
   */
  @PostMapping(value = "/login")
  @ResponseBody
  public boolean home(@RequestBody AdminForm f) {
    boolean bool = false;
    // 入力されたユーザ名とパスワードをもとに管理者アカウントを取得
    Admin admin = adminService.findByAdminNameAndPassword(f.getAdminName(), f.getPassword());
    // 取得した管理者の情報が存在していたら
    if (admin != null) {
      adminService.setAdminSession(admin); // adminセッションにログイン情報を登録する
      bool = true; // 照合成功に変更する
    }
    return bool;
  }

  /**
   * 管理者画面初期表示
   * @param m Model
   * @return admin/home.html
   */
  @GetMapping(value = "/home")
  public String index(Model m) {
    if (!adminSession.isLogined()) { // ログインしていなかった場合ログインページに戻る
      return "redirect:/portfolio/admin";
    }
    Admin admin = adminService.findById(adminSession.getId()); // IDをもとにアカウントを取得する
    m.addAttribute("admin", admin);
    m.addAttribute("adminSession", adminSession);
    return "admin/home";
  }

  /**
   * 管理者アカウントページ初期表示
   * @param m Model
   * @return admin/account
   */
  @GetMapping(value = "/account")
  public String showAccount(Model m) {
    Admin admin = adminService.findById(adminSession.getId()); // IDをもとに管理者アカウントを取得する
    m.addAttribute("admin", admin);
    return "admin/account";
  }

  /**
   * 管理者アカウント情報修正
   * @param adminForm AdminForm
   * @param m Model
   * @return admin/account.html
   */
  @PostMapping(value = "/modify")
  public String modify(AdminForm adminForm, Model m) {
    adminService.updateAdmin(adminForm); // 修正されて内容をもとにアカウント情報更新する
    Admin admin = adminService.findById(adminSession.getId()); // IDをもとにアカウント情報を取得する
    adminService.setAdminSession(admin); // adminセッションに更新した情報を登録する
    boolean completeMsg = true; // アカウント情報の更新完了
    m.addAttribute("admin", admin);
    m.addAttribute("adminSession", adminSession);
    m.addAttribute("completeMsg", completeMsg);
    return "admin/account";
  }

}
