package org.launchcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.launchcode.models.Cheese;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheeseMvcApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void runSomeTests() {
		System.out.println("run some tests");
	}

	@Test
	public void testCheese() throws Exception {
		Cheese expectedCheese = new Cheese();
		expectedCheese.equals(new Cheese());
	}
}
