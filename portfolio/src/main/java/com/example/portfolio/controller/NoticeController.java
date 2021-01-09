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

}
