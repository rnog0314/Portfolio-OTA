package com.example.portfolio.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_details")
@Getter @Setter @NoArgsConstructor
public class ProductDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_detail_id")
  private Integer productDetailId;

  @Column(name = "product_id")
  private Integer productId;

  @Column(name = "image")
  private String image;

  @Column(name = "article_title")
  private String articleTitle;

  @Column(name = "article_text")
  private String articleText;

  @Column(name = "price")
  private Integer price;

}
