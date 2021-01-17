package com.example.portfolio.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import com.example.portfolio.model.entity.SearchDto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class Utils {

  private final int RECORDS = 9;

  /**
   * endの取得
   *
   * @param start 予約日
   * @return end
   */
  public String getEndDate(String start) {
    String end;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date formatDate;
    try {
      formatDate = sdf.parse(start);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(formatDate);
      calendar.add(Calendar.DATE, +1);
      System.out.println(sdf.format(calendar.getTime()));
      end = sdf.format(calendar.getTime());
    } catch (ParseException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
    return end;
  }

  /**
   * Pageableの作成
   *
   * @param page リクエストされたページ番号
   * @return Pageable
   */
  public Pageable getPageable(Optional<Integer> page) {
    int currentPage = getCurrentPage(page);
    Sort sort = Sort.by("userId").ascending(); // ソートのルールを作成
    Pageable pageable = PageRequest.of(currentPage - 1, 10, sort); // ページネーション情報作成
    return pageable;
  }

  /**
   * 現在ページを取得
   *
   * @param page
   * @return
   */
  public int getCurrentPage(Optional<Integer> page) {
    int currentPage = page.orElse(1); // ページ初期表示でリクエストされたページ番号がない時、ページ番号を1とする
    if (currentPage == 0) {// 先頭ページを表示している際の「<」押下用
      currentPage = 1;
    }
    return currentPage;
  }

  /**
   * 最後のページ番号を取得
   *
   * @param list
   * @return
   */
  public int getLastPage(Set<SearchDto> list) {
    int count = list.size();
    int lastPage = count / RECORDS;
    if (count % RECORDS == 0) {
      return lastPage;
    } else {
      return lastPage + 1;
    }
  }
}
