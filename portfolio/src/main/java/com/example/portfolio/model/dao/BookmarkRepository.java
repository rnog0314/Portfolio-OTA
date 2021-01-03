package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.Bookmark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

  @Modifying
	@Query(value = "DELETE FROM bookmarks WHERE product_id = :productId", nativeQuery = true)
	int deleteByProductId(@Param("productId") Integer productId);


}
