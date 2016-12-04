package org.launchcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.launchcode.refeval.models.Official;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RefEvalApplicationTests extends TestCase {

	//test official, evaluator and Admin are created correctly
	@Test
	public void testUserInit() {
		Official official = new Official("bob", "cohen", "indyracer", "whootie1969", 3, true, false, false);
		Official evaluator = new Official("susan", "cohen", "wife", "may12004", 0, false, true, false);
		Official admin = new Official ("zach", "cohen", "oldestson", "zach192006", 0 , false, false, true);
		//official tests
		assertEquals("bob", official.getFirstName());
		assertEquals("cohen", official.getLastName());
		assertEquals("indyracer", official.getUsername());
		assertEquals(3, official.getLevel());
		assertTrue(official.getIsOfficial());
		assertFalse(official.getIsAdmin());
		assertFalse(official.getIsEvaluator());
		
		
		//evaluator test
		assertEquals("susan", evaluator.getFirstName());
		assertEquals("cohen", evaluator.getLastName());
		assertEquals("wife", evaluator.getUsername());
		assertEquals(0, evaluator.getLevel());
		assertFalse(evaluator.getIsOfficial());
		assertFalse(evaluator.getIsAdmin());
		assertTrue(evaluator.getIsEvaluator());
		
		//admin test
		assertEquals("zach", admin.getFirstName());
		assertEquals("cohen", admin.getLastName());
		assertEquals("oldestson", admin.getUsername());
		assertEquals(0, admin.getLevel());
		assertFalse(admin.getIsOfficial());
		assertTrue(admin.getIsAdmin());
		assertFalse(admin.getIsEvaluator());
	}
	
	@Test
	public void testValidUsername(){
		assertTrue("indyracer", Official.isValidUsername("indyracer"));
		assertFalse("indy", Official.isValidUsername("indy"));//username length too short
		assertFalse("indianapolisindiana20", Official.isValidUsername("indianapolisindiana20")); //username too long
		assertFalse("indy#500", Official.isValidUsername("indy#500"));//invalid character
	}
	
	@Test
	public void testValidPassword(){
		assertTrue("testpass", Official.isValidPassword("testpass"));
		assertFalse("indy", Official.isValidPassword("indy"));//password too short
		assertFalse("indianapolisindiana20", Official.isValidPassword("indianapolisindiana20"));//password too long
	}
	
	@Test
	public void testHashPassword(){
		Official official = new Official("zach", "cohen", "oldestson", "zach192006", 1, true, false, false);
		assertEquals("$2a$10$oheqw7LKsPMJo1V3E8uxweYVZLHz1/9CXG8HJE9yMEvBddKGrQ88i", official.getPwHash());//why doesn't this match what's in MySQL?
	}
	
}
