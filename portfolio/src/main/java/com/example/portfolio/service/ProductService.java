package com.example.portfolio.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.portfolio.model.dao.ProductDtoRepository;
import com.example.portfolio.model.dao.ProductRepository;
import com.example.portfolio.model.dao.SearchDtoRepository;
import com.example.portfolio.model.entity.dto.ProductDto;
import com.example.portfolio.model.entity.dto.SearchDto;
import com.example.portfolio.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepos;

	@Autowired
	private ProductDtoRepository productDtoRepos;

	@Autowired
	private SearchDtoRepository searchRepos;

	@Autowired
	private Utils utils;

	private final int RECORDS = 9;

	/**
	 * トップで表示する10個の商品取得
	 *
	 * @return
	 */
	public List<ProductDto> findAll() {
		return productDtoRepos.findTenProducts();
	}

	/**
	 * 行き先を条件に商品レコードを取得
	 *
	 * @param destinationId デスティネーションID
	 * @return List<Product> デスティネーションIDでソートした商品一覧
	 */
	public List<ProductDto> findByDestinationId(int destinationId) {
		return productDtoRepos.findProductByDestinationId(destinationId);
	}

	/**
	 * カテゴリを条件に商品レコードを取得
	 *
	 * @param categoryId カテゴリID
	 * @return List<Product> カテゴリIDでソートした商品一覧
	 */
	public List<ProductDto> findByCategoryId(int categoryId) {
		return productDtoRepos.findByCategoryId(categoryId);
	}

	/**
	 * 商品詳細取得
	 *
	 * @param productId 商品ID
	 * @return Product 商品
	 */
	public ProductDto findById(int productId) {
		ProductDto product = productDtoRepos.findProductById(productId);
		return product;
	}

	/**
	 * キーワード条件にレコードを全て取得
	 *
	 * @param keyword
	 * @return Set<SearchDto> キーワドでソートされたリスト
	 */
	public Set<SearchDto> getAllSearchResult(String keyword) {
		// 検索キーワードをトリミング&分割して配列に入れる
		String[] keywords = keyword.replaceAll("　", " ").replaceAll("\\s{2,}", " ").trim().split(" ");
		Set<SearchDto> products = new HashSet<>(); // リスト内で重複したレコードがないようにSetを使用
		for (String key : keywords) {
			// 分割したキーワード毎にレコードを検索し、用意した空のSetに追加
			Set<SearchDto> set = searchRepos.fetchProduct(key);
			for (SearchDto s : set) {
				products.add(s);
			}
		}
		return products;
	}

	/**
	 * リクエストされたページの商品リスト取得
	 *
	 * @param products 検索された商品一覧の全結果
	 * @param page     リクエストされたページ番号
	 * @return List<SearchDto> ページネーションされた検索結果
	 */
	public List<SearchDto> getPaginatedResult(Set<SearchDto> products, Optional<Integer> page) {
		// 取得した数が9つに満たなかった場合はSetをそのままListに変換して返す
		int count = products.size();
		if (!(count > 8)) {
			return new ArrayList<>(products);
		}
		int currentPage = utils.getCurrentPage(page); // 押下されたページリンクの数字(リクエストされたページ番号)
		int from = (currentPage - 1) * RECORDS;
		int to = currentPage * RECORDS;
		if (count < to) {
			to = count;
		}
		List<SearchDto> list = new ArrayList<>(products);
		List<SearchDto> subList = list.subList(from, to);
		return subList;
	}

	/**
	 * 商品削除
	 *
	 * @param id 商品ID
	 */
	public void delete(int id) {
		productRepos.deleteById(id);
		;
	}

	/**
	 * ページネーションされた商品リスト取得
	 *
	 * @param page リクエストされたページ番号
	 * @return ページネーションされた商品リスト
	 */
	public Page<ProductDto> findPaginatedList(Optional<Integer> page) {
		Pageable pageable = utils.getPageable(page);
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<ProductDto> list;
		List<ProductDto> products = productDtoRepos.findAllProduct();
		if (products.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, products.size());
			list = products.subList(startItem, toIndex);
		}
		Page<ProductDto> productPage = new PageImpl<>(list, pageable, products.size());
		return productPage;
	}

	/**
	 * 商品価格取得
	 *
	 * @param productId 商品ID
	 * @return 商品価格
	 */
	public int getPriceByProductId(int productId) {
		return productRepos.getPriceById(productId);
	}

	/**
	 * 商品画像のパス取得
	 *
	 * @param productId 商品ID
	 * @return String 商品画像のパス
	 */
	public String getProductImageByProductId(int productId) {
		return productRepos.getProductImageById(productId);
	}

	/**
	 * 商品名
	 *
	 * @param productId 商品ID
	 * @return String 商品名
	 */
	public String getProductNameByProductId(int productId) {
		return productRepos.getProductNameById(productId);
	}

}
