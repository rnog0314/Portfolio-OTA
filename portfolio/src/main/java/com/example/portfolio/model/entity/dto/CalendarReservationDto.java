package com.example.portfolio.model.entity.dto;

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
  private String start;
  private String end;
  private String title;
}
