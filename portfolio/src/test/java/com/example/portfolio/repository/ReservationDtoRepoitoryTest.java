package com.example.portfolio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.ReservationDtoRepository;
import com.example.portfolio.model.entity.dto.ReservationDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Sql(scripts = "classpath:ReservationDtoRepoitoryTest.sql")
public class ReservationDtoRepoitoryTest {

  @Autowired
  private ReservationDtoRepository repos;

  @Test
  public void testGetReservationList() {
    int userId = 1;
    List<ReservationDto> actual = repos.getReservationList(userId);
    assertEquals(2, actual.size());
  }


}
