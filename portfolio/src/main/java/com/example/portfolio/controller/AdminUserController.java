package com.example.portfolio.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.session.AdminSession;
import com.example.portfolio.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/portfolio/admin/user")
public class AdminUserController {
  @Autowired
  private AdminSession adminSession;

  @Autowired
  private UserService userService;

  @GetMapping(value = { "", "/{page:^[1-9][0-9]*$}" })
  public String getMethodName(@PathVariable(name = "page") Optional<Integer> page, Model m, User u) {
    Page<User> users = userService.findPaginatedList(page);
    int lastPage = users.getTotalPages();
    if (lastPage > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, lastPage).boxed().collect(Collectors.toList()); // HTMLでページ分ループするために各ページ番号が入ったリストを作成
      m.addAttribute("pageNumbers", pageNumbers);
    }
    m.addAttribute("users", users);
    m.addAttribute("adminSession", adminSession);
    return "admin/user";
  }

}
