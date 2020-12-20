package com.example.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/portfolio")
public class IndexController {

  @GetMapping("")
  public String index(Model m) {
    
    return "index";
  }


}
