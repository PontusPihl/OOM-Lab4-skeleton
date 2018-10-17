package se.ju.csi.oom.lab4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DateTimeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToString() 
	{
		DateTime dateTime=new DateTime(2018, 12, 24, 16, 30,30);		
		assertEquals("2018-12-24 16:30:30", dateTime.toString());
		
		dateTime=new DateTime(666, 1, 1, 5, 5,5);		
		assertEquals("0666-01-01 05:05:05", dateTime.toString());
	}

	@Test
	public void testDateTimeString()
	{
		String rawString="2018-12-24 16:30:30";
		DateTime dateTime=new DateTime(rawString);		
		assertEquals(rawString, dateTime.toString());

		dateTime=new DateTime("0666-01-01 05:05:05");		
		assertEquals("0666-01-01 05:05:05", dateTime.toString());
	}
}
