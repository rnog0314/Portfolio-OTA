package com.example.portfolio.repository;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.ProductDetailRepository;
import com.example.portfolio.model.entity.ProductDetail;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Sql
public class ProductDetailRepositoryTest {

  @Autowired
  private ProductDetailRepository repos;

  @Test
  public void testFindByProductId() {
    int id = 1;
    ProductDetail actual = repos.findByProductId(id);
    ProductDetail expected = new ProductDetail(1, 1, "dummyImg", "dummyArticleTitle", "dummyArticleText", 1000);
    assertThat(actual, is(samePropertyValuesAs(expected)));

  }

}
