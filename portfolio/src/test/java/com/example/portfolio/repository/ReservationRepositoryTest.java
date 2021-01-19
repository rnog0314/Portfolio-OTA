package com.example.portfolio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.ReservationRepository;
import com.example.portfolio.model.entity.Reservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Sql
public class ReservationRepositoryTest {

  @Autowired
  private ReservationRepository repos;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void testDeleteByReservationId() {
    int reservationId = 1;
    int actual = repos.deleteByReservationId(reservationId);
    assertEquals(1, actual);

    String sql = "SELECT * FROM reservations WHERE id = ?";
    RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<Reservation>(Reservation.class);
    List<Reservation> updated = jdbcTemplate.query(sql, rowMapper, reservationId);
    assertEquals(0, updated.size());
  }
}
