package com.example.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.portfolio.model.dao.ProductRepository;
import com.example.portfolio.model.dao.SearchDtoRepository;
import com.example.portfolio.model.entity.SearchDto;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ProductServiceTest {

  @Mock
  private ProductRepository productRepos;

  @Mock
  private SearchDtoRepository searchRepos;

  @InjectMocks
  private ProductService productService;

  @Test
  public void testGetAllSearchResult() {
    Set<SearchDto> expected = new HashSet<>();
    expected.add(new SearchDto(1, "dummyImage1", "dummyName1"));
    expected.add(new SearchDto(2, "dummyImage2", "dummyName2"));
    when(searchRepos.fetchProduct(anyString())).thenReturn(expected);
    Set<SearchDto> actual = productService.getAllSearchResult("key1 key2 key3");
    assertEquals(expected, actual);
  }

  @Test
  public void testGetPaginatedResult() {
    // リストが9つに満たなかった時
    Set<SearchDto> products = new HashSet<>();
    products.add(new SearchDto(1, "dummyImage1", "dummyName1"));
    products.add(new SearchDto(2, "dummyImage2", "dummyName2"));
    Optional<Integer> page = Optional.of(Integer.valueOf(0));
    List<SearchDto> actual1 = productService.getPaginatedResult(products, page);
    assertEquals(2, actual1.size());

    // リストが9つよりも多くあった時
    Set<SearchDto> productList = new HashSet<>();
    for (int i = 1; i <= 15; i++) {
      productList.add(new SearchDto(i, "dummyImage" + i, "dummyName" + i));
    }
    page = Optional.of(Integer.valueOf(2));
    List<SearchDto> actual2 = productService.getPaginatedResult(productList, page);
    assertEquals(6, actual2.size());
  }

}
