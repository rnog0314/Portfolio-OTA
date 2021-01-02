package com.example.portfolio.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.portfolio.model.entity.Product;
import com.example.portfolio.model.entity.SearchDto;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/portfolio/search")
public class SearchController {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private ProductService productService;

  @GetMapping(value = { "", "/{page:^[1-9][0-9]*$}" })
  public String index(@RequestParam("keyword") String keyword, @PathVariable(name = "page") Optional<Integer> page, Model m) {
    Set<SearchDto> products = productService.search(keyword, page);
    int lastPage = products.size();
    if (lastPage > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, lastPage).boxed().collect(Collectors.toList()); // HTMLでページ分ループするために各ページ番号が入ったリストを作成
      m.addAttribute("pageNumbers", pageNumbers);
    }
    m.addAttribute("lastPage", lastPage);
    m.addAttribute("products", products);
    m.addAttribute("loginSession", loginSession);
    return "search";
  }



}
