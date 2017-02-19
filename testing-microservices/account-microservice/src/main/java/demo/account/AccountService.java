package demo.account;

import demo.user.User;
import demo.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

 private AccountRepository accountRepository;
 private UserService userService;

 public AccountService(AccountRepository accountRepository, UserService userService) {
  this.accountRepository = accountRepository;
  this.userService = userService;
 }

 public List<Account> getUserAccounts() {
  List<Account> accounts = null;
  User user = userService.getAuthenticatedUser();

  if (user != null)
   accounts = accountRepository.findAccountsByUsername(user.getUsername());

  return accounts;
 }
}
