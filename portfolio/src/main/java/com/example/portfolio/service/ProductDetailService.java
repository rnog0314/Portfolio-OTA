package com.example.portfolio.service;

import com.example.portfolio.model.dao.ProductDetailRepository;
import com.example.portfolio.model.entity.ProductDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductDetailService {

  @Autowired
  private ProductDetailRepository productDetailRepos;

  /**
   * 商品詳細取得
   * @param productId 商品ID
   * @return ProductDetail 商品詳細
   */
  public ProductDetail findByProductId(int productId) {
    return productDetailRepos.findByProductId(productId);
  }
}
