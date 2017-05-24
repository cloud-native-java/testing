package demo.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import sun.security.acl.PrincipalImpl;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

 @Autowired
 private MockMvc mvc;

 @MockBean
 private UserService userService;

 @MockBean
 private AuthService authService;

 @Test
 public void getUserShouldReturnUser() throws Exception {
  String content = "{\"username\": \"user\", \"firstName\": \"Jack\", \"lastName\": \"Frost\", \"email\": \"jfrost@example.com\"}";

  given(this.userService.getUserByPrincipal(new PrincipalImpl("user")))
   .willReturn(new User("user", "Jack", "Frost", "jfrost@example.com"));

  given(this.authService.getAuthenticatedUser(null)).willReturn(
   new PrincipalImpl("user"));

  this.mvc.perform(get("/uaa/v1/me").accept(MediaType.APPLICATION_JSON))
   .andExpect(status().isOk()).andExpect(content().json(content));
 }
}
