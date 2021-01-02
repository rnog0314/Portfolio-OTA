package com.example.portfolio.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.ReservationDtoRepoitory;
import com.example.portfolio.model.dao.ReservationRepository;
import com.example.portfolio.model.entity.Reservation;
import com.example.portfolio.model.entity.ReservationDto;
import com.example.portfolio.model.form.ReservationForm;
import com.example.portfolio.model.session.LoginSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ReservationService {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private ReservationRepository reservationRepos;

  @Autowired
  private ReservationDtoRepoitory reservationDtoRepos;

  public Reservation reserve(ReservationForm f) {
    Integer userId = loginSession.getUserId();
    Integer productId = f.getProductId();
    Integer count = f.getCount();
    String date = f.getDate();
    System.out.println("受け取った日にち：" + date);
    Reservation reservation = new Reservation(userId, productId, count, date);
    return reservationRepos.save(reservation);
  }

  public List<ReservationDto> getReservationList(Integer userId) {
    return reservationDtoRepos.getReservationList(userId);
  }

}
