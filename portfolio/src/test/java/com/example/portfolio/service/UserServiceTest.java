package com.example.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.Optional;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.UserRepository;
import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.utils.Utils;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UserServiceTest {

  @Mock
  private UserRepository userRepos;

  @Mock
  private LoginSession loginSession;

  @Mock
  private Utils utils;

  @InjectMocks
  private UserService userSerive;

  @Captor
  ArgumentCaptor<User> argCaptor;

  @Test
  public void testUpdateUserImage() {
    // 正常処理
    when(loginSession.getUserId()).thenReturn(1);
    MockMultipartFile kmlfile = new MockMultipartFile("data", "filename.kml", "text/plain", "some kml".getBytes());
    when(userRepos.updateUserImage(any(), anyInt())).thenReturn(1);
    int actual = userSerive.updateUserImage(kmlfile);
    assertEquals(1, actual);

    // 例外処理
    MockMultipartFile nullFile = null;
    assertThrows(RuntimeException.class, () -> userSerive.updateUserImage(nullFile));
  }

  @Test
  public void testUpdateUserImageThrowException() {

  }

  @Test
  public void testSetLoginSession() {
    User user = new User();
    user.setUserId(1);
    user.setUserName("testAdmin");
    user.setEmail("example@gmail.com");
    user.setPassword("password");
    doNothing().when(loginSession).setTmpUserId(null);
    doNothing().when(loginSession).setLogined(true);
    doNothing().when(loginSession).setUserId(user.getUserId());
    doNothing().when(loginSession).setUserName(user.getUserName());
    doNothing().when(loginSession).setPassword(user.getPassword());
    doNothing().when(loginSession).setEmail(user.getEmail());
    userSerive.setLoginSession(user);
  }

  @Test
  public void testFindPaginatedList() {
    Optional<Integer> page = Optional.of(Integer.valueOf(0));
    when(utils.getCurrentPage(any())).thenReturn(1);
    Pageable pageable = mock(Pageable.class);
    @SuppressWarnings("unchecked")
    Page<User> expected = mock(Page.class);
    when(utils.getPageable(page)).thenReturn(pageable);
    when(userRepos.findAll(pageable)).thenReturn(expected);
    Page<User> actual = userSerive.findPaginatedList(page);
    assertEquals(expected, actual);
  }




}
