package demo.account;

import demo.address.Address;
import demo.address.AddressType;
import demo.creditcard.CreditCard;
import demo.creditcard.CreditCardType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class AccountTests {

 private Account account;

 @Autowired
 private JacksonTester<Account> json;

 @Before
 public void setUp() throws Exception {
  Account account = new Account("user", "123456789");
  account.setId(0L);
  account.setDefaultAccount(true);
  account.setCreatedAt(12345L);
  account.setLastModified(12346L);

  // Generate account address
  Address address = new Address("111 Pine St", "Apt D", "CA", "San Francisco",
   "US", AddressType.BILLING, 94110);
  address.setId(0L);
  address.setCreatedAt(12345L);
  address.setLastModified(12346L);
  account.setAddresses(Collections.singleton(address));

  // Generate account credit card
  CreditCard card = new CreditCard("1111-1111-1111-1111", CreditCardType.VISA);
  card.setId(0L);
  card.setCreatedAt(12345L);
  card.setLastModified(12346L);
  account.setCreditCards(Collections.singleton(card));

  this.account = account;
 }

 @Test
 public void serializeJson() throws Exception {
  assertThat(this.json.write(account)).isEqualTo("account.json");
  assertThat(this.json.write(account)).isEqualToJson("account.json");
  assertThat(this.json.write(account)).hasJsonPathStringValue("@.username");

  assertThat(this.json.write(account)).extractingJsonPathStringValue(
   "@.username").isEqualTo("user");

  assertThat(this.json.write(account)).extractingJsonPathStringValue(
   "@.accountNumber").isEqualTo("123456789");
 }

 @Test
 public void deserializeJson() throws Exception {
  String content = "{\"username\": \"user\", \"accountNumber\": \"123456789\"}";
  assertThat(this.json.parse(content)).isEqualTo(
   new Account("user", "123456789"));
  assertThat(this.json.parseObject(content).getUsername()).isEqualTo("user");
 }
}
