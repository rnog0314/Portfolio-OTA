package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.Notice;
import com.example.portfolio.model.session.AdminSession;
import com.example.portfolio.service.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/portfolio/admin/notice")
public class AdminNoticeController {

  @Autowired
  private AdminSession adminSession;

  @Autowired
  private NoticeService noticeService;

  @GetMapping(value = "")
  public String init(Model m) {
    List<Notice> notices = noticeService.findAll();
    m.addAttribute("notices", notices);
    return "admin/notice";
  }

}
