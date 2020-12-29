package com.example.portfolio.service;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.UserRepository;
import com.example.portfolio.model.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserService {

  @Autowired
  private UserRepository userRepos;

public User findByEmailAndPassword(String email, String password) {
	return userRepos.findByEmailAndPassword(email, password);
}
}
