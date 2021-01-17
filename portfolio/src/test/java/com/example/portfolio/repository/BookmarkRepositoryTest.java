package com.example.portfolio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.BookmarkRepository;
import com.example.portfolio.model.entity.Bookmark;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.jdbc.Sql;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Sql
public class BookmarkRepositoryTest {

  @Autowired
  private BookmarkRepository repos;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void name() {
    String sql = "SELECT * FROM bookmarks WHERE product_id = 1 AND user_id = 1";
    RowMapper<Bookmark> rowMapper = new BeanPropertyRowMapper<Bookmark>(Bookmark.class);
    List<Bookmark> actual1 = jdbcTemplate.query(sql, rowMapper);
    assertEquals(1, actual1.size());

    // 削除後
    int productId = 1;
    int userId = 1;
    repos.deleteByProductIdAndUserId(productId, userId);
    List<Bookmark> actual2 = jdbcTemplate.query(sql, rowMapper);
    assertEquals(0, actual2.size());
  }
}
