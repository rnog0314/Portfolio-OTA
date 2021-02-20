package com.example.portfolio.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.NoticeRepository;
import com.example.portfolio.model.entity.Notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class NoticeService {

  @Autowired
  private NoticeRepository noticeRepos;

  /**
   * お知らせ全取得
   *
   * @return List<Notice> お知らせ一覧
   */
  public List<Notice> findAll() {
    return noticeRepos.findAllOrderByCreatedAt();
  }

  /**
   * お知らせ詳細取得
   *
   * @param id お知らせID
   * @return Notice お知らせ詳細
   */
  public Notice findById(int id) {
    return noticeRepos.findById(id).get();
  }

  /**
   * お知らせ情報更新
   *
   * @param n Notice
   * @return お知らせ更新件数
   */
  public int update(Notice n) {
    Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
    return noticeRepos.update(n.getId(), n.getTitle(), n.getText(), updatedAt);
  }

  /**
   * 表示可お知らせ一覧取得
   *
   * @return List<Notice> 表示可お知らせ一覧
   */
  public List<Notice> findAllByVisibleFlagTrue() {
    return noticeRepos.findAllByVisibleFlagTrueOrderByCreatedAt();
  }

  /**
   * お知らせ登録
   * @param Notice
   * @return 新規登録したNotice
   */
  public Notice insert(Notice n) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Notice notice = new Notice(n.getTitle(), n.getText(), timestamp, timestamp);
    return noticeRepos.save(notice);
  }

}
