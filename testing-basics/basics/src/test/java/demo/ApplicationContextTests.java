package demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationContextTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void confirmContextLoaded() throws Throwable {
		Assert.assertNotNull("the application context " +
						"should have been loaded.", this.applicationContext);
	}
}
