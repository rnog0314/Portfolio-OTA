package com.example.portfolio.model.dao;

import java.util.List;

import com.example.portfolio.model.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByDestinationId(int destinationId);

	List<Product> findByCategoryId(int categoryId);

}
