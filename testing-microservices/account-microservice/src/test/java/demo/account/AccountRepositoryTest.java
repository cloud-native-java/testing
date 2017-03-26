package demo.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

 private static final AccountNumber ACCOUNT_NUMBER = new AccountNumber(
  "098765432");

 @Autowired
 private TestEntityManager entityManager;

 @Autowired
 private AccountRepository accountRepository;

 @Test
 public void findUserAccountsShouldReturnAccounts() throws Exception {
  this.entityManager.persist(new Account("jack", ACCOUNT_NUMBER));
  List<Account> account = this.accountRepository.findAccountsByUsername("jack");
  assertThat(account).size().isEqualTo(1);
  Account actual = account.get(0);
  assertThat(actual.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
  assertThat(actual.getUsername()).isEqualTo("jack");
 }

 @Test
 public void findAccountShouldReturnAccount() throws Exception {
  this.entityManager.persist(new Account("jill", ACCOUNT_NUMBER));
  Account account = this.accountRepository
   .findAccountByAccountNumber(ACCOUNT_NUMBER);
  assertThat(account).isNotNull();
  assertThat(account.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
 }

 @Test
 public void findAccountShouldReturnNull() throws Exception {
  this.entityManager.persist(new Account("jack", ACCOUNT_NUMBER));
  Account account = this.accountRepository
   .findAccountByAccountNumber(new AccountNumber("000000000"));
  assertThat(account).isNull();
 }
}
