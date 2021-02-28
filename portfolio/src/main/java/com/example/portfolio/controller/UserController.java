package com.example.portfolio.controller;

import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.form.UserForm;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.UserService;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/portfolio/user")
public class UserController {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private UserService userService;

  Gson gson = new Gson();

  /**
   * 新規ユーザー登録ページ初期表示
   *
   * @param m Model
   * @return user_register.html
   */
  @GetMapping("")
  public String init(Model m) {
    m.addAttribute("loginSession", loginSession);
    return "user_register";
  }

  /**
   * ユーザ名重複確認メソッド
   *
   * @param newUserName 新規ユーザ名
   * @return bool 重複有無(falseで重複なし)
   */
  @PostMapping("/check")
  @ResponseBody
  public boolean checkUserNameDuplicate(@RequestBody String newUserName) {
    boolean bool = false; // 重複があるかどうか真偽値をfalseで初期化
    int count = userService.findByUserName(newUserName);
    if (count > 0) { // 同一のユーザ名が既に存在していたらtrueを返してエラーメッセージを表示
      bool = true;
    } else {
      bool = false; // 同一のユーザ名が存在していなければfalseを返して続行させる
    }
    return bool;
  }

  /**
   * 新規ユーザー登録
   *
   * @param user User
   * @return bool 新規ユーザ登録成功/失敗
   */
  @PostMapping("/register")
  @ResponseBody
  public boolean register(@RequestBody User newUser) {
    boolean bool = false;
    int result = userService.insertUser(newUser); // 渡ってきたパラメータを使ってusersテーブルにレコードを新規登録する
    if (result > 0) {
      User user = userService.findByUserNameAndPassword(newUser.getUserName(), newUser.getPassword());
      userService.setLoginSession(user); // loginセッションを更新する
      bool = true;
    }
    return bool;
  }

  /**
   * ユーザ情報変更
   *
   * @param f UserForm
   * @param m Model
   * @return mypage.html
   */
  @PostMapping(value = "/modify", consumes = { "multipart/form-data" })
  public String modify(UserForm f, Model m) {
    int userId = loginSession.getUserId(); // ユーザID取得
    f.setUserId(userId);
    userService.updateUser(f); // 修正されたユーザ情報を元にDB更新
    User user = userService.findByUserId(userId); //
    userService.setLoginSession(user); // logoinセッションを更新
    String image = userService.getUserImg(user); // プロフィール画像を取得
    boolean completeMsg = true; // 更新完了情報を追加
    m.addAttribute("user", user);
    m.addAttribute("image", image);
    m.addAttribute("loginSession", loginSession);
    m.addAttribute("completeMsg", completeMsg);
    return "mypage";
  }

  /**
   * プロフィール画像変更
   *
   * @param file MultipartFile
   * @param m Model
   * @return bool プロフィール画像変更成功/失敗
   */
  @PostMapping(value = "/imgUpload")
  @ResponseBody
  public boolean updateUserImg(@RequestParam("file") MultipartFile file, Model m) {
    boolean bool = false;
    int result = userService.updateUserImage(file); // プロフィール画像を更新
    if (result > 0) { bool = true; }
    return bool;
  }
}
