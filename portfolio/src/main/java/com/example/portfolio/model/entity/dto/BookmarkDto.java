package com.example.portfolio.model.entity.dto;

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
  // 画面表示用のDTO
  @Id
  private int id;
  private int userId;
  private int productId;
  private String productImage;
  private String productName;

}
