package com.example.portfolio.model.dao;

import java.util.Set;

import com.example.portfolio.model.entity.SearchDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchDtoRepository extends JpaRepository<SearchDto, Integer> {
	@Query(value = "SELECT p.product_id, p.product_image, p.product_name " +
									"FROM products AS p " +
									"INNER JOIN categories AS c ON p.category_id = c.category_id " +
									"INNER JOIN destinations AS d ON p.destination_id = d.destination_id " +
									"WHERE p.product_name LIKE concat('%', :keyword, '%') " +
									"OR c.category_name LIKE concat('%', :keyword, '%') " +
									"OR d.destination_name LIKE concat('%', :keyword, '%') " +
									"ORDER BY p.product_id ", nativeQuery = true)
	Set<SearchDto> fetchProduct(@Param("keyword") String keyword);

}
