package demo.account;

import demo.user.User;
import demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private UserService userService;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    public List<Account> getUserAccounts() {
        List<Account> accounts = null;
        User user = userService.getAuthenticatedUser();

        if (user != null) {
            accounts = accountRepository.findAccountsByUserId(user.getUsername());

            // Mask credit card numbers
            if (accounts != null) {
                accounts.forEach(acct -> acct.getCreditCards()
                        .forEach(card ->
                                card.setNumber(card.getNumber()
                                        .replaceAll("([\\d]{4})(?!$)", "****-"))));
            }
        }

        return accounts;
    }
}
