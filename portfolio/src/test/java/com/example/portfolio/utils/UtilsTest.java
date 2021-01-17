package com.example.portfolio.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import com.example.portfolio.model.entity.SearchDto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UtilsTest {

  Utils utils = new Utils();

  @Test
  public void testGetCurrentPage() {
    // 先頭ページ「<」が押下された時
    Optional<Integer> page = Optional.of(Integer.valueOf(0));
    int actual1 = utils.getCurrentPage(page);
    assertEquals(1, actual1);

    // 先頭ページ「<」以外が押下された時
    page = Optional.of(Integer.valueOf(8));
    int actual2 = utils.getCurrentPage(page);
    assertEquals(8, actual2);
  }

  @Test
  public void testGetLastPage() {
    // リストが9の倍数の時
    Set<SearchDto> list1 = new HashSet<>();
    for (int i = 1; i <= 18; i++) {
      list1.add(new SearchDto(i, "dummyImage" + i, "dummyName" + i));
    }
    int actual1 = utils.getLastPage(list1);
    assertEquals(2, actual1);

    // リストが9の倍数以外の時
    Set<SearchDto> list2 = new HashSet<>();
    for (int i = 1; i <= 55; i++) {
      list2.add(new SearchDto(i, "dummyImage" + i, "dummyName" + i));
    }
    int actual2 = utils.getLastPage(list2);
    assertEquals(7, actual2);
  }

  @Test
  public void testGetEndDate() {
    Utils utils = new Utils();
    // 正常系
    String start = "2020-12-31";
    String actual1 = utils.getEndDate(start);
    assertEquals("2021-01-01", actual1);

    // 異常系
    String start1 = "something";
    assertThrows(RuntimeException.class, ()-> utils.getEndDate(start1));

  }
}
