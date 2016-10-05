package demo.account;

import demo.creditcard.CreditCard;
import demo.address.Address;
import demo.customer.Customer;
import demo.data.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This entity stores information about a {@link Customer}'s {@link Account}.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
@Entity
public class Account extends BaseEntity {

    private Long id;
    private String userId;
    private AccountNumber accountNumber;
    private Boolean defaultAccount;
    private Set<CreditCard> creditCards;
    private Set<Address> addresses;

    public Account() {
        this.creditCards = new HashSet<>();
        this.addresses = new HashSet<>();
        this.defaultAccount = false;
    }

    public Account(String userId, AccountNumber accountNumber) {
        this();
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.defaultAccount = false;
    }

    public Account(String userId, String accountNumber) {
        this();
        this.userId = userId;
        this.accountNumber = new AccountNumber(accountNumber);
        this.defaultAccount = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AccountNumber getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Boolean getDefaultAccount() {
        return defaultAccount;
    }

    public void setDefaultAccount(Boolean defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Set<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", accountNumber=" + accountNumber +
                ", defaultAccount=" + defaultAccount +
                ", creditCards=" + creditCards +
                ", addresses=" + addresses +
                "} " + super.toString();
    }
}
