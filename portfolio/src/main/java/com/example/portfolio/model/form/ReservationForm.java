package com.example.portfolio.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationForm {

  private String date;
  private int count;
  private int productId;

}
