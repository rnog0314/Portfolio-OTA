package com.example.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.example.portfolio.model.dao.BookmarkRepository;
import com.example.portfolio.model.entity.Bookmark;
import com.example.portfolio.model.session.LoginSession;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class BookmarkServiceTest {

  @Mock
  private BookmarkRepository bookmarkRepos;

  @Mock
  private LoginSession loginSession;

  @InjectMocks
  private BookmarkService bookmarkService;

  @Test
  public void testAdd() {
    // 正常処理
    when(loginSession.getUserId()).thenReturn(1);
    Bookmark bookmark = mock(Bookmark.class);
    when(bookmarkRepos.save(bookmark)).thenReturn(new Bookmark(1, 1));
    int actual = bookmarkService.add(1);
    assertEquals(1, actual);

    // 例外処理
    doReturn(null).when(bookmark);
    assertThrows(RuntimeException.class, () -> bookmarkService.add(1));
  }

  @Test
  public void testDelete() {
    when(bookmarkRepos.deleteByProductIdAndUserId(anyInt(), anyInt())).thenReturn(1);
    int actual = bookmarkService.delete(1);
    assertEquals(1, actual);
  }


}
