package demo.account;

import demo.user.User;
import demo.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@MockBean
	private UserService userService;

	@Test
	public void userServiceShouldReturnMockResponse() {
		given(this.userService.getAuthenticatedUser()).willReturn(
				new User(0L, "bobbyd", "Bob", "Dylan"));

		User actual = userService.getAuthenticatedUser();
		assertThat(actual).isNotNull();
		assertThat(actual.getFirstName()).isEqualTo("Bob");
		assertThat(actual.getLastName()).isEqualTo("Dylan");
	}

}