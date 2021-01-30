package com.example.portfolio.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.portfolio.model.entity.Reservation;
import com.example.portfolio.model.entity.dto.ReservationDto;
import com.example.portfolio.model.session.AdminSession;
import com.example.portfolio.service.ReservationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/portfolio/admin/reservation")
public class AdminReservationController {
  @Autowired
  private AdminSession adminSession;

  @Autowired
  private ReservationService reservationService;

  Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

  /**
   * 管理者/予約情報一覧表示
   * @param page リクエストされたページ番号
   * @param m Model
   * @param r Reservation
   * @return admin/reservation.html
   */
  @GetMapping(value = { "", "/{page:^[1-9][0-9]*$}" })
  public String init(@PathVariable(name = "page") Optional<Integer> page, Model m, Reservation r) {
    Page<ReservationDto> reservations = reservationService.findPaginatedList(page);
    int lastPage = reservations.getTotalPages();
    if (lastPage > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, lastPage).boxed().collect(Collectors.toList()); // HTMLでページ分ループするために各ページ番号が入ったリストを作成
      m.addAttribute("pageNumbers", pageNumbers);
    }
    m.addAttribute("reservations", reservations);
    m.addAttribute("adminSession", adminSession);
    return "admin/reservation_list";
  }

  @GetMapping("/calendar")
  public String showCalendar(Model m) {
    m.addAttribute("adminSession", adminSession);
    return "admin/reservation";
  }

  /**
   * DBから予約情報取得しカレンダーに表示
   * @return reservations 全予約情報
   */
  @GetMapping(value = "/fetchAll")
  @ResponseBody
  public String fetchAll() {
    List<Reservation> reservations = reservationService.findAllForCalendar();
    return gson.toJson(reservations);
  }

}
