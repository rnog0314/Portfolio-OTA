package com.example.portfolio.model.dao;

import java.util.List;

import com.example.portfolio.model.entity.Testimonial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Integer> {

	/**
	 * testimonial全取得
	 * @return List<Testimonial>
	 */
	@Query(value = "SELECT * FROM testimonials", nativeQuery = true)
	List<Testimonial> findAllTestimonails();

}
