package demo.creditcard;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class CreditCardTests {

 private CreditCard creditCard;

 @Autowired
 private JacksonTester<CreditCard> json;

 @Before
 public void setUp() throws Exception {
  // Generate account credit card
  CreditCard card = new CreditCard("1111-1111-1111-1111", CreditCardType.VISA);
  card.setId(0L);
  card.setCreatedAt(12345L);
  card.setLastModified(12346L);
  this.creditCard = card;
 }

 @Test
 public void serializeJson() throws Exception {
  assertThat(this.json.write(creditCard)).isEqualTo("creditcard.json");
  assertThat(this.json.write(creditCard)).isEqualToJson("creditcard.json");
  assertThat(this.json.write(creditCard)).hasJsonPathStringValue("@.number");

  assertThat(this.json.write(creditCard)).extractingJsonPathStringValue(
   "@.number").isEqualTo("1111-1111-1111-1111");

  assertThat(this.json.write(creditCard)).extractingJsonPathStringValue(
   "@.type").isEqualTo("VISA");
 }

 @Test
 public void deserializeJson() throws Exception {
  String content = "{\n" + "  \"createdAt\": 12345,\n"
   + "  \"lastModified\": 12346,\n" + "  \"id\": 0,\n"
   + "  \"number\": \"1111-1111-1111-1111\",\n" + "  \"type\": \"VISA\"\n"
   + "}";
  assertThat(this.json.parse(content)).isEqualTo(creditCard);
  assertThat(this.json.parseObject(content).getNumber()).isEqualTo(
   "1111-1111-1111-1111");
 }
}
