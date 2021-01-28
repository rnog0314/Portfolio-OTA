package com.example.portfolio.model.entity.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CalendarReservationDto {

  @Id
  private int id;

  @Column(name = "start_date")
  private String start;

  @Column(name = "end_date")
  private String end;

  private String title;

  private int count;

  private boolean validFlag;
}
