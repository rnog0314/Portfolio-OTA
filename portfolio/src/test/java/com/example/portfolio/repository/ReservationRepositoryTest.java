package com.example.portfolio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    int id = 1;
    int actual = repos.deleteByReservationId(id);
    assertEquals(1, actual);

    String sql = "SELECT * FROM reservations WHERE id = ?";
    RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<Reservation>(Reservation.class);
    List<Reservation> updated = jdbcTemplate.query(sql, rowMapper, id);
    assertEquals(0, updated.size());
  }

  @Test
  public void testUpdateValidFlag() {
    int id = 3;
    repos.updateValidFlag(id);
    String sql = "SELECT valid_flag FROM reservations WHERE id = ?";
    RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<Reservation>(Reservation.class);
    Reservation actual = jdbcTemplate.queryForObject(sql, rowMapper, id);
    assertTrue(actual.isValidFlag());
  }
}
