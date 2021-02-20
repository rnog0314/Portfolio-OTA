package com.example.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailSendService {

  @Autowired
  private MailSender sender;

  /**
   * メール送信準備
   *
   * @param email  メールアドレス
   * @param id     決済ID
   * @param status 決済状況
   */
  public void send(String email, String id, String status) {
    String topic = "Thank you for booking our product!!"; // 件名
    String body = "Your payment ID : " + id + "\n" + "Payament status : " + status; // 本文
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email);
    message.setSubject(topic);
    message.setText(body);
    sender.send(message);
  }

}
