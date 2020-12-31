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

  /**
   * メールアドレスとパスワードを条件にユーザ取得してログインユーザと照合
   *
   * @param userName
   * @param password
   * @return
   */
  public User findByEmailAndPassword(String email, String password) {
    return userRepos.findByEmailAndPassword(email, password);
  }

  /**
   * ユーザ名重複確認
   *
   * @param newUserName
   * @return
   */
  public int findByUserName(String newUserName) {
    return userRepos.findByUserName(newUserName);
  }


}
