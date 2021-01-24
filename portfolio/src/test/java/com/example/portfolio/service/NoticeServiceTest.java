package com.example.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;

import com.example.portfolio.model.dao.NoticeRepository;
import com.example.portfolio.model.entity.Notice;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.Mock;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class NoticeServiceTest {

  @Mock
  private NoticeRepository repos;

  @InjectMocks
  private NoticeService service;

  @Test
  public void testInsert() {
    Notice expected = new Notice();
    when(repos.save(any())).thenReturn(expected);
    Notice notice = new Notice("title", "text");
    Notice actual = service.insert(notice);
    assertEquals(expected, actual);
  }

}
