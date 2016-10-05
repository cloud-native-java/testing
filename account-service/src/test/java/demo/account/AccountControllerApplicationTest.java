package demo.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.account.Account;
import demo.account.AccountService;
import demo.user.User;
import demo.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AccountControllerApplicationTest {

    private static final String ACCOUNT_NUMBER = "123456789";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AccountService accountService;

    @MockBean
    private UserService userService;

    @Test
    public void exampleTest() throws Exception {
        given(this.userService.getAuthenticatedUser())
                .willReturn(new User(0L, "user", "John", "Doe"));

        Account expected = new Account("user", ACCOUNT_NUMBER);
        expected.setId(123L);

        this.mvc.perform(get("/v1/accounts").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(
                new ObjectMapper()
                        .writeValueAsString(
                                Collections.singletonList(expected))));
    }

}
