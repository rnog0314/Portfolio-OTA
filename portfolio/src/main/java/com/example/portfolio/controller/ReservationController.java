package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.ChargeRequest;
import com.example.portfolio.model.entity.Reservation;
import com.example.portfolio.model.entity.ReservationDto;
import com.example.portfolio.model.entity.ChargeRequest.Currency;
import com.example.portfolio.model.form.ReservationForm;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.EmailSendService;
import com.example.portfolio.service.ReservationService;
import com.example.portfolio.service.StripeService;
import com.example.portfolio.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

  @Value("${stripe.keys.public}")
  private String stripePublicKey;

  Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

  @GetMapping(value = "")
  public String init(Model m) {
    int userId = loginSession.getUserId();
    List<ReservationDto> reservationList = reservationService.getReservationList(userId);
    String email = userService.findByUserId(userId).getEmail();
    m.addAttribute("email", email);
    m.addAttribute("reservationList", reservationList);
    m.addAttribute("loginSession", loginSession);
    return "reservation_list";
  }

  @PostMapping(value = "/reserve")
  public String reserve(ReservationForm reservationForm, Model m) {
    Reservation reserve = reservationService.reserve(reservationForm);
    boolean isReserved = false;
    if (!reserve.equals(null)) {
      isReserved = true;
    }
    List<ReservationDto> reservationList = reservationService.getReservationList(reserve.getUserId());
    m.addAttribute("reservationList", reservationList);
    m.addAttribute("loginSession", loginSession);
    m.addAttribute("isReserved", isReserved);
    return "redirect:/portfolio/reservation";
  }

  @PostMapping(value = "/delete")
  @ResponseBody
  public boolean delete(@RequestBody int reservationId) throws Exception {
    boolean bool = false;
    int result = reservationService.delete(reservationId);
    if (result > 0) {
      bool = true;
    }
    return bool;
  }

  @PostMapping("/charge")
  public String charge(ChargeRequest chargeRequest, Model model) throws StripeException {
    chargeRequest.setDescription("Example charge");
    chargeRequest.setCurrency(Currency.EUR);
    Charge charge = paymentsService.charge(chargeRequest);
    String email = userService.findEmailByUserId(loginSession.getUserId());
    String id = charge.getId();
    String status = charge.getStatus();
    emailSender.send(email, id, status);
    model.addAttribute("loginSession", loginSession);
    return "result";
  }

  @ExceptionHandler(StripeException.class)
  public String handleError(Model model, StripeException ex) {
    model.addAttribute("error", ex.getMessage());
    return "result";
  }
  
  // @PostMapping(value = "/sendEmail")
  // @ResponseBody
  // public boolean sendeEmail(@RequestBody String email) {

  //   boolean bool = true;
  //   try {
  //     emailSender.send(email);
  //     return bool;
  //   } catch (Exception e) {
  //     throw new RuntimeException(e);
  //   }
  // }

  @GetMapping(value="/fetchAll")
  @ResponseBody
  public String fetchAll() {
    List<Reservation> reservations = reservationService.findAll();
    return gson.toJson(reservations);
  }


  @PostMapping(value="/checkout")
  public String checkout( @RequestParam("amount") int amount,
                          @RequestParam("imagePath") String imagePath,
                          @RequestParam("productName") String productName,
                          Model model) {
    model.addAttribute("amount", amount * 100); // in cents
    model.addAttribute("imagePath", imagePath);
    model.addAttribute("productName", productName);
    model.addAttribute("stripePublicKey", stripePublicKey);
    model.addAttribute("currency", ChargeRequest.Currency.USD);
    model.addAttribute("loginSession", loginSession);
    return "checkout";
  }


}
