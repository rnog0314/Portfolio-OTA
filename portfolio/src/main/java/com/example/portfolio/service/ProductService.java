package com.example.portfolio.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.portfolio.model.dao.ProductRepository;
import com.example.portfolio.model.dao.SearchDtoRepository;
import com.example.portfolio.model.entity.Product;
import com.example.portfolio.model.entity.SearchDto;
import com.example.portfolio.model.form.ProductForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductService {

	@Autowired
	ProductRepository productRepos;

	@Autowired
	SearchDtoRepository searchRepos;

	private final int RECORDS = 9;

	/**
	 * トップで表示する10個の商品取得
	 *
	 * @return
	 */
	public List<Product> findAll() {
		return productRepos.findAll().subList(1, 11);
	}

	/**
	 * 行き先を条件に商品レコードを取得
	 *
	 * @param destinationId デスティネーションID
	 * @return List<Product> デスティネーションIDでソートした商品一覧
	 */
	public List<Product> findByDestinationId(int destinationId) {
		return productRepos.findByDestinationId(destinationId);
	}

	/**
	 * カテゴリを条件に商品レコードを取得
	 * @param categoryId カテゴリID
	 * @return List<Product> カテゴリIDでソートした商品一覧
	 */
	public List<Product> findByCategoryId(int categoryId) {
		return 	productRepos.findByCategoryId(categoryId);
	}

	/**
	 * 商品詳細取得
	 *
	 * @param productId 商品ID
	 * @return Product 商品
	 */
	public Product findById(int productId) {
		Optional<Product> result = productRepos.findById(productId);
		Product product = result.get();
		return product;
	}


	/**
	 * キーワード条件にレコードを全て取得
	 *
	 * @param keyword
	 * @return
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
	 * リクエストされたページのレコードを取得
	 *
	 * @param products
	 * @param page
	 * @return
	 */
	public List<SearchDto> getPaginatedResult(Set<SearchDto> products, Optional<Integer> page) {
		int currentPage = getCurrentPage(page); // 押下されたページリンクの数字(リクエストされたページ番号)

		int from = (currentPage - 1) * RECORDS;
		int to = currentPage * RECORDS;
		int count = products.size();
		if (!(count > 8)) { // 取得した数が9つに満たなかった時
			return new ArrayList<>(products);
		}
		if (count < to) {
			to = count;
		}
		List<SearchDto> list = new ArrayList<>(products);
		List<SearchDto> subList = list.subList(from, to);
		return subList;
	}

	/**
	 * 現在ページを取得
	 *
	 * @param page
	 * @return
	 */
	public int getCurrentPage(Optional<Integer> page) {
		int currentPage = page.orElse(1);
		if (currentPage == 0) {// 先頭ページを表示している際の「<」押下用
      currentPage = 1;
    }
		return currentPage;
	}

	/**
	 * 最後のページ番号を取得
	 *
	 * @param list
	 * @return
	 */
	public int getLastPage(Set<SearchDto> list) {
		int count = list.size();
		int lastPage = count / RECORDS;
		if (count % RECORDS == 0) {
			return lastPage;
		} else {
			return lastPage + 1;
		}
	}

	/**
	 * 商品削除
	 * @param id 商品ID
	 */
	public void delete(int id) {
		productRepos.deleteById(id);;
	}

	/**
	 * ページネーションされた商品リスト取得
	 * @param page リクエストされたページ番号
	 * @return ページネーションされた商品リスト
	 */
	public Page<Product> findPaginatedList(Optional<Integer> page) {
		int currentPage = getCurrentPage(page);
		Sort sort = Sort.by("productId").ascending(); // ソートのルールを作成
		Pageable pageable = PageRequest.of(currentPage - 1, 10, sort); // ページネーション情報作成
		return productRepos.findAll(pageable);
	}

	/**
	 * 商品情報修正
	 * @param f ProductForm
	 */
	public void updateProduct(ProductForm f) {
		productRepos.updateProduct(f.getProductName(), f.getPrice());
	}

	/**
	 * 商品価格取得
	 * @param productId 商品ID
	 * @return 商品価格
	 */
	public int getPriceByProductId(int productId) {
		return productRepos.getPriceByProductId(productId);
	}

	public String getProductImageByProductId(int productId) {
		return productRepos.getProductImageByProductId(productId);
	}

	public String getProductNameByProductId(int productId) {
		return productRepos.getProductNameByProductId(productId);
	}

}
