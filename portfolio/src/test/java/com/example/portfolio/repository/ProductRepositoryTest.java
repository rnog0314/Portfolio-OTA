package com.example.portfolio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.ProductRepository;
import com.example.portfolio.model.entity.Product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.jdbc.core.BeanPropertyRowMapper;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Sql
public class ProductRepositoryTest {

  @Autowired
  private ProductRepository repos;

  // @Autowired
  // private JdbcTemplate jdbcTemplate;

  @Test
  public void testFindByDestinationId() {
    int id = 1;
    List<Product> actual = repos.findByDestinationId(id);
    assertEquals(2, actual.size());
  }

  @Test
  public void testFindByCategoryId() {
    int id = 3;
    List<Product> actual = repos.findByCategoryId(id);
    assertEquals(2, actual.size());
  }

  @Test
  public void PriceById() {
    int productId = 4;
    int actual = repos.getPriceById(productId);
    assertEquals(1500, actual);
  }

  @Test
  public void testGetProductImageById() {
    int productId = 1;
    String actual = repos.getProductImageById(productId);
    assertEquals("dummyImage1", actual);
  }

  @Test
  public void testGetProductNameById() {
    int productId = 1;
    String actual = repos.getProductNameById(productId);
    assertEquals("dummyName1", actual);
  }
}
