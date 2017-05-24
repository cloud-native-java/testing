package demo;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import demo.user.AuthService;
import demo.user.User;
import demo.user.UserController;
import demo.user.UserService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import sun.security.acl.PrincipalImpl;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserServiceBase {

 @MockBean
 private UserService userService;

 @MockBean
 private AuthService authService;

 @Before
 public void setup() {

  User actual = new User("user", "Jack", "Frost", "jfrost@example.com");
  actual.setLastModified(12345L);
  actual.setCreatedAt(12345L);
  actual.setId(0L);
  given(this.userService.getUserByPrincipal(new PrincipalImpl("user")))
   .willReturn(actual);

  given(this.authService.getAuthenticatedUser(null)).willReturn(
   new PrincipalImpl("user"));

  RestAssuredMockMvc.standaloneSetup(new UserController(userService,
   authService));
 }

 public void assertThatRejectionReasonIsNull(Object rejectionReason) {
  assert rejectionReason == null;
 }
}