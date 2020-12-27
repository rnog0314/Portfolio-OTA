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
@Table(name = "testimonials")
@Getter @Setter @NoArgsConstructor
public class Testimonials {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "testimonial_id")
  private Integer testimonialId;

  @Column(name = "testimonial_image")
  private String testimonialImage;

  @Column(name = "testimonial_title")
  private String testimonialTitle;

  @Column(name = "testimonial_text")
  private String testimonialText;

  @Column(name = "testimonial_review")
  private Integer testimonialReview;

  @Column(name = "delete_flag")
  private boolean deleteFlag;

}
