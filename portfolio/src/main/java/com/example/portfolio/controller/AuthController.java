package com.example.portfolio.controller;

import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.form.UserForm;
import com.example.portfolio.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/portfolio/auth")
public class AuthController {

  private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

  @Autowired
  private UserService userService;

  @PostMapping(value = "/login")
  @ResponseBody
  public String login(@RequestBody UserForm form, Model m) {
    User user = userService.findByEmailAndPassword(form.getEmail(), form.getPassword());
    return gson.toJson(user);
  }

}
