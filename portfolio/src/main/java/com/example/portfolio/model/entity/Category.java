package com.example.portfolio.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter @Setter @NoArgsConstructor
public class Category {

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

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) // mappedByには所有側の持つこのエンティティのインスタンス名をいれる
  private List<Product> productList;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "category_id") // categorysテーブルがcategory_detailsテーブルの所有テーブルであり、結合する際のこのエンティティのカラムをnameに指定する
  private CategoryDetail categoryDetail;


}
