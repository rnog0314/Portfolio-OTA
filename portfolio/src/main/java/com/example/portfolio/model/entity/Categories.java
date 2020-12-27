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
@Table(name = "categories")
@Getter @Setter @NoArgsConstructor
public class Categories {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Integer categoryId;

  @Column(name = "category_name")
  private String categoryName;

  @Column(name = "category_image")
  private String categoryImage;

  @Column(name = "delete_flag")
  private boolean deleteFlag;

  @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL) // mappedByには所有側の持つこのエンティティのインスタンス名をいれる
  private List<Products> productList;


}
