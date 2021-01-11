package com.example.portfolio.model.dao;

import java.util.List;

import com.example.portfolio.model.entity.Notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

	@Modifying
	@Query(value = "UPDATE notices SET title = :title, text = :text WHERE id = :id", nativeQuery = true)
	int update( @Param("id") int id,
							@Param("title") String title,
							@Param("text") String text);

	List<Notice> findAllByVisibleFlagTrueOrderByCreatedAt();

}
