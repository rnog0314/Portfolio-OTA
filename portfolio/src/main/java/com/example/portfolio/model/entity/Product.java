package com.example.portfolio.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter @Setter @NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id")
  private Integer productId;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "category_id")
  private Integer categoryId;

  @Column(name = "product_image")
  private String productImage;

  @Column(name = "destination_id")
  private Integer destinationId;

  @Column(name = "delete_flag")
  private boolean deleteFlag;

  // @JoinColumnアノテーションは所有側に定義される。nameにはOne側と結合する際に必要となるカラム名をいれる。これを入力したら、One(被所有側)にmappedByをつける
  @ManyToOne(fetch = FetchType.LAZY) // One-To-Many/Many-To-Oneの関係ではMany側が所有側になる
  @JoinColumn(name = "category_id", insertable = false, updatable = false)
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY) // One-To-Many/Many-To-Oneの関係ではMany側が所有側になる
  @JoinColumn(name = "destination_id", insertable = false, updatable = false)
  private Destination destination;

}
