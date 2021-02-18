package com.example.portfolio.controller;

import com.example.portfolio.model.entity.CategoryDetail;
import com.example.portfolio.model.entity.Category;
import com.example.portfolio.model.entity.dto.ProductDto;
import com.example.portfolio.model.session.LoginSession;

import java.util.List;

import com.example.portfolio.service.CategoryDetailService;
import com.example.portfolio.service.CategoryService;
import com.example.portfolio.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portfolio/category")
public class CategoryController {

  @Autowired
  private CategoryDetailService categoryDetailService;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private ProductService productService;

  @Autowired
  private LoginSession loginSession;

  /**
   * カテゴリ詳細ページ初期表示
   * @param categoryId カテゴリID
   * @param m Model
   * @return category_detail.html
   */
  @GetMapping(value = "/{id}")
  public String init(@PathVariable("id") int categoryId, Model m) {
    Category category = categoryService.findById(categoryId); // カテゴリ取得
    CategoryDetail categoryDetail = categoryDetailService.findById(categoryId); // カテゴリ詳細情報取得
    List<ProductDto> products = productService.findByCategoryId(categoryId); // カテゴリIDを元に商品詳細を取得
    m.addAttribute("category", category);
    m.addAttribute("categoryDetail", categoryDetail);
    m.addAttribute("products", products);
    m.addAttribute("loginSession", loginSession);
    return "category_detail";
  }

}
