package org.launchcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.launchcode.refeval.controllers.OfficialController;
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
	public void isValidDate(){
		//valid dates
		String date1 = "3/1/69";
		String date2 = "03/1/69";
		String date3 = "03/01/69";
		String date4 = "3/01/69";
		String date5 = "3/1/1969";
		String date6 = "03/1/1969";
		String date7 = "3/01/1969";
		String date8 = "03/01/1969";
		
		//invalid dates
		String date9 = "March 1, 1969";
		String date10 = "o3/1/69";
		String date11 = "o3/o1/69";
		String date12 = "3/o1/69";
		String date13 = "o3/1/1969";
		String date14 = "3/o1/1969";
		String date15 = "o3/o1/1969";
		String date16 = "/1/69";
		String date17 = "3//69";
		String date18 = "3/1/";
		String date19 = "/1/1969";
		String date20 = "3//1969";
		String date21 = "//1969";
		String date22 = "//69";
		String date23 = "03//69";
		String date24 = "03//1969";
		String date25 = "/01/69";
		String date26 = "/01/1969";
		String date27 = "3//";
		String date28 = "03//";
		String date29 = "/1/";
		String date30 = "/01/";
		
		assertTrue(OfficialController.isValidDate(date1));
		assertTrue(OfficialController.isValidDate(date2));
		assertTrue(OfficialController.isValidDate(date3));
		assertTrue(OfficialController.isValidDate(date4));
		assertTrue(OfficialController.isValidDate(date5));
		assertTrue(OfficialController.isValidDate(date6));
		assertTrue(OfficialController.isValidDate(date7));
		assertTrue(OfficialController.isValidDate(date8));
		assertFalse(OfficialController.isValidDate(date9));
		assertFalse(OfficialController.isValidDate(date10));
		assertFalse(OfficialController.isValidDate(date11));
		assertFalse(OfficialController.isValidDate(date12));
		assertFalse(OfficialController.isValidDate(date13));
		assertFalse(OfficialController.isValidDate(date14));
		assertFalse(OfficialController.isValidDate(date15));
		assertFalse(OfficialController.isValidDate(date16));
		assertFalse(OfficialController.isValidDate(date17));
		assertFalse(OfficialController.isValidDate(date18));
		assertFalse(OfficialController.isValidDate(date19));
		assertFalse(OfficialController.isValidDate(date20));
		assertFalse(OfficialController.isValidDate(date21));
		assertFalse(OfficialController.isValidDate(date22));
		assertFalse(OfficialController.isValidDate(date23));
		assertFalse(OfficialController.isValidDate(date24));
		assertFalse(OfficialController.isValidDate(date25));
		assertFalse(OfficialController.isValidDate(date26));
		assertFalse(OfficialController.isValidDate(date27));
		assertFalse(OfficialController.isValidDate(date28));
		assertFalse(OfficialController.isValidDate(date29));
		assertFalse(OfficialController.isValidDate(date30));
		
	}
	
	@Test
	public void isValidTimeTest(){
		//valid times
		String time1 = "2:15 pm";
		String time3 = "10:15 am";
		String time4 = "10:15 PM";
		String time5 = "10:59 PM";
		
		//invalid times...add here
		String time6 = "02:15 pm";
		String time7 = "13:00 am";
		String time8 = "2:65 pm";
		String time9 = "2:0 pm";
		String time10 = "2:00";
		String time11 = ":00 pm";
		String time12 = ":56";
		
		
		assertTrue(OfficialController.isValidTime(time1));
		assertTrue(OfficialController.isValidTime(time3));
		assertTrue(OfficialController.isValidTime(time4));
		assertTrue(OfficialController.isValidTime(time5));
		assertFalse(OfficialController.isValidTime(time6));
		assertFalse(OfficialController.isValidTime(time7));
		assertFalse(OfficialController.isValidTime(time8));
		assertFalse(OfficialController.isValidTime(time9));
		assertFalse(OfficialController.isValidTime(time10));
		assertFalse(OfficialController.isValidTime(time11));
		assertFalse(OfficialController.isValidTime(time12));
	}
	
}
