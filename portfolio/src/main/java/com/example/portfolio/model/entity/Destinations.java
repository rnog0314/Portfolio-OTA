package com.example.portfolio.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "destinations")
@Getter @Setter @NoArgsConstructor
public class Destinations {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "destination_id")
  private Integer destinationId;

  @Column(name = "destination_name")
  private String destinationName;

  @Column(name = "destination_image")
  private String destinationImage;

  @Column(name = "delete_flag")
  private boolean deleteFlag;

  @OneToMany(mappedBy = "destinations", cascade = CascadeType.ALL) // mappedByには所有側の持つこのエンティティのインスタンス名をいれる
  private List<Products> productList;

}
