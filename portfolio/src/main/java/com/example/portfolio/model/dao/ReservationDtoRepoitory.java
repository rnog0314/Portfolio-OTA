package com.example.portfolio.model.dao;

import java.util.List;

import com.example.portfolio.model.entity.ReservationDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDtoRepoitory extends JpaRepository<ReservationDto, Integer> {


  @Query(value =  "SELECT r.id, p.product_image, r.count, p.product_id, pd.price,  p.product_name, r.start_date, u.user_name "
                + "FROM reservations AS r "
                + "INNER JOIN products AS p "
                + "ON r.product_id = p.product_id "
                + "INNER JOIN product_details AS pd "
                + "ON r.product_id = pd.product_id "
                + "INNER JOIN users AS u "
                + "ON r.user_id = u.user_id "
                + "WHERE r.user_id = :userId "
                + "AND r.valid_flag = true", nativeQuery = true)
	List<ReservationDto> getReservationList(int userId);

}
