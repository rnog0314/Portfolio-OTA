package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.Testimonial;
import com.example.portfolio.model.session.AdminSession;
import com.example.portfolio.service.TestimonialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/portfolio/admin/testimonial")
public class AdminTestimonialController {

  @Autowired
  private AdminSession adminSession;

  @Autowired
  private TestimonialService testimonialService;

  /**
   * お客様の声一覧表示
   *
   * @param m Model
   * @return admin/testimonial.html
   */
  @GetMapping(value = "")
  public String init(Model m) {
    List<Testimonial> testimonials = testimonialService.findAll(); // testimonialsテーブルに存在する全てのレコードを取得する
    m.addAttribute("testimonials", testimonials);
    m.addAttribute("adminSession", adminSession);
    return "admin/testimonial";
  }
}
