package com.example.portfolio.model.dao;

import java.util.List;

import com.example.portfolio.model.entity.dto.ProductDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDtoRepository extends JpaRepository<ProductDto, Integer> {

  @Query(value = "SELECT * FROM products WHERE category_id = :categoryId", nativeQuery = true)
  List<ProductDto> findByCategoryId(int categoryId);

  @Query(value = "SELECT * FROM products WHERE id = :productId", nativeQuery = true)
  ProductDto findProductById(int productId);

}
