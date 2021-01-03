package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.Reservation;
import com.example.portfolio.model.entity.ReservationDto;
import com.example.portfolio.model.form.ReservationForm;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.EmailSendService;
import com.example.portfolio.service.ReservationService;
import com.example.portfolio.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @GetMapping(value = "")
  public String init(Model m) {
    Integer userId = loginSession.getUserId();
    List<ReservationDto> reservationList = reservationService.getReservationList(userId);
    String email = userService.findEmailByUserId(userId).getEmail();
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
  public Boolean delete(@RequestBody Integer reservationId) throws Exception {
    Boolean bool = false;
    int result = reservationService.delete(reservationId);
    if (result > 0) {
      bool = true;
    }
    return bool;
  }

  @PostMapping(value = "/sendEmail")
  @ResponseBody
  public Boolean sendeEmail(@RequestBody String email) {

    Boolean bool = true;
    try {
      emailSender.send(email);
      return bool;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
