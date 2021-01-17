package com.example.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.ProductRepository;
import com.example.portfolio.model.dao.SearchDtoRepository;
import com.example.portfolio.model.entity.SearchDto;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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

}
