package com.example.portfolio.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserForm {

  private int userId;
  private String userName;
  private String email;
  private String password;
  private String familyName;
  private String firstName;

}
