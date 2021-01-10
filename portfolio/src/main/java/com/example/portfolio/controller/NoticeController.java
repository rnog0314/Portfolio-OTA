package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.Notice;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/portfolio/notice")
public class NoticeController {

  @Autowired
  private NoticeService noticeService;

  @Autowired
  private LoginSession loginSession;

  @GetMapping(value = "")
  public String init(Model m) {
    List<Notice> notices = noticeService.findAllByVisibleFlagTrue();
    m.addAttribute("notices", notices);
    m.addAttribute("loginSession", loginSession);
    return "notice";
  }

  @GetMapping(value = "/{id}")
  public String showDetail(@PathVariable("id") int id, Model m) {
    Notice notice = noticeService.findById(id);
    m.addAttribute("notice", notice);
    m.addAttribute("loginSession", loginSession);
    return "notice_detail";
  }

}
