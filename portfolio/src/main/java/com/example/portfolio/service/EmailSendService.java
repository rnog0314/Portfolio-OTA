package com.example.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailSendService {

  @Autowired
  private MailSender sender;

  public void sendEmail(String to, String body, String topic) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("ryoto.noguchi@gmail");
    message.setTo(to);
    message.setSubject(topic);
    message.setText(body);
    sender.send(message);

  }

  public void send(String email) {
    String topic = "Your payment link is available now!";
    String body = "Thank you for booking our product. Here is a payament link of you reservation. Please click and follow the proeedure https://www.google.com";
    sendEmail(email, body, topic);
  }
}
