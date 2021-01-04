package com.example.portfolio.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.portfolio.model.entity.Product;
import com.example.portfolio.model.form.ProductForm;
import com.example.portfolio.model.session.AdminSession;
import com.example.portfolio.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/portfolio/admin/product")
public class AdminProductController {

  @Autowired
  private AdminSession adminSession;

  @Autowired
  private ProductService productService;

  @GetMapping(value = { "", "/{page:^[1-9][0-9]*$}" })
  public String findProduct(@PathVariable(name = "page") Optional<Integer> page, Model m, Product p) {
    Page<Product> products = productService.findPaginatedList(page);
    int lastPage = products.getTotalPages();
    if (lastPage > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, lastPage).boxed().collect(Collectors.toList()); // HTMLでページ分ループするために各ページ番号が入ったリストを作成
      m.addAttribute("pageNumbers", pageNumbers);
    }
    m.addAttribute("products", products);
    m.addAttribute("adminSession", adminSession);
    return "admin/product";
  }

  @PostMapping(value = "/delete")
  @ResponseBody
  public Boolean delete(@RequestBody String[] checkedIdList) {
    Boolean bool = false;
    try {
      for (String id : checkedIdList) { // 配列を拡張for文でループしてそれぞれDBから論理削除する
        productService.delete(Integer.parseInt(id)); // パラメータの型はStringであるためparseIntする
      }
      bool = true;
      return bool;
    } catch (Exception e) {
      throw new RuntimeException();
    }

  }

  @PostMapping(value = "/modify")
  public String modify(ProductForm productForm) {
    productService.updateProduct(productForm);

    return "redirect:/portfolio/admin/product";
  }

}
