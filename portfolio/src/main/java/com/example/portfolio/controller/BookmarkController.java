package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.BookmarkDto;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.BookmarkService;
// import com.example.portfolio.service.ProductService;
// import com.example.portfolio.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/portfolio/bookmark")
public class BookmarkController {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private BookmarkService bookmarkService;

  // @Autowired
  // private ProductService productService;

  // @Autowired
  // private UserService userService;

  @GetMapping(value = "")
  public String init(Model m) {
    List<BookmarkDto> bookmarks = bookmarkService.getBookmarkList(loginSession.getUserId());
    m.addAttribute("bookmarks", bookmarks);
    m.addAttribute("loginSession", loginSession);
    return "bookmark";
  }

  @PostMapping(value = "/add")
  @ResponseBody
  public boolean add(@RequestBody Integer productId) {
    boolean bool = false;
    int result = bookmarkService.add(productId);
    if (result > 0) {
      bool = true;
    }
    return bool;
  }

  @PostMapping(value = "/delete")
  @ResponseBody
  public boolean delete(@RequestBody Integer productId) throws Exception {
    boolean bool = false;
    int result = bookmarkService.delete(productId);
    if (result > 0) {
      bool = true;
    }
    return bool;
  }

}
