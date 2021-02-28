package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.Bookmark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

	/**
	 * ブックマークの削除
	 * @param productId
	 * @param userId
	 * @return 1
	 */
  @Modifying
	@Query(value = "DELETE FROM bookmarks WHERE product_id = :productId AND user_id = :userId", nativeQuery = true)
	int deleteByProductIdAndUserId( @Param("productId") int productId,
																	@Param("userId") int userId);
}
