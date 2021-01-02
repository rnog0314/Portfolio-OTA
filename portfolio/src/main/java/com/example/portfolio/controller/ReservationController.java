package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.Reservation;
import com.example.portfolio.model.entity.ReservationDto;
import com.example.portfolio.model.form.ReservationForm;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @GetMapping(value = "")
  public String postMethodName(Model m) {
    List<ReservationDto> reservationList = reservationService.getReservationList(loginSession.getUserId());
    m.addAttribute("reservationList", reservationList);
    m.addAttribute("loginSession", loginSession);
    return "reservation_list";
  }

  @PostMapping(value = "/reserve")
  public String reserve(ReservationForm reservationForm, Model m) {
    System.out.println(reservationForm.getProductId());
    Reservation reserve = reservationService.reserve(reservationForm);
    boolean isReserved = false;
    if (!reserve.equals(null)) {
      isReserved = true;
    }
    List<ReservationDto> reservationList = reservationService.getReservationList(reserve.getUserId());
    m.addAttribute("reservationList", reservationList);
    m.addAttribute("loginSession", loginSession);
    m.addAttribute("isReserved", isReserved);
    return "reservation_list";
  }

}
