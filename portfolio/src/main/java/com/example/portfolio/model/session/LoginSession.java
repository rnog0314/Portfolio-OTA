package com.example.portfolio.model.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component
@SessionScope
@Data
public class LoginSession {
  private Integer userId;
	private Integer tmpUserId;
	private String userName;
  private String password;
  private String email;
	private boolean logined;
}
