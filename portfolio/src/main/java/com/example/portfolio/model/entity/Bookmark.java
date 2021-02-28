package com.example.portfolio.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookmarks")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Bookmark {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "user_id")
  private int userId;

  @Column(name = "product_id")
  private int productId;

  @ManyToOne
  @JoinColumn(name = "product_id", insertable = false, updatable = false)
  private Product product;

  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;

  /**
   * ブックマーク登録時にインスタンスを生成する
   * @param userId
   * @param productId
   */
  public Bookmark(int userId, int productId) {
    this.userId = userId;
    this.productId = productId;
  }

}
