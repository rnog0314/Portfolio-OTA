package com.example.portfolio.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.SearchDtoRepository;
import com.example.portfolio.model.entity.dto.SearchDto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Sql(scripts = "classpath:SearchDtoRepositoryTest.sql")
public class SearchDtoRepositoryTest {

  @Autowired
  private SearchDtoRepository repos;

  @Test
  public void testFetchProduct() {

    String keyword = "Name1";
    Set<SearchDto> set = repos.fetchProduct(keyword);
    List<SearchDto> actual = new ArrayList<>(set);
    SearchDto expected = new SearchDto(1, "dummyImage1", "dummyName1");
    assertThat(actual.get(0), is(samePropertyValuesAs(expected)));

  }


}
