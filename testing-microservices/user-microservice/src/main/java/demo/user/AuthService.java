package demo.user;

import org.springframework.stereotype.Service;
import sun.security.acl.PrincipalImpl;

import java.security.Principal;

@Service
public class AuthService {

	public Principal getAuthenticatedUser(Principal principal) {
		// TODO: Replace with actual implementation
		return principal == null ? new PrincipalImpl("user") : principal;
	}
}
