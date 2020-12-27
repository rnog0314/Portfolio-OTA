package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.Testimonials;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonialRepository extends JpaRepository<Testimonials, Integer> {

}
