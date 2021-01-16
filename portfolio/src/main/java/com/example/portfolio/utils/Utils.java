package com.example.portfolio.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Utils {

  /**
   * endの取得
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
  
}
