package com.example.portfolio.model.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@SessionScope
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LoginSession {
  private Integer userId;
	private int tmpUserId;
	private String userName;
  private String password;
  private String email;
	private boolean logined;
}
