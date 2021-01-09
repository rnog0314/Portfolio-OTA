package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.Notice;
import com.example.portfolio.service.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/portfolio/admin/notice")
public class AdminNoticeController {

  @Autowired
  private NoticeService noticeService;

  @GetMapping(value = "")
  public String init(Model m) {
    List<Notice> notices = noticeService.findAll();
    m.addAttribute("notices", notices);
    return "admin/notice";
  }

  @GetMapping(value = "/{id}")
  public String goDetail(@PathVariable("id") int id, Model m) {
    Notice notice = noticeService.findById(id);
    m.addAttribute("notice", notice);
    return "admin/notice_detail";
  }

  @PostMapping(value = "/modify")
  @ResponseBody
  public boolean update(@RequestBody Notice notice) {
    boolean bool = false;
    int result = noticeService.update(notice);
    if (result > 0) {
      bool = true;
    }
    return bool;
  }

}
