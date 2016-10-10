package demo.user;

import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByPrincipal(Principal principal) {
        User user = null;

        if (principal != null)
            user = userRepository.findUserByUsername(principal.getName());

        return user;
    }
}
