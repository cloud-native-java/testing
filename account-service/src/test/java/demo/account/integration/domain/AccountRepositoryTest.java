package demo.account.integration.domain;

import demo.account.Account;
import demo.account.AccountRepository;
import demo.customer.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    private Logger log = LoggerFactory.getLogger(AccountRepositoryTest.class);

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testExample() throws Exception {
        this.entityManager.persist(new Account("jack", "098765432"));
        List<Account> account = this.accountRepository.findAccountsByUserId("jack");
        assertThat(account).size().isEqualTo(1);
        Account actual = account.get(0);
        assertThat(actual.getAccountNumber()).isEqualTo("098765432");
        assertThat(actual.getUserId()).isEqualTo("jack");
    }
}
