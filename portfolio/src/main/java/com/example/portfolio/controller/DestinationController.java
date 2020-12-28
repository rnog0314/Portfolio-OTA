package com.example.portfolio.controller;

import com.example.portfolio.model.entity.DestinationDetails;
import com.example.portfolio.service.DestinationDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portfolio/destination")
public class DestinationController {

  @Autowired
  private DestinationDetailService destinationDetailService;

  @GetMapping(value = "/{destination_id}")
  public String goDestinationPage(@PathVariable("destination_id") int destinationId, Model m) {
    DestinationDetails destinationDetail = destinationDetailService.findById(destinationId);
    m.addAttribute("destinationDetail", destinationDetail);
    return "destination_detail";
  }

}
