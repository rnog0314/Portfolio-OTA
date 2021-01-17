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
import com.example.portfolio.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
  private UserService userService;

  @Autowired
  private Utils utils;

  /**
   * 予約の挿入
   *
   * @param f ReservationForm
   * @return Reservation
   */
  public Reservation reserve(ReservationForm f) {
    int userId = loginSession.getUserId();
    int productId = f.getProductId();
    int count = f.getCount();
    String title = userService.findUserNameByUserId(userId) + " : " + count + "PAX";
    String start = f.getDate();
    String end = utils.getEndDate(start);
    Reservation reservation = new Reservation(userId, productId, count, start, end, title);
    return reservationRepos.save(reservation);
  }

  /**
   * 予約リスト取得
   *
   * @param userId ユーザID
   * @return 予約リスト
   */
  public List<ReservationDto> getReservationList(int userId) {
    return reservationDtoRepos.getReservationList(userId);
  }

  /**
   * 予約削除
   *
   * @param reservationId 予約ID
   * @return 削除件数
   */
  public int cancel(int reservationId) {
    try {
      return reservationRepos.deleteByReservationId(reservationId);
    } catch (IllegalArgumentException e) {
      throw new RuntimeException();
    }
  }

  /**
   * ページネーションされた予約リスト取得
   *
   * @param page リクエストされたページ番号
   * @return ページネーションされた予約リスト
   */
  public Page<Reservation> findPaginatedList(Optional<Integer> page) {
    Pageable pageable = utils.getPageable(page);
    return reservationRepos.findAll(pageable);
  }

  /**
   * 予約リストの全取得
   *
   * @return 全予約リスト
   */
  public List<Reservation> findAll() {
    return reservationRepos.findAllByValidFlagTrue();
  }

  /**
   * 予約有効フラグの更新
   * @param reservationId 予約ID
   */
  public void updateValidFlag(int reservationId) {
    reservationRepos.updateValidFlag(reservationId);
  }


}
