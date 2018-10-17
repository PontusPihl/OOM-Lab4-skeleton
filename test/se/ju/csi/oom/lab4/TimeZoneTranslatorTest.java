package se.ju.csi.oom.lab4;

import static org.junit.Assert.*;

import java.io.Console;

import org.junit.Before;
import org.junit.Test;

public class TimeZoneTranslatorTest {
	
	@Before
	public void setUp() throws Exception {
	} 

	public void checkDateTime(int expectedYear,int expectedMonth,int expectedDay,
			int expectedHour,int expectedMinute,int expectedSecond,DateTime dateTime)
	{
		String expectedString = String.format("%04d-%02d-%02d %02d:%02d", 
				expectedYear, expectedMonth, expectedDay, expectedHour, expectedMinute, expectedSecond);		
		
		assertEquals(expectedString, dateTime.toString());
	}
	
	@Test
	public void testShiftTimeZone()
	{	
		DateTime dateTime;
		
		
		//simple_forwards 0 -> 12
		dateTime=new DateTime(2018, 1, 1, 10, 0, 0);
		dateTime = TimeZoneTranslator.shiftTimeZone(dateTime, TimeZone.GREENWICH_UTC.getOffset(), TimeZone.NEW_ZEALAND.getOffset());
		checkDateTime(2018,1,1,22,0,0,dateTime);	
		//simple_forwards -12 -> 0
		dateTime=new DateTime(2018, 1, 1, 10, 0, 0);
		dateTime = TimeZoneTranslator.shiftTimeZone(dateTime, TimeZone.INTERNATIONAL_DATE_LINE_WEST.getOffset(), TimeZone.GREENWICH_UTC.getOffset());
		checkDateTime(2018,1,1,22,0,0,dateTime);
		//simple_forwards -6 -> 6
		dateTime=new DateTime(2018, 1, 1, 10, 0, 0);
		dateTime = TimeZoneTranslator.shiftTimeZone(dateTime, TimeZone.US_CENTRAL.getOffset(), TimeZone.BANGLADESH.getOffset());
		checkDateTime(2018,1,1,22,0,0,dateTime);
		
		//simple_backwards 0 -> -12
		dateTime=new DateTime(2018, 1, 1, 22, 0, 0);
		dateTime = TimeZoneTranslator.shiftTimeZone(dateTime, TimeZone.GREENWICH_UTC.getOffset(), TimeZone.INTERNATIONAL_DATE_LINE_WEST.getOffset());
		checkDateTime(2018,1,1,10,0,0,dateTime);
		
		//-----------------------------------
		//overflow_day 0 -> 9
		dateTime=new DateTime(2018, 1, 1, 22, 0, 0);
		dateTime = TimeZoneTranslator.shiftTimeZone(dateTime, TimeZone.GREENWICH_UTC.getOffset(), TimeZone.JAPAN.getOffset());
		checkDateTime(2018,1,2,7,0,0,dateTime);
		//overflow_year 0 -> 9
		dateTime=new DateTime(2015, 12, 31, 21, 0, 0);
		dateTime = TimeZoneTranslator.shiftTimeZone(dateTime, TimeZone.GREENWICH_UTC.getOffset(), TimeZone.JAPAN.getOffset());
		checkDateTime(2016,1,1,6,0,0,dateTime);
		
		//underflow_day 0 -> -9
		dateTime=new DateTime(2018, 1, 10, 02, 0, 0);
		dateTime = TimeZoneTranslator.shiftTimeZone(dateTime, TimeZone.GREENWICH_UTC.getOffset(), TimeZone.ALASKA.getOffset());
		checkDateTime(2018,1,9,17,0,0,dateTime);
		//underflow_year 0 -> -9
		dateTime=new DateTime(2016, 01, 01, 06, 0, 0);
		dateTime = TimeZoneTranslator.shiftTimeZone(dateTime, TimeZone.GREENWICH_UTC.getOffset(), TimeZone.ALASKA.getOffset());
		checkDateTime(2015,12,31,21,0,0,dateTime);
		//----------------------------
		
	}

	@Test
	public void testShiftEventTimeZone() 
	{
		Event event=new Event(null, null, null, null, null);
		
		//forwards 0 -> 6
		event.setStartDate(new DateTime(2018,1,1, 10,0,0));
		event.setEndDate(  new DateTime(2018,1,1, 12,0,0));
		event = TimeZoneTranslator.shiftEventTimeZone(event, TimeZone.GREENWICH_UTC, TimeZone.BANGLADESH);
		checkDateTime(2018,1,1,16,0,0,event.getStartDate());
		checkDateTime(2018,1,1,18,0,0,event.getEndDate());
		
		//backwards 0 -> -6
		event.setStartDate(new DateTime(2018,1,1, 16,0,0));
		event.setEndDate(  new DateTime(2018,1,1, 18,0,0));
		event = TimeZoneTranslator.shiftEventTimeZone(event, TimeZone.GREENWICH_UTC, TimeZone.US_CENTRAL);
		checkDateTime(2018,1,1,10,0,0,event.getStartDate());
		checkDateTime(2018,1,1,12,0,0,event.getEndDate());
		
		//----------------------------------------
		//partial overflow 0 -> 8
		event.setStartDate(new DateTime(2017,12,31, 14,0,0));
		event.setEndDate(  new DateTime(2017,12,31, 18,0,0));
		event = TimeZoneTranslator.shiftEventTimeZone(event, TimeZone.GREENWICH_UTC, TimeZone.SINGAPORE);
		checkDateTime(2017,12,31,22,0,0,event.getStartDate());
		checkDateTime(2018,1,1,2,0,0,event.getEndDate());
		
		//full overflow 0 -> 12
		event.setStartDate(new DateTime(2017,12,31, 14,0,0));
		event.setEndDate(  new DateTime(2017,12,31, 18,0,0));
		event = TimeZoneTranslator.shiftEventTimeZone(event, TimeZone.GREENWICH_UTC, TimeZone.NEW_ZEALAND);
		checkDateTime(2018,1,1,2,0,0,event.getStartDate());
		checkDateTime(2018,1,1,6,0,0,event.getEndDate());
		
		//partial underflow 0 -> -8
		event.setStartDate(new DateTime(2018, 1, 1, 6,0,0));
		event.setEndDate(  new DateTime(2018, 1, 1, 14,0,0));
		event = TimeZoneTranslator.shiftEventTimeZone(event, TimeZone.GREENWICH_UTC, TimeZone.US_PACIFIC);
		checkDateTime(2017,12,31,22,0,0,event.getStartDate());
		checkDateTime(2018,1,1,6,0,0,event.getEndDate());
		
		//full underflow 0 -> -12
		event.setStartDate(new DateTime(2018, 1, 1, 2,0,0));
		event.setEndDate(  new DateTime(2018, 1, 1, 8,0,0));
		event = TimeZoneTranslator.shiftEventTimeZone(event, TimeZone.GREENWICH_UTC, TimeZone.INTERNATIONAL_DATE_LINE_WEST);
		checkDateTime(2017,12,31,14,0,0,event.getStartDate());
		checkDateTime(2017,12,31,20,0,0,event.getEndDate());
	}
}
