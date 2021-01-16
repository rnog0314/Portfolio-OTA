package com.example.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.CategoryDetailRepository;
import com.example.portfolio.model.entity.CategoryDetail;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class CategoryDetailServiceTest {

  @Mock
  private CategoryDetailRepository cdRepos;

  @InjectMocks
  private CategoryDetailService cdSerive;

  @Test
  public void testFindById() {
    int id = 1;
    CategoryDetail cd = new CategoryDetail();
    when(cdRepos.findById(anyInt())).thenReturn(Optional.of(cd));
    CategoryDetail actual = cdSerive.findById(id);
    assertEquals(Optional.of(cd).get(), actual);
  }
}
