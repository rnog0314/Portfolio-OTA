package com.example.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.portfolio.model.dao.ReservationRepository;
import com.example.portfolio.model.entity.Reservation;
import com.example.portfolio.model.form.ReservationForm;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.utils.Utils;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ReservationServiceTest {

  @Mock
  private LoginSession loginSession;

  @Mock
  private UserService userService;

  @Mock
  private Utils utils;

  @Mock
  private ReservationRepository rRepos;

  @InjectMocks
  private ReservationService rService;

  @Test
  public void testReserve() {
    when(loginSession.getUserId()).thenReturn(1);
    when(userService.findUserNameById(anyInt())).thenReturn("testUser");
    when(utils.getEndDate(anyString())).thenReturn("2020-02-04");
    Reservation expected = mock(Reservation.class);
    when(rRepos.save(expected)).thenReturn(expected);
    ReservationForm f = new ReservationForm("2020-12-31", 4, 5);
    Reservation actual = rService.reserve(f);
    assertEquals(expected, actual);

  }

}
