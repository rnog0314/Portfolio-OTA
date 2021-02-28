package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.ProductDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {

	/**
	 * 商品詳細レコード取得
	 * @param productId
	 * @return ProductDetail
	 */
	@Query("SELECT pd FROM ProductDetail pd WHERE pd.productId = :productId")
	ProductDetail findByProductId(int productId);

}
