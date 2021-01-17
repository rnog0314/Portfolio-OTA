package com.example.portfolio.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.BookmarkDtoRepository;
import com.example.portfolio.model.entity.BookmarkDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Sql
public class BookmarkDtoRepositoryTest {

  @Autowired
  private BookmarkDtoRepository repos;

  @Test
  public void testGetBookmarkList() {
    List<BookmarkDto> expected = new ArrayList<>();
    expected.add(new BookmarkDto(1, 1, 1, "dummyImage1", "dummyName1"));
    expected.add(new BookmarkDto(1, 2, 2, "dummyImage2", "dummyName2"));
    int userId = 1;
    List<BookmarkDto> actual = repos.getBookmarkList(userId);
    assertThat(actual, is(samePropertyValuesAs(expected)));
  }

}
