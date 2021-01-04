package com.example.portfolio.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.ReservationDtoRepoitory;
import com.example.portfolio.model.dao.ReservationRepository;
import com.example.portfolio.model.entity.Reservation;
import com.example.portfolio.model.entity.ReservationDto;
import com.example.portfolio.model.form.ReservationForm;
import com.example.portfolio.model.session.LoginSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

  @Autowired
  private ProductService productService;


  public Reservation reserve(ReservationForm f) {
    Integer userId = loginSession.getUserId();
    Integer productId = f.getProductId();
    Integer count = f.getCount();
    String date = f.getDate();
    Reservation reservation = new Reservation(userId, productId, count, date);
    return reservationRepos.save(reservation);
  }

  public List<ReservationDto> getReservationList(Integer userId) {
    return reservationDtoRepos.getReservationList(userId);
  }

  public int delete(Integer reservationId) {
    try {
      return reservationRepos.deleteByReservationId(reservationId);
    } catch (IllegalArgumentException e) {
      throw new RuntimeException(e);
    }
  }

  public Page<Reservation> findPaginatedList(Optional<Integer> page) {
    int currentPage = productService.getCurrentPage(page);
    Sort sort = Sort.by("id").ascending(); // ソートのルールを作成
    Pageable pageable = PageRequest.of(currentPage - 1, 10, sort); // ページネーション情報作成
    return reservationRepos.findAll(pageable);
  }

}
