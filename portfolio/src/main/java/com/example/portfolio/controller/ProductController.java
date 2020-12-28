package com.example.portfolio.controller;

import com.example.portfolio.model.entity.Product;
import com.example.portfolio.model.entity.ProductDetail;
import com.example.portfolio.service.ProductDetailService;
import com.example.portfolio.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/portfolio/product")
public class ProductController {

  @Autowired
  private ProductDetailService productDetailService;

  @Autowired
  private ProductService productService;

  @GetMapping(value="/{productId}")
  public String goProductDetailPage(@PathVariable("productId") int productId, Model m) {
    Product product = productService.findById(productId);
    ProductDetail productDetail = productDetailService.findByProductId(productId);
    m.addAttribute("productDetail", productDetail);
    m.addAttribute("product", product);
    return "product_detail";
  }

}
