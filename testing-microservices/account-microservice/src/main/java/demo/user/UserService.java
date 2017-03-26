package demo.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class UserService {

 @Value("${user-service.host:user-service}")
 private String serviceHost;

 private RestTemplate restTemplate;

 public UserService(RestTemplate restTemplate) {
  this.restTemplate = restTemplate;
 }

 public User getAuthenticatedUser() {
  HttpHeaders headers = new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_JSON);
  return restTemplate.exchange(
   new RequestEntity<>(headers, HttpMethod.GET, URI.create(String.format(
    "http://%s/uaa/v1/me", serviceHost))), User.class).getBody();
 }
}
