package demo.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

 @Autowired
 private UserRepository userRepository;

 @Autowired
 private TestEntityManager entityManager;

 @Before
 public void setUp() {
  this.entityManager.persist(new User("jack", "Jack", "Frost",
   "jfrost@example.com"));
 }

 @Test
 public void findUserShouldReturnUser() throws Exception {
  User actual = this.userRepository.findUserByUsername("jack");
  assertThat(actual).isNotNull();
  assertThat(actual.getUsername()).isEqualTo("jack");
  assertThat(actual.getFirstName()).isEqualTo("Jack");
  assertThat(actual.getLastName()).isEqualTo("Frost");
  assertThat(actual.getEmail()).isEqualTo("jfrost@example.com");
 }

 @Test
 public void findUserShouldReturnNull() throws Exception {
  User user = this.userRepository.findUserByUsername("jill");
  assertThat(user).isNull();
 }
}
