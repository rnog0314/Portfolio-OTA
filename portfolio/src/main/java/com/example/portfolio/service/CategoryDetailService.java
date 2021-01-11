package com.example.portfolio.service;

import java.util.Optional;

import com.example.portfolio.model.dao.CategoryDetailRepository;
import com.example.portfolio.model.entity.CategoryDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CategoryDetailService {

  @Autowired
  private CategoryDetailRepository categoryDetailRepos;

  /**
   * カテゴリ詳細取得
   * @param categoryId カテゴリID
   * @return CategoryDetail カテゴリ詳細
   */
	public CategoryDetail findById(int categoryId) {
    Optional <CategoryDetail> result =  categoryDetailRepos.findById(categoryId);
    CategoryDetail categoryDetail =  result.get();
    return categoryDetail;
	}

}
