package com.example.portfolio.controller;

import com.example.portfolio.model.entity.ChargeRequest;
import com.example.portfolio.model.entity.ChargeRequest.Currency;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portfolio/charge")
public class ChargeController {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private StripeService paymentsService;

  /**
   * 決済
   * @param chargeRequest
   * @param model
   * @return 決済結果ページ result.html
   * @throws StripeException
   */
  @PostMapping("")
  public String charge(ChargeRequest chargeRequest, Model model) throws StripeException {
    chargeRequest.setDescription("Example charge");
    chargeRequest.setCurrency(Currency.EUR);
    Charge charge = paymentsService.charge(chargeRequest);
    model.addAttribute("id", charge.getId());
    model.addAttribute("status", charge.getStatus());
    model.addAttribute("chargeId", charge.getId());
    model.addAttribute("balance_transaction", charge.getBalanceTransaction());
    model.addAttribute("loginSession", loginSession);
    return "result";
  }

  /**
   * 例外ハンドラー
   * @param model
   * @param ex
   * @return
   */
  @ExceptionHandler(StripeException.class)
  public String handleError(Model model, StripeException ex) {
    model.addAttribute("error", ex.getMessage());
    return "result";
  }
}
