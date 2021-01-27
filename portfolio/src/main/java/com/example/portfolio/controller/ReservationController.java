package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.ChargeRequest;
import com.example.portfolio.model.entity.Reservation;
import com.example.portfolio.model.entity.ReservationDto;
import com.example.portfolio.model.entity.ChargeRequest.Currency;
import com.example.portfolio.model.form.ReservationForm;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.EmailSendService;
import com.example.portfolio.service.ProductService;
import com.example.portfolio.service.ReservationService;
import com.example.portfolio.service.StripeService;
import com.example.portfolio.service.UserService;
import com.stripe.model.Charge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/portfolio/reservation")
public class ReservationController {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private ReservationService reservationService;

  @Autowired
  private UserService userService;

  @Autowired
  private EmailSendService emailSender;

  @Autowired
  private StripeService paymentsService;

  @Autowired
  private ProductService productService;

  // application.ymlに記載したStripeのパブリックキー
  @Value("${stripe.keys.public}")
  private String stripePublicKey;

  /**
   * 予約確認画面初期表示
   *
   * @param m Model
   * @return reservation_list.html
   */
  @GetMapping(value = "")
  public String init(Model m) {
    int userId = loginSession.getUserId();
    List<ReservationDto> reservationList = reservationService.getReservationList(userId);
    m.addAttribute("reservationList", reservationList);
    m.addAttribute("loginSession", loginSession);
    return "reservation_list";
  }

  /**
   * 仮予約
   *
   * @param reservationForm ReservationForm
   * @param m               Model
   * @return checkout.html
   */
  @PostMapping(value = "/reserve")
  public String reserve(ReservationForm reservationForm, Model m) {
    Reservation reserve = reservationService.reserve(reservationForm);
    int productId = reserve.getProductId();
    int price = productService.getPriceByProductId(productId);
    String imagePath = productService.getProductImageByProductId(productId);
    String productName = productService.getProductNameByProductId(productId);
    int amount = price * reserve.getCount();
    int id = reserve.getId();

    m.addAttribute("amount", amount * 100); // in Stripeはセントはでamoutを扱うため、✖️100
    m.addAttribute("imagePath", imagePath);
    m.addAttribute("id", id);
    m.addAttribute("productName", productName);
    m.addAttribute("stripePublicKey", stripePublicKey);
    m.addAttribute("currency", ChargeRequest.Currency.USD);
    m.addAttribute("loginSession", loginSession);
    return "checkout";
  }

  /**
   * 予約キャンセル
   *
   * @param reservationId 予約ID
   * @return bool キャンセル処理成功/失敗
   */
  @PostMapping(value = "/cancel")
  @ResponseBody
  public boolean cancel(@RequestBody int reservationId) {
    boolean bool = false;
    int result = reservationService.cancel(reservationId);
    if (result > 0) {
      bool = true;
    }
    return bool;
  }

  /**
   * 決済処理
   *
   * @param chargeRequest ChargeRequest
   * @param reservationId 予約ID
   * @param m             Model
   * @return result.html
   */
  @PostMapping("/charge")
  public String charge(ChargeRequest chargeRequest, @RequestParam("reservationId") int reservationId, Model m) {
    chargeRequest.setDescription("Example charge");
    chargeRequest.setCurrency(Currency.EUR);
    Charge charge = paymentsService.charge(chargeRequest);
    String email = userService.findEmailById(loginSession.getUserId());
    String id = charge.getId();
    String status = charge.getStatus();
    emailSender.send(email, id, status); // 予約確認メール送信
    reservationService.updateValidFlag(reservationId); // 仮予約を本予約に変更
    m.addAttribute("loginSession", loginSession);
    return "result";
  }

}
