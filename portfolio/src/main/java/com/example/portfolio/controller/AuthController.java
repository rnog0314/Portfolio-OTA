package com.example.portfolio.controller;

import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.form.UserForm;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/portfolio/auth")
public class AuthController {

  private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

  @Autowired
  private UserService userService;

  @Autowired
  private LoginSession loginSession;

  /**
   * 管理者ログイン
   *
   * @param form UserForm
   * @param m Model
   * @return User
   */
  @PostMapping(value = "/login")
  @ResponseBody
  public String login(@RequestBody UserForm form, Model m) {
    // ログインフォームに入力されたユーザ名とパスワードと一致するユーザを取得
    User user = userService.findByEmailAndPassword(form.getEmail(), form.getPassword());
    userService.setLoginSession(user);
    m.addAttribute("loginSession", loginSession);
    return gson.toJson(user);
  }

  /**
   * ログアウト
   *
   * @return なし
   */
  @PostMapping(value = "/logout")
  @ResponseBody
  public String logout() {
    loginSession.setTmpUserId(0);
    loginSession.setLogined(false);
    loginSession.setUserId(null);
    loginSession.setUserName(null);
    loginSession.setPassword(null);
    loginSession.setEmail(null);
    return "";
  }

}
