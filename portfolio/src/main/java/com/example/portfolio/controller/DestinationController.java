package com.example.portfolio.controller;

import com.example.portfolio.model.entity.DestinationDetail;
import com.example.portfolio.model.entity.Product;
import com.example.portfolio.model.session.LoginSession;

import java.util.List;

import com.example.portfolio.model.entity.Destination;
import com.example.portfolio.service.DestinationDetailService;
import com.example.portfolio.service.DestinationService;
import com.example.portfolio.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portfolio/destination")
public class DestinationController {

  @Autowired
  private DestinationDetailService destinationDetailService;

  @Autowired
  private DestinationService destinationService;

  @Autowired
  private ProductService productService;

  @Autowired
  private LoginSession loginSession;

  /**
   * 行き先詳細ページ初期表示
   * @param destinationId デスティネーションID
   * @param m Model
   * @return destination_detail.html
   */
  @GetMapping(value = "/{init}")
  public String init(@PathVariable("init") int destinationId, Model m) {
    Destination destination = destinationService.findById(destinationId);
    DestinationDetail destinationDetail = destinationDetailService.findById(destinationId);
    List<Product> products = productService.findByDestinationId(destinationId);
    m.addAttribute("destination", destination);
    m.addAttribute("destinationDetail", destinationDetail);
    m.addAttribute("products", products);
    m.addAttribute("loginSession", loginSession);
    return "destination_detail";
  }

}
