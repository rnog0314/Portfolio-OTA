package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT price FROM products WHERE id = :productId", nativeQuery = true)
	int getPriceById(@Param("productId") int productId);

	@Query(value = "SELECT product_image FROM products WHERE id = :productId", nativeQuery = true)
	String getProductImageById(@Param("productId") int productId);

	@Query(value = "SELECT product_name FROM products WHERE id = :productId", nativeQuery = true)
	String getProductNameById(@Param("productId") int productId);

	@Modifying
	@Query(value = "UPDATE products SET delete_flag = TRUE WHERE id = :id", nativeQuery = true)
	void logicalDeleteById(int id);
}
