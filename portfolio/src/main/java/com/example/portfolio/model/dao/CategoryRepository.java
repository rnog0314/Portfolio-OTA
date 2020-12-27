package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.Categories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
  
}
