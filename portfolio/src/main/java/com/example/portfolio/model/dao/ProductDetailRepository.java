package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.ProductDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {

	ProductDetail findByProductId(int productId);

}
