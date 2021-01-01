package com.example.portfolio.service;

import java.io.IOException;
import java.util.Base64;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.UserRepository;
import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.form.UserForm;
import com.example.portfolio.model.session.LoginSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

  public int update(UserForm userForm, MultipartFile file, Integer userId) {
    String userName = userForm.getUserName();
    String familyName = userForm.getFamilyName();
    String firstName = userForm.getFirstName();
    String email = userForm.getEmail();
    String password = userForm.getPassword();
    String imgPath = "/img/user/" + file.getOriginalFilename();
    return userRepos.update(userName, familyName, firstName, email, password, imgPath, userId);
  }

  // public void saveUserImg(MultipartFile file) throws Exception {
  // String folder =
  // "/Users/ryotonoguchi/Dropbox/Programing/Portfolio-OTA/portfolio/src/main/resources/static/img/user/";
  // // String folder = "src/main/resources/static/img/user/";
  // byte[] bytes = file.getBytes();
  // FileOutputStream output = new FileOutputStream(folder +
  // file.getOriginalFilename());
  // output.write(bytes);
  // output.close();
  // Path path = Paths.get(folder + file.getOriginalFilename());
  // Files.write(path, bytes);
  // }

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

  public void updateUser(UserForm userForm) {
    userRepos.updateUser(userForm);
  }

  public String getUserImg(User user) {
    return Base64.getEncoder().encodeToString(user.getUserImg());
  }
}
