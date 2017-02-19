package demo.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(path = "/uaa/v1")
public class UserController {

 private UserService userService;
 private AuthService authService;

 public UserController(UserService userService, AuthService authService) {
  this.userService = userService;
  this.authService = authService;
 }

 @RequestMapping(path = "/me")
 public ResponseEntity<User> me(Principal principal) throws Exception {
  return Optional
    .ofNullable(authService.getAuthenticatedUser(principal))
    .map(
      p -> new ResponseEntity<User>(userService.getUserByPrincipal(p),
        HttpStatus.OK)).orElse(new ResponseEntity<User>(HttpStatus.UNAUTHORIZED));
 }
}
