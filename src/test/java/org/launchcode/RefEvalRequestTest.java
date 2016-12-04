package org.launchcode;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.launchcode.refeval.controllers.OfficialController;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RefEvalRequestTest {
	
	@Test
	public void validDateTest() {
		
		String date1 = "5/1/16";
		String date2 = "March 15, 2016";
		String date3 = "March";
		
		assertTrue(date1, OfficialController.isValidDate(date1));
		assertFalse(date2, OfficialController.isValidDate(date2));
		assertFalse(date3, OfficialController.isValidDate(date3));
		
	}
	
	@Test
	public void validTimeTest(){
		
		String time1 =  "5 pm";
		String time2 = "5:00 pm";
		String time3 = "12 pm";
		String time4 = "12:15 am";
		
		assertFalse(time1, OfficialController.isValidTime(time1));
		assertTrue(time2, OfficialController.isValidTime(time2));
		assertFalse(time3, OfficialController.isValidTime(time3));
		assertTrue(time4, OfficialController.isValidTime(time4));
	}

}
