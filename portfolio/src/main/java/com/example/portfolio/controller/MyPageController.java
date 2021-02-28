package com.example.portfolio.controller;


import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portfolio/mypage")
public class MyPageController {

  @Autowired
  private UserService userService;

  @Autowired
  private LoginSession loginSession;

  /**
   * マイページ初期表示
   *
   * @param model Model
   * @return myapage.html
   */
  @GetMapping("")
  public String init(Model model) {
    User user = userService.findByUserId((int) loginSession.getUserId()); // ログインしているユーザーのユーザーIDを元に合致するユーザー情報取得
    byte[] bytes = user.getUserImg(); // 取得したユーザーのプロフィール画像のバイナリデータ取得
    if (bytes != null) {
      String image = userService.getUserImg(user);  // バイナリデータをStringに変換する
      model.addAttribute("image", image); 
    }
    model.addAttribute("user", user);
    model.addAttribute("loginSession", loginSession);
    return "mypage";
  }
}
