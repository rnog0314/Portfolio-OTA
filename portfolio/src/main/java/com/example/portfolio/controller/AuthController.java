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
   * ログインメソッド
   *
   * @param form
   * @param m
   * @return
   */
  @PostMapping(value = "/login")
  @ResponseBody
  public String login(@RequestBody UserForm form, Model m) {

    // ログインフォームに入力されたユーザ名とパスワードと一致するユーザを取得
    User user = userService.findByEmailAndPassword(form.getEmail(), form.getPassword());

    if (user != null) { // ユーザが存在すれば
      loginSession.setTmpUserId(null); // トップページ初期表示時に付与した仮ユーザIDをnullにして破棄
      loginSession.setLogined(true); // ログイン状態にする
      loginSession.setUserId(user.getUserId());
      loginSession.setUserName(user.getUserName());
      loginSession.setPassword(user.getPassword());
      loginSession.setEmail(user.getEmail());
    } else { // 一致するユーザ情報がなければ、仮ユーザIDはそのまま
      loginSession.setLogined(false);
      loginSession.setUserId(null);
      loginSession.setUserName(null);
      loginSession.setPassword(null);
      loginSession.setEmail(null);
    }
    m.addAttribute("loginSession", loginSession);
    return gson.toJson(user);
  }

  /**
   * ログアウトメソッド
   *
   * @return
   */
  @PostMapping(value = "/logout")
  @ResponseBody
  public String logout() {
    loginSession.setTmpUserId(null);
    loginSession.setLogined(false);
    loginSession.setUserId(null);
    loginSession.setUserName(null);
    loginSession.setPassword(null);
    loginSession.setEmail(null);
    return "";
  }

}
