package com.example.portfolio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.NoticeRepository;
import com.example.portfolio.model.entity.Notice;

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
public class NoticeRepositoryTest {

  @Autowired
  private NoticeRepository repos;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void testUpdate() {
    // 更新処理
    int id = 1;
    String title = "updatedTitle";
    String text = "updatedText";
    Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
    repos.update(id, title, text, updatedAt);

    // 更新確認
    String sql = "SELECT * FROM notices WHERE id = ?";
    RowMapper<Notice> rowMapper = new BeanPropertyRowMapper<Notice>(Notice.class);
    Notice notice = jdbcTemplate.queryForObject(sql, rowMapper, id);
    assertEquals("updatedTitle", notice.getTitle());
    assertEquals("updatedText", notice.getText());
  }

  @Test
  public void testFindAllByVisibleFlagTrueOrderByCreatedAt() {
      List<Notice> actual = repos.findAllByVisibleFlagTrueOrderByCreatedAt();
      assertEquals(1, actual.size());
  }
}
