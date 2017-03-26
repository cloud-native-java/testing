package demo.account;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountNumberTests {

 private static final String ACCOUNT_NUMBER = "123456789";

 @Rule
 public ExpectedException thrown = ExpectedException.none();

 @Test
 public void createWhenAccountNumberIsNullShouldThrowException()
  throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Account Number must not be null");
  new AccountNumber(null);
 }

 @Test
 public void createWhenAccountNumberIsMoreThan9CharsShouldThrowException()
  throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Account Number must be exactly 9 characters");
  new AccountNumber("1234567890");
 }

 @Test
 public void createWhenAccountNumberIsLessThan9CharsShouldThrowException()
  throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Account Number must be exactly 9 characters");
  new AccountNumber("12345678");
 }

 @Test
 public void toStringShouldReturnAccountNumber() throws Exception {
  AccountNumber accountNumber = new AccountNumber(ACCOUNT_NUMBER);
  assertThat(accountNumber.toString()).isEqualTo(ACCOUNT_NUMBER);
 }

 @Test
 public void equalsAndHashCodeShouldBeBasedOnAccountNumber() throws Exception {
  AccountNumber accountNumber1 = new AccountNumber(ACCOUNT_NUMBER);
  AccountNumber accountNumber2 = new AccountNumber(ACCOUNT_NUMBER);
  AccountNumber accountNumber3 = new AccountNumber("000000000");
  assertThat(accountNumber1.hashCode()).isEqualTo(accountNumber2.hashCode());
  assertThat(accountNumber1).isEqualTo(accountNumber1)
   .isEqualTo(accountNumber2).isNotEqualTo(accountNumber3);
 }
}
