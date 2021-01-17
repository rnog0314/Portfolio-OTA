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

  /**
   * お気に入り追加
   * @param productId 商品ID
   * @return 商品追加件数
   */
  public int add(int productId) {
    try {
      Bookmark bookmark = new Bookmark(loginSession.getUserId(), productId);
      bookmarkRepos.save(bookmark);
      return 1;
    } catch (IllegalArgumentException e) {
      throw new RuntimeException();
    }
  }

  /**
   * お気に入り取得
   * @param userId ユーザID
   * @return ユーザ別お気に入り一覧
   */
  public List<BookmarkDto> getBookmarkList(int userId) {
    return bookmarkDtoRepos.getBookmarkList(userId);
  }

  /**
   * お気に入り削除
   * @param productId 商品ID
   * @return 削除件数
   */
  public int delete(int productId) {
    int userId = loginSession.getUserId();
    return bookmarkRepos.deleteByProductIdAndUserId(productId, userId);
  }

}
