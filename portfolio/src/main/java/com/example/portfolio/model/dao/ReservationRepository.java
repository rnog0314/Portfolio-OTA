package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	/**
	 * 予約IDを条件にして予約を削除
	 * @param reservationId
	 * @return 1
	 */
	@Modifying
	@Query(value = "DELETE FROM reservations WHERE id = :reservationId", nativeQuery = true)
	int deleteByReservationId(@Param("reservationId") int reservationId);

	/**
	 * 決済完了後に予約を確定する
	 * @param reservationId
	 */
	@Modifying
	@Query(value = "UPDATE reservations SET valid_flag = true WHERE id = :reservationId", nativeQuery = true)
	void updateValidFlag(@Param("reservationId") int reservationId);
}
