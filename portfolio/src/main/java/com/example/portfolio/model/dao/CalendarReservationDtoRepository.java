package com.example.portfolio.model.dao;

import java.util.List;

import com.example.portfolio.model.entity.dto.CalendarReservationDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarReservationDtoRepository extends JpaRepository<CalendarReservationDto, Integer> {


  /**
   * カレンダー表示用の全予約情報
   * @return List<CalendarReservationDto>
   */
  // findAll()を使ってしまうとReservationエンティティがOneToManyで連結しているProductとUserエンティを取得しようとN+1問題が発生するため、このように生SQLを使用しなければいけない
  @Query(value =  "SELECT id, start_date, end_date, title, count, valid_flag FROM reservations WHERE valid_flag = TRUE", nativeQuery = true)
  List<CalendarReservationDto> findAllByValidFlagTrue();

}
