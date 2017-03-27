package demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserService {

 private final UserRepository userRepository;

 @Autowired
 public UserService(UserRepository userRepository) {
  this.userRepository = userRepository;
 }

 public User getUserByPrincipal(Principal principal) {
  // <1>
  return Optional.ofNullable(principal)
   .map(p -> userRepository.findUserByUsername(p.getName())).orElse(null);
 }
}
