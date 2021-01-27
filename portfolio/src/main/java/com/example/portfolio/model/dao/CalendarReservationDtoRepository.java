package com.example.portfolio.model.dao;

import java.util.List;

import com.example.portfolio.model.entity.dto.CalendarReservationDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarReservationDtoRepository extends JpaRepository<CalendarReservationDto, Integer> {

  // findAll()を使ってしまうとReservationエンティティがOneToManyで連結しているProductとUserエンティを取得しようとN+1問題が発生するため、このように生SQLを使用しなければいけない
  @Query(value =  "SELECT r.id, r.start_date, r.end_date, r.title " +
                  "FROM reservations AS r " +
                  "INNER JOIN products AS p " +
                  "ON r.product_id = p.id " +
                  "INNER JOIN users AS u " +
                  "ON r.user_id = u.id ", nativeQuery = true)
  List<CalendarReservationDto> findAllByValidFlagTrue();

}
