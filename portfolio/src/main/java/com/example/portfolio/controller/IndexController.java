package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.Category;
import com.example.portfolio.model.entity.Destination;
import com.example.portfolio.model.entity.Product;
import com.example.portfolio.model.entity.Testimonial;
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

  @GetMapping(value = "")
  public String index(Model m) {
    List<Product> products = productService.findAll();
    List<Category> categories = categoryService.findAll();
    for (Category category : categories) {
      System.out.println("取得したカテゴリID : " + category.getCategoryId());
      System.out.println("取得したカテゴリ名 : " + category.getCategoryName());
    }
    List<Destination> destinations = destinationService.findAll();
    for (Destination destination : destinations) {
      System.out.println("取得したカテゴリ名 : " + destination.getDestinationName());
      System.out.println("取得したカテゴリID : " + destination.getDestinationImage());
    }
    List<Testimonial> testimonials = testimonialService.findAll();
    m.addAttribute("products", products);
    m.addAttribute("categories", categories);
    m.addAttribute("destinations", destinations);
    m.addAttribute("testimonials", testimonials);
    return "index";
  }




}
