package demo.account;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPA {@link AttributeConverter} for
 * {@link AccountNumber}
 *
 * @author Kenny Bastani
 */
@Converter(autoApply = true)
public class AccountNumberAttributeConverter implements
 AttributeConverter<AccountNumber, String> {

 @Override
 public String convertToDatabaseColumn(AccountNumber attribute) {
  return attribute.toString();
 }

 @Override
 public AccountNumber convertToEntityAttribute(String dbData) {
  return new AccountNumber(dbData);
 }

}
