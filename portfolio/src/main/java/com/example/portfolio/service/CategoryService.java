package com.example.portfolio.service;

import java.util.List;

import com.example.portfolio.model.dao.CategoryRepository;
import com.example.portfolio.model.entity.Categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CategoryService {

  @Autowired
  CategoryRepository categoryRepos;

	public List<Categories> findAll() {
		return categoryRepos.findAll();
	}

}
