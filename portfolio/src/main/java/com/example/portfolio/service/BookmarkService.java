package com.example.portfolio.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.BookmarkDtoRepository;
import com.example.portfolio.model.dao.BookmarkRepository;
import com.example.portfolio.model.entity.Bookmark;
import com.example.portfolio.model.entity.BookmarkDto;
import com.example.portfolio.model.session.LoginSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class BookmarkService {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private BookmarkRepository bookmarkRepos;

  @Autowired
  private BookmarkDtoRepository bookmarkDtoRepos;

  public int add(Integer productId) {
    Bookmark bookmark = new Bookmark(loginSession.getUserId(), productId);
    try {
      bookmarkRepos.save(bookmark);
      return 1;
    } catch (IllegalArgumentException e) {
      throw new RuntimeException();
    }
  }

  public List<BookmarkDto> getBookmarkList(Integer userId) {
    return bookmarkDtoRepos.getBookmarkList(userId);
  }

  public int delete(Integer productId) {
    try {
      return bookmarkRepos.deleteByProductId(productId);
    } catch (IllegalArgumentException e) {
      throw new RuntimeException(e);
    }
  }

}
