package demo.account;

import org.springframework.util.Assert;

/**
 * An Account Number for the {@link Account} entity
 *
 * @author Kenny Bastani
 */
public class AccountNumber {

    private String accountNumber;

    public AccountNumber(String accountNumber) {
        Assert.notNull(accountNumber, "Account Number must not be null");
        Assert.isTrue(accountNumber.length() == 9, "Account Number must be exactly 9 characters");
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
