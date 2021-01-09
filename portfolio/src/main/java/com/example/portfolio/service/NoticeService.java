package com.example.portfolio.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.NoticeRepository;
import com.example.portfolio.model.entity.Notice;
import com.example.portfolio.model.session.AdminSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class NoticeService {

  @Autowired
  private NoticeRepository noticeRepos;

  @Autowired
  private AdminSession adminSession;

  public List<Notice> findAll() {
    return noticeRepos.findAll();
  }

}
