package demo.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Value("${user-service.host:user-service}")
    private String serviceHost;

    private RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User getAuthenticatedUser() {
        return restTemplate.getForObject(
                String.format("http://%s/uaa/v1/me", serviceHost), User.class);
    }
}
