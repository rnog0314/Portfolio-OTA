package com.example.portfolio.model.dao;

import java.util.List;

import com.example.portfolio.model.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query(value = "SELECT c FROM Category c INNER JOIN FETCH c.categoryDetail")
	List<Category> findAllCategories();

	@Query(value = "SELECT c FROM Category c INNER JOIN FETCH c.categoryDetail WHERE c.categoryId = :categoryId")
	Category findCategoryById(int categoryId);

}
