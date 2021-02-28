package com.example.portfolio.model.dao;

import java.util.List;

import com.example.portfolio.model.entity.dto.BookmarkDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkDtoRepository extends JpaRepository<BookmarkDto, Integer> {

  /**
   * ブックマーク一覧取得
   * @param userId
   * @return List<BookmarkDto> ブックマーク一覧
   */
  @Query(value =  "SELECT b.id, b.product_id, b.user_id, p.product_image, p.product_name " +
                  "FROM bookmarks AS b " +
                  "INNER JOIN products AS p " +
                  "ON b.product_id = p.id " +
                  "WHERE b.user_id = :userId", nativeQuery = true)
  List<BookmarkDto> getBookmarkList(@Param("userId") int userId);

  /**
   * ブックマーク
   * @param productId
   * @param userId
   * @return BookmarkDto ブックマーク
   */
  @Query(value = "SELECT b.id, b.product_id, b.user_id, p.product_image, p.product_name " +
                  "FROM bookmarks AS b " +
                  "INNER JOIN products AS p " +
                  "ON b.product_id = p.id " +
                  "INNER JOIN users AS u " +
                  "ON b.user_id = u.id " +
                  "WHERE b.product_id = :productId " +
                  "AND b.user_id = :userId", nativeQuery = true)
  BookmarkDto findByProductIdAndUserId(int productId, Integer userId);

}
