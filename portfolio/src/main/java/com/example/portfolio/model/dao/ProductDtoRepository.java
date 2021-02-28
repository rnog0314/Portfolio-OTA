package com.example.portfolio.model.dao;

import java.util.List;

import com.example.portfolio.model.entity.dto.ProductDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDtoRepository extends JpaRepository<ProductDto, Integer> {

  /**
   * 商品カテゴリを条件に絞り込んだ商品レコード一覧取得
   * @param categoryId
   * @return List<ProductDto>
   */
  @Query(value = "SELECT * FROM products WHERE category_id = :categoryId", nativeQuery = true)
  List<ProductDto> findByCategoryId(int categoryId);

  /**
   * 商品IDを条件に絞り込んだ商品レコードを取得
   * @param productId
   * @return ProductDto
   */
  @Query(value = "SELECT * FROM products WHERE id = :productId", nativeQuery = true)
  ProductDto findProductById(int productId);

  /**
   * productsテーブルの昇順10レコードを取得
   * @return List<ProductDto>
   */
  @Query(value = "SELECT * FROM products ORDER BY id LIMIT 10", nativeQuery = true)
  List<ProductDto> findTenProducts();

  /**
   * 商品レコード全取得
   * @return ist<ProductDto>
   */
  @Query(value = "SELECT * FROM products WHERE delete_flag = FALSE ORDER BY id", nativeQuery = true)
  List<ProductDto> findAllProduct();

  /**
   * デスティネーションIDを検索条件として絞り込んだ商品レコード取得
   * @param destinationId
   * @return List<ProductDto>
   */
  @Query(value = "SELECT * FROM products WHERE destination_id = :destinationId", nativeQuery = true)
	List<ProductDto> findProductByDestinationId(int destinationId);

}
