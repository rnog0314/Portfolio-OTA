package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.Testimonial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Integer> {

}
