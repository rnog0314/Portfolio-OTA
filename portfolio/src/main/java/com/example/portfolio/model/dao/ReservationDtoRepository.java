package com.example.portfolio.model.dao;

import java.util.List;

import com.example.portfolio.model.entity.dto.ReservationDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDtoRepository extends JpaRepository<ReservationDto, Integer> {


  @Query(value =  "SELECT r.id, r.user_id, r.product_id, r.count, p.product_image, p.price,  p.product_name, r.start_date, u.user_name, r.valid_flag "
                + "FROM reservations AS r "
                + "INNER JOIN products AS p "
                + "ON r.product_id = p.id "
                + "INNER JOIN users AS u "
                + "ON r.user_id = u.id "
                + "WHERE r.user_id = :userId "
                + "AND r.valid_flag = true", nativeQuery = true)
	List<ReservationDto> getReservationList(@Param("userId") int userId);

  @Query(value =  "SELECT r.id, r.user_id, r.product_id, r.count, p.product_image, p.price,  p.product_name, r.start_date, u.user_name, r.valid_flag "
                + "FROM reservations AS r "
                + "INNER JOIN products AS p "
                + "ON r.product_id = p.id "
                + "INNER JOIN users AS u "
                + "ON r.user_id = u.id "
                + "WHERE r.valid_flag = true", nativeQuery = true)
  List<ReservationDto> findAllReservation();

}
