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
   * @param newUserName ユーザ名
   * @return 該当するユーザ件数
   */
  public int findByUserName(String newUserName) {
    return userRepos.findByUserName(newUserName);
  }

  /**
   * 新規ユーザレコード登録
   *
   * @param user User
   * @return 新規作成したユーザの件数
   */
  public int insertUser(User user) {
    return userRepos.insertUser(user);
  }

  /**
   * 新規作成したユーザの取得
   *
   * @param userName ユーザ名
   * @param password パスワード
   * @return User
   */
  public User findByUserNameAndPassword(String userName, String password) {
    return userRepos.findByUserNameAndPassword(userName, password);
  }

  /**
   * 主キーでユーザを取得
   *
   * @param userId ユーザID
   * @return User
   */
  public User findByUserId(Integer userId) {
    return userRepos.findByUserId(userId);
  }

  /**
   * LoginSessionにログイン情報を格納
   *
   * @param user User
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
   * @param file MultipartFile
   * @return 更新件数
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
   * @param userForm UserForm
   */
  public void updateUser(UserForm userForm) {
    userRepos.updateUser(userForm);
  }

  /**
   * ユーザ画像をbyte[]からStringへの変換
   *
   * @param user User
   * @return Stringにしたユーザ画像
   */
  public String getUserImg(User user) {
    return Base64.getEncoder().encodeToString(user.getUserImg());
  }

  /**
   * メールアドレスの取得
   * @param userId ユーザID
   * @return メールアドレス
   */
  public User findByUserId(int userId) {
    return userRepos.findByUserId(userId);
  }

  public String findEmailByUserId(int userId) {
    return userRepos.findEmailByUserId(userId);
  }

  /**
   * ページネーションされたユーザリストの取得
   * @param page リクエストされたページ番号
   * @return ページネーションされたユーザリスト
   */
  public Page<User> findPaginatedList(Optional<Integer> page) {
    int currentPage = productService.getCurrentPage(page);
    Sort sort = Sort.by("userId").ascending(); // ソートのルールを作成
    Pageable pageable = PageRequest.of(currentPage - 1, 10, sort); // ページネーション情報作成
    return userRepos.findAll(pageable);
  }

  /**
   * ユーザIDをもとにユーザ名を取得
   * @param userId ユーザID
   * @return ユーザ名
   */
  public String findUserNameByUserId(int userId) {
    return userRepos.findUserNameByUserId(userId);
  }

}
