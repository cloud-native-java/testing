package demo;

import demo.user.User;
import demo.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = { "cnj:user-microservice:+:stubs:8081" }, workOffline = true) // <1>
public class ConsumerDrivenTests {

 @Autowired
 private UserService service; // <2>

 @Test
 public void shouldReturnAuthenticatedUser() {
  User actual = service.getAuthenticatedUser();

  assertThat(actual).isNotNull();
  assertThat(actual.getUsername()).matches("[A-Za-z0-9]+");
  assertThat(actual.getFirstName()).matches("[A-Za-z]+");
  assertThat(actual.getLastName()).matches("[A-Za-z]+");
  assertThat(actual.getEmail()).matches(
   "[A-Za-z0-9]+\\@[A-Za-z0-9]+\\.[A-Za-z]+");
 }
}
