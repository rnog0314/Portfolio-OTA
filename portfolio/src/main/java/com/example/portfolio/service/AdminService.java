package com.example.portfolio.service;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.AdminRepository;
import com.example.portfolio.model.entity.Admin;
import com.example.portfolio.model.session.AdminSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class AdminService {

  @Autowired
  private AdminRepository adminRepos;

  @Autowired
  private AdminSession adminSession;

  public Admin findByAdminNameAndPassword(String adminName, String password) {
    return adminRepos.findByAdminNameAndPassword(adminName, password);
  }

}
