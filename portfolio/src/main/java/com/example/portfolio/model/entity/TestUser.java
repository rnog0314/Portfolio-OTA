package com.example.portfolio.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "test_users")
@Getter @Setter @NoArgsConstructor
public class TestUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  @Expose
  private Integer userId;

  @Column(name = "user_name")
  @Expose
  private String userName;

  @Column(name = "password")
  @Expose
  private String password;

  @Column(name = "email")
  @Expose
  private String email;

  @Column(name = "family_name")
  private String familyName;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "gender")
  private char gender;

  @Lob
	@Column(name = "user_img", columnDefinition = "MEDIUMBLOB")
  private String UserImg;

  @Column(name = "delete_flag")
  private boolean deleteFlag;


}
