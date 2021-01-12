package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.Category;
import com.example.portfolio.model.entity.Destination;
import com.example.portfolio.model.entity.Product;
import com.example.portfolio.model.entity.Testimonial;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.CategoryService;
import com.example.portfolio.service.DestinationService;
import com.example.portfolio.service.ProductService;
import com.example.portfolio.service.TestimonialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/portfolio")
public class IndexController {

  @Autowired
  CategoryService categoryService;

  @Autowired
  DestinationService destinationService;

  @Autowired
  ProductService productService;

  @Autowired
  TestimonialService testimonialService;

  @Autowired
  private LoginSession loginSession;

  /**
   * トップページ初期表示
   * @param m Model
   * @return index.html
   */
  @GetMapping(value = "")
  public String init(Model m) {
     // ログインしてない&仮ユーザIDがない(=初めてページを開いたとき)
    if (!loginSession.isLogined() && loginSession.getTmpUserId() == null) {
      int tempUserId = (int) (Math.random() * 1000000000);
      loginSession.setTmpUserId(tempUserId); // ランダムな整数を仮ユーザIDとしてログインセッションに登録
    }
    List<Product> products = productService.findAll();
    List<Category> categories = categoryService.findAll();
    List<Destination> destinations = destinationService.findAll();
    List<Testimonial> testimonials = testimonialService.findAll();
    m.addAttribute("products", products);
    m.addAttribute("categories", categories);
    m.addAttribute("destinations", destinations);
    m.addAttribute("testimonials", testimonials);
    m.addAttribute("loginSession", loginSession);
    return "index";
  }

}

/* TODO
  - トップスライダー画像を画質の良いものに変更
  - JSファイルの分離
  - 各ページのaタグの色修正
  - 各ページのimgのbdrsの修正
  - try-catchの確認
*/

/* 残りの作成工程
  - 単体テスト(JUnit)
  - 統合テスト(統合試験仕様書をページごとに簡単に作成の上、試験)
  - README作成
  - ⭐️AWSとDockerを使ってデプロイ
  - ER図作成
  - 画面設計書作成
*/
