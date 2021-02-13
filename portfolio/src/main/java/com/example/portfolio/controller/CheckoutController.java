package com.example.portfolio.controller;

import com.example.portfolio.model.entity.ChargeRequest;
import com.example.portfolio.model.session.LoginSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/portfolio/checkout")
public class CheckoutController {

  @Value("${stripe.keys.public}")
  private String stripePublicKey;

  @Autowired
  private LoginSession loginSession;

  /**
   * 決済ページ初期表示
   * @param model
   * @return checkout.html
   */
  @GetMapping(value="")
  public String checkout(Model model) {
    model.addAttribute("amount", 50 * 100); // in cents
    model.addAttribute("stripePublicKey", stripePublicKey);
    model.addAttribute("currency", ChargeRequest.Currency.EUR);
    model.addAttribute("loginSession", loginSession);
    return "checkout";
  }



}
