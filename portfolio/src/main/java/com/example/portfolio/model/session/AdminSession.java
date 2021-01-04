package com.example.portfolio.model.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component
@SessionScope
@Data
public class AdminSession {
  private Integer adminId;
	private String adminName;
  private String password;
	private boolean logined = false;
}
