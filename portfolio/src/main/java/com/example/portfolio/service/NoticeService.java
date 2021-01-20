package com.example.portfolio.service;

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
    return noticeRepos.findAll();
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
    return noticeRepos.update(n.getId(), n.getTitle(), n.getText());
  }

  /**
   * 表示可お知らせ一覧取得
   *
   * @return List<Notice> 表示可お知らせ一覧
   */
  public List<Notice> findAllByVisibleFlagTrue() {
    return noticeRepos.findAllByVisibleFlagTrueOrderByCreatedAt();
  }

  public Notice insert(Notice n) {
    Notice notice = new Notice(n.getTitle(), n.getText());
    return noticeRepos.save(notice);
    // TODO 作成時刻をNow()で取得して、それをStringにフォーマットしてからentity挿入
  }

}
