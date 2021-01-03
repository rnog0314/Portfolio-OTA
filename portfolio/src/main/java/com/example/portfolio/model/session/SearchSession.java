package com.example.portfolio.model.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component
@SessionScope
@Data
public class SearchSession {
  private String keyword;
}
