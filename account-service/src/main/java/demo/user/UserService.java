package demo.user;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User getAuthenticatedUser() {
        return restTemplate.getForObject("http://user-service/uaa/v1/me", User.class);
    }
}
