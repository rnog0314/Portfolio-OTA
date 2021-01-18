package com.example.portfolio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.ProductRepository;
import com.example.portfolio.model.entity.Product;

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

  @Test
  public void testFindByDestinationId() {
    int id = 1;
    List<Product> actual = repos.findByDestinationId(id);
    assertEquals(2, actual.size());
  }
}
