package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.Category;
import com.example.portfolio.model.entity.Destination;
import com.example.portfolio.model.entity.Testimonial;
import com.example.portfolio.model.entity.dto.ProductDto;
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
    if (!loginSession.isLogined() && loginSession.getTmpUserId() == 0) {
      int tempUserId = (int) (Math.random() * 100000);
      loginSession.setTmpUserId(tempUserId); // ランダムな整数を仮ユーザIDとしてログインセッションに登録
      loginSession.setLogined(false);
    }
    List<ProductDto> products = productService.findAll(); // トップページ表示する商品一覧取得
    List<Category> categories = categoryService.findAll(); // カテゴリ全取得
    List<Destination> destinations = destinationService.findAll(); // デスティネーション全取得
    List<Testimonial> testimonials = testimonialService.findAll(); // testimonial全取得
    m.addAttribute("products", products);
    m.addAttribute("categories", categories);
    m.addAttribute("destinations", destinations);
    m.addAttribute("testimonials", testimonials);
    m.addAttribute("loginSession", loginSession);
    return "index";
  }

}
