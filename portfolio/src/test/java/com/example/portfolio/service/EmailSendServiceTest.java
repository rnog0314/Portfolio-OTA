package com.example.portfolio.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class EmailSendServiceTest {

  @Mock
  private SimpleMailMessage msg;

  @Mock
  private MailSender sender;

  @InjectMocks
  private EmailSendService emailSender;

  @Test
  public void testSend() {
    doNothing().when(msg).setTo(anyString());
    doNothing().when(msg).setSubject(anyString());
    doNothing().when(msg).setText(anyString());
    doNothing().when(sender).send(msg);
    String email = "abc1234@example.com";
    String id = "abcde0001";
    String status = "Success";
    emailSender.send(email, id, status);
  }

}
