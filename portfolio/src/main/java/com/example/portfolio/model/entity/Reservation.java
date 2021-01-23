package com.example.portfolio.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reservations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Expose
  private int id;

  @Column(name = "user_id")
  @Expose
  private int userId;

  @Column(name = "product_id")
  @Expose
  private int productId;

  @Column(name = "count")
  @Expose
  private int count;

  @Column(name = "start_date")
  @Expose
  private String start;

  @Column(name = "end_date")
  @Expose
  private String end;

  @Column(name = "title")
  @Expose
  private String title;

  @Column(name = "valid_flag", columnDefinition = "boolean default true", nullable = false)
  private boolean validFlag;

  @ManyToOne
  @JoinColumn(name = "product_id", insertable = false, updatable = false)
  private Product product;

  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;

  public Reservation(int userId, int productId, int count, String start, String end, String title) {
    this.userId = userId;
    this.productId = productId;
    this.count = count;
    this.start = start;
    this.end = end;
    this.title = title;
    this.validFlag = false;
  }
}
