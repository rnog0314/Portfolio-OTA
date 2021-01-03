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
public class BookmarkDto {

  @Id
  private Integer id;
  private Integer userId;
  private Integer productId;
  private String productImage;
  private String productName;

}
