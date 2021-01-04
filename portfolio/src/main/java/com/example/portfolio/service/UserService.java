package com.example.portfolio.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.UserRepository;
import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.form.UserForm;
import com.example.portfolio.model.session.LoginSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Service
public class UserService {

  @Autowired
  private UserRepository userRepos;

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private ProductService productService;

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
   * 新規作成したユーザの取得
   *
   * @param userName
   * @param password
   * @return
   */
  public User findByUserNameAndPassword(String userName, String password) {
    return userRepos.findByUserNameAndPassword(userName, password);
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

  /**
   * プロフィール画像の更新
   *
   * @param file
   * @return
   */
  public int updateUserImage(MultipartFile file) {
    Integer userId = loginSession.getUserId();
    byte[] bytes;
    try {
      bytes = file.getBytes();
      return userRepos.updateUserImage(bytes, userId);
    } catch (IOException e) {
      e.printStackTrace();
      return 0;
    }
  }

  /**
   * ユーザ情報の更新
   *
   * @param userForm
   */
  public void updateUser(UserForm userForm) {
    userRepos.updateUser(userForm);
  }

  /**
   * byte[]からStringへの変換
   *
   * @param user
   * @return
   */
  public String getUserImg(User user) {
    return Base64.getEncoder().encodeToString(user.getUserImg());
  }

  public User findEmailByUserId(Integer userId) {
    return userRepos.findByUserId(userId);
  }

  public Page<User> findPaginatedList(Optional<Integer> page) {
    int currentPage = productService.getCurrentPage(page);
    Sort sort = Sort.by("userId").ascending(); // ソートのルールを作成
    Pageable pageable = PageRequest.of(currentPage - 1, 10, sort); // ページネーション情報作成
    return userRepos.findAll(pageable);
  }

}
