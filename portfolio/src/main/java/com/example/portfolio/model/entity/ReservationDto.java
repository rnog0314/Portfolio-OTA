package com.example.portfolio.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReservationDto {

  @Id
  private int id;
  private int productId;
  private String productImage;
  private int count;
  private int price;
  private String productName;
  private String startDate;
  private String userName;
  // private String title;

  // public void setTitle(String userName, int count) {
  //   this.title = userName + ":" + count + "PAX";
  // }

}
