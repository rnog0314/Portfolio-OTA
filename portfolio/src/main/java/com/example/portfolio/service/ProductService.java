package com.example.portfolio.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.portfolio.model.dao.ProductRepository;
import com.example.portfolio.model.dao.SearchDtoRepository;
import com.example.portfolio.model.entity.Product;
import com.example.portfolio.model.entity.SearchDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductService {

	@Autowired
	ProductRepository productRepos;

	@Autowired
	SearchDtoRepository searchRepos;

	public List<Product> findAll() {
		return productRepos.findAll();
	}

	public List<Product> findByDestinationId(int destinationId) {
		return productRepos.findByDestinationId(destinationId);
	}

	public Product findById(int productId) {
		Optional<Product> result = productRepos.findById(productId);
		Product product = result.get();
		return product;
	}

	public Set<SearchDto> search(String keyword, Optional<Integer> page) {
		int currentPage = page.orElse(1); // 押下されたページリンクの数字(リクエストされたページ番号)
		if (currentPage == 0) { currentPage = 1;} // 先頭ページを表示している際の「<」押下用
		Integer records = 9;
		Integer start = (currentPage - 1) * records;
		String[] keywords = keyword.replaceAll("　", " ").replaceAll("\\s{2,}", " ").trim().split(" ");
		Set<SearchDto> products = new HashSet<>();
		for (String str : keywords) {
			Set<SearchDto> set =  searchRepos.fetchProduct(str, records, start);
			for (SearchDto s : set) {
				products.add(s);
			}
		}
		return products;
	}

}
