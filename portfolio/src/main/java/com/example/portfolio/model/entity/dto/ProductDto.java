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
public class ProductDto {
  @Id
  private int id;
  private String productName;
  private int categoryId;
  private String productImage;
  private int destinationId;
  private int price;
  private boolean deleteFlag;
}
