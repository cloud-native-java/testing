package demo.user;

import org.springframework.stereotype.Service;
import sun.security.acl.PrincipalImpl;

import java.security.Principal;

@Service
public class AuthService {

 public Principal getAuthenticatedUser(Principal principal) {
  return principal == null ? new PrincipalImpl("user") : principal;
 }
}
