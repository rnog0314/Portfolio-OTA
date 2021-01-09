package com.example.portfolio.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  @Expose
  private int userId;

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
  @Type(type = "org.hibernate.type.BinaryType")
  @Column(name = "user_img")
  private byte[] userImg;

  @Column(name = "delete_flag")
  private boolean deleteFlag;

  @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
  private List<Reservation> reservations;

  @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
  private List<Bookmark> bookmarks;


}
