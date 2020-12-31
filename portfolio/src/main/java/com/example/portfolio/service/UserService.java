package com.example.portfolio.service;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.UserRepository;
import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.session.LoginSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserService {

  @Autowired
  private UserRepository userRepos;

  @Autowired
  private LoginSession loginSession;

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

  /**
   * 新規ユーザレコード登録
   *
   * @param user
   * @return
   */
  public int insertUser(User user) {
    return userRepos.insertUser(user);
  }

  /**
   * 主キーでユーザを取得
   *
   * @param userId
   * @return
   */
  public User findByUserId(Integer userId) {
    return userRepos.findByUserId(userId);
  }

  /**
   * LoginSessionにログイン情報を格納
   *
   * @param user
   */
  public void setLoginSession(User user) {
    if (user != null) { // ユーザが存在すれば
      loginSession.setTmpUserId(null); // トップページ初期表示時に付与した仮ユーザIDをnullにして破棄
      loginSession.setLogined(true); // ログイン状態にする
      loginSession.setUserId(user.getUserId());
      loginSession.setUserName(user.getUserName());
      loginSession.setPassword(user.getPassword());
      loginSession.setEmail(user.getEmail());
    } else { // 一致するユーザ情報がなければ、仮ユーザIDはそのまま
      loginSession.setLogined(false);
      loginSession.setUserId(null);
      loginSession.setUserName(null);
      loginSession.setPassword(null);
      loginSession.setEmail(null);
    }
  }

  public User findByUserNameAndPassword(String userName, String password) {
    return userRepos.findByUserNameAndPassword(userName, password);
  }
}
