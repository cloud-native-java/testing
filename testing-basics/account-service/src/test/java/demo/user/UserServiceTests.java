package demo.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest({ UserService.class })
@AutoConfigureWebClient(registerRestTemplate = true)
public class UserServiceTests {

 @Value("${user-service.host:user-service}")
 private String serviceHost;

 @Autowired
 private UserService userService;

 @Autowired
 private MockRestServiceServer server;

 @Test
 public void getAuthenticatedUserShouldReturnUser() {
  // Mock the expected response from the
  // user
  // service
  this.server.expect(
   requestTo(String.format("http://%s/uaa/v1/me", serviceHost))).andRespond(
   withSuccess(getClassPathResource("user.json"), MediaType.APPLICATION_JSON));

  User user = userService.getAuthenticatedUser();

  assertThat(user.getUsername()).isEqualTo("user");
  assertThat(user.getFirstName()).isEqualTo("John");
  assertThat(user.getLastName()).isEqualTo("Doe");
  assertThat(user.getCreatedAt()).isEqualTo(12345);
  assertThat(user.getLastModified()).isEqualTo(12346);
  assertThat(user.getId()).isEqualTo(0L);
 }

 private ClassPathResource getClassPathResource(String path) {
  return new ClassPathResource(path, getClass());
 }
}
