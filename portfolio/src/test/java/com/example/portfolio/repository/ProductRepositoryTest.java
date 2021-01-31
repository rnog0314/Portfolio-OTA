package com.example.portfolio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.ProductDtoRepository;
import com.example.portfolio.model.dao.ProductRepository;
import com.example.portfolio.model.entity.dto.ProductDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Sql
public class ProductRepositoryTest {

  @Autowired
  private ProductRepository repos;

  @Autowired
  private ProductDtoRepository dtoRepos;

  @Test
  public void testFindByCategoryId() {
    int id = 3;
    List<ProductDto> actual = dtoRepos.findByCategoryId(id);
    assertEquals(2, actual.size());
  }

  @Test
  public void testGetPriceById() {
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
