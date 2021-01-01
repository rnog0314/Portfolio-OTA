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
   * 新規ユーザー登録ページ初期表示メソッド
   *
   * @param model
   * @return
   */
  @GetMapping("")
  public String goUserRegisterPage(Model model) {
    model.addAttribute("loginSession", loginSession);
    return "user_register";
  }

  /**
   * ユーザ名重複確認メソッド
   *
   * @param newUserName
   * @return
   */
  @PostMapping("/check")
  @ResponseBody
  public boolean checkUserName(@RequestBody String newUserName) {
    boolean bool = false; // 重複があるかどうか真偽値をfalseで初期化
    int count = userService.findByUserName(newUserName);
    if (count > 0) { // 同一のユーザ名が既に存在していたらtrueを返してエラーを表示
      bool = true;
    } else {
      bool = false; // 同一のユーザ名が存在していなければfalseを返して続行させる
    }
    return bool;
  }

  /**
   * 新規ユーザー登録メソッド
   *
   * @param user
   * @return
   */
  @PostMapping("/register")
  @ResponseBody
  public boolean registerUser(@RequestBody User newUser) {
    boolean bool = false;
    int result = userService.insertUser(newUser);
    if (result > 0) {
      User user = userService.findByUserNameAndPassword(newUser.getUserName(), newUser.getPassword());
      userService.setLoginSession(user);
      bool = true;
    }
    return bool;
  }

  /**
   * ユーザ情報変更
   *
   * @param userForm
   * @param file
   * @param m
   * @return
   */
  // @PostMapping(value = "/modify", consumes = { "multipart/form-data" })
  // public String modify(UserForm userForm, @RequestPart("file") MultipartFile file, Model m) {

  //   int result = userService.update(userForm, file, loginSession.getUserId());
  //   if (result > 0) {
  //     System.out.println(result + "レコード更新しました");
  //   }

  //   User user = userService.findByUserId(loginSession.getUserId());
  //   userService.setLoginSession(user);
  //   m.addAttribute("user", user);
  //   m.addAttribute("loginSession", loginSession);
  //   Boolean completeMsg = true;
  //   m.addAttribute("completeMsg", completeMsg);
  //   return "mypage";
  // }


  @PostMapping(value = "/modify", consumes = { "multipart/form-data" })
  public String modify(UserForm userForm, Model m) {
    Integer userId = loginSession.getUserId();
    userForm.setUserId(userId);
    userService.updateUser(userForm);

    User user = userService.findByUserId(userId);
    userService.setLoginSession(user);
    String image = userService.getUserImg(user);
    Boolean completeMsg = true;
    m.addAttribute("user", user);
    m.addAttribute("image", image);
    m.addAttribute("loginSession", loginSession);
    m.addAttribute("completeMsg", completeMsg);
    return "mypage";
  }

  /**
   * プロフィール画像変更
   *
   * @param file
   * @param m
   * @return
   */
  // @PostMapping(value = "/imgRegis")
  // @ResponseBody
  // public boolean imgRegister(@RequestParam("file") MultipartFile file, Model m) {
  //   boolean bool = false;
  //   try {
  //     userService.saveUserImg(file);
  //     bool = true;
  //   } catch (Exception e) {
  //     e.printStackTrace();
  //   }
  //   return bool;
  // }

  @PostMapping(value = "/imgUpload")
  @ResponseBody
  public boolean imgUpdate(@RequestParam("file") MultipartFile file, Model m) {
    boolean bool = false;
    int result = userService.updateUserImage(file);
    if (result > 0) { bool = true; }
    return bool;
  }
}
