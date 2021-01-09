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

  public List<Notice> findAll() {
    return noticeRepos.findAll();
  }

  public Notice findById(int id) {
    return noticeRepos.findById(id).get();
  }

  public int update(Notice n) {
    return noticeRepos.update(n.getId(), n.getTitle(), n.getText());
  }

  public List<Notice> findAllByVisibleFlagTrue() {
    return noticeRepos.findAllByVisibleFlagTrueOrderByCreatedAt();
  }

}
