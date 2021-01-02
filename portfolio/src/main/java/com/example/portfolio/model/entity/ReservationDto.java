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
  private Integer id;
  private Integer productId;
  private String productImage;
  private Integer count;
  private Integer price;
  private String productName;
  private String date;

}
