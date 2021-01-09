package com.example.portfolio.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "reservations")
@Getter @Setter @NoArgsConstructor
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "user_id")
  private int userId;

  @Column(name = "product_id")
  private int productId;

  @Column(name = "count")
  private int count;

  @Column(name = "date")
  private String date;

  @Column(name = "valid_flag", columnDefinition = "boolean default true", nullable = false)
  private boolean validFlag;

  @ManyToOne
  @JoinColumn(name = "product_id", insertable = false, updatable = false)
  private Product product;

  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;

  public Reservation(int userId, int productId, int count, String date) {
    this.userId = userId;
    this.productId = productId;
    this.count = count;
    this.date = date;
    this.validFlag = true;
  }
}
