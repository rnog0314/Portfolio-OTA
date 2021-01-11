package com.example.portfolio.controller;

import com.example.portfolio.model.entity.CategoryDetail;
import com.example.portfolio.model.entity.Category;
import com.example.portfolio.model.entity.Product;
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
    Category category = categoryService.findById(categoryId);
    CategoryDetail categoryDetail = categoryDetailService.findById(categoryId);
    List<Product> products = productService.findByCategoryId(categoryId);
    m.addAttribute("category", category);
    m.addAttribute("categoryDetail", categoryDetail);
    m.addAttribute("products", products);
    m.addAttribute("loginSession", loginSession);
    return "category_detail";
  }

}
