package demo.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

 private final String serviceHost;
 private final RestTemplate restTemplate;

 public UserService(RestTemplate restTemplate,
   @Value("${user-service.host:user-service}") String sh) {
  this.serviceHost = sh;
  this.restTemplate = restTemplate;
 }

 public User getAuthenticatedUser() {
  return restTemplate.getForObject(String.format("http://%s/uaa/v1/me", serviceHost),
    User.class);
 }
}
