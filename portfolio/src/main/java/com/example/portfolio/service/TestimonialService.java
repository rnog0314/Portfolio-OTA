package com.example.portfolio.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.TestimonialRepository;
import com.example.portfolio.model.entity.Testimonial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class TestimonialService {

  @Autowired
  TestimonialRepository testimonialRepos;

  /**
   * Testimonial一覧取得
   * @return List<Testimonial> Testimonial一覧
   */
  public List<Testimonial> findAll() {
    return testimonialRepos.findAllTestimonails();
  }
}
