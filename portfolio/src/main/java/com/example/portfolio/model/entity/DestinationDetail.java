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
@Table(name = "destination_details")
@Getter @Setter @NoArgsConstructor
public class DestinationDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "destination_detail_id")
  private int destinationDetailId;

  @Column(name = "destination_id")
  private int destinationId;

  @Column(name = "image1")
  private String image1;

  @Column(name = "article_title1")
  private String articleTitle1;

  @Column(name = "article_text1")
  private String articleText1;

  @Column(name = "image2")
  private String image2;

  @Column(name = "article_title2")
  private String articleTitle2;

  @Column(name = "article_text2")
  private String articleText2;

  @Column(name = "image3")
  private String image3;

  @Column(name = "article_title3")
  private String articleTitle3;

  @Column(name = "article_text3")
  private String articleText3;

}
