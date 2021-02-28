package com.example.portfolio.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.portfolio.model.entity.dto.SearchDto;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.model.session.SearchSession;
import com.example.portfolio.service.ProductService;
import com.example.portfolio.utils.Utils;

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

  @Autowired
  private Utils utils;

  @Autowired
  private SearchSession searchSession;

  /**
   * 商品検索
   * @param keyword 検索ワード
   * @param page リクエストされたページ番号
   * @param m Model
   * @return search.html
   */
  @GetMapping(value = { "", "/{page:^[1-9][0-9]*$}" })
  public String init(@RequestParam("keyword") Optional<String> keyword, @PathVariable(name = "page") Optional<Integer> page, Model m) {
    String key = "";
    if (keyword.isPresent()) { // 検索ワードが入力されていた場合
      key = keyword.get();
      searchSession.setKeyword(key); // searchセッションを更新する
    } else {
      key = searchSession.getKeyword(); // セッションから検索キーワードを取得する
    }
    Set<SearchDto> searchResult = productService.getAllSearchResult(key); // 検索キーワードを条件に検索処理を行う
    int lastPage = utils.getLastPage(searchResult); // 検索結果の最後ページを取得
    List<SearchDto> paginatedResult = productService.getPaginatedResult(searchResult, page); // 全検索結果からリクエストされたページの検索結果のみを取得する
    int currentPage = utils.getCurrentPage(page); // 現在ページを取得する

    if (lastPage > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, lastPage).boxed().collect(Collectors.toList()); // HTMLでページ分ループするために各ページ番号が入ったリストを作成
      m.addAttribute("pageNumbers", pageNumbers);
    }

    m.addAttribute("page", currentPage);
    m.addAttribute("lastPage", lastPage);
    m.addAttribute("products", paginatedResult);
    m.addAttribute("loginSession", loginSession);
    return "search";
  }

}
