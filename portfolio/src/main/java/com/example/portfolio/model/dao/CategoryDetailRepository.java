package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.CategoryDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDetailRepository extends JpaRepository<CategoryDetail, Integer> {

}
