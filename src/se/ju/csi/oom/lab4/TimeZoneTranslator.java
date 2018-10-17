package se.ju.csi.oom.lab4;

import java.util.Arrays;
import java.util.HashSet;

public class TimeZoneTranslator {

	static int timesOverflow(int value,int validMin, int validMax)
	{
		int normVal = value-validMin;
		int normMax = validMax-validMin+1;
		//overflow
		if (normVal >= normMax)
			return normVal / normMax;
		
		//underflow
		int count = 0;
		while(normVal < 0)
		{
			normVal += normMax;
			count--;
		}		
		return count;
	}
	static int limitToInterval(int value, int validMin, int validMax)
	{
		int range = validMax - validMin + 1;
		while(value < validMin)
			value += range;
		while(value > validMax)
			value -= range;
		return value;
	}
	static DateTime shiftTimeZone(DateTime inputDate, int fromOffset, int toOffset) {
		
		int inputHour = inputDate.getHour();
		int gmtHour = inputHour - fromOffset;
		int targetHour = gmtHour + toOffset;

		int newDay=inputDate.getDay() + timesOverflow(targetHour, 0, 23);
		int newMonth=inputDate.getMonth() + timesOverflow(newDay, 1, 31);
		int newYear=inputDate.getYear() + timesOverflow(newMonth, 1, 12);
		
		DateTime targetDateTime = new DateTime(
				newYear,
				limitToInterval(newMonth, 1, 12),
				limitToInterval(newDay, 1, 31),
				limitToInterval(targetHour, 0, 23),
				inputDate.getMinute(),
				inputDate.getSecond());
		
		return targetDateTime;
	}
	
	static Event shiftEventTimeZone(Event inputEvent, TimeZone fromZone, TimeZone toZone) {
		DateTime shiftedStart = shiftTimeZone(inputEvent.getStartDate(), fromZone.getOffset(), toZone.getOffset());
		DateTime shiftedEnd = shiftTimeZone(inputEvent.getEndDate(), fromZone.getOffset(), toZone.getOffset());
		Event targetEvent = new Event(inputEvent.getLabel(), shiftedStart, shiftedEnd, inputEvent.getAttendees(), inputEvent.getLocation());
		return targetEvent;
	}
	
	public static void main(String [ ] args) {
		DateTime LectureStart = new DateTime(2018, 8, 27, 8, 0, 0);
		DateTime LectureEnd = new DateTime(2018, 8, 27, 9, 45, 0);
		Person johannes = new Person("Johannes Schmidt");
		Person ragnar = new Person("Ragnar Nohre");
		Place HC218 = new Place("Hc218",57.7785672,14.1614833,20.0);
		
		Event firstOomLecture = new Event("OOM 2018 Lecture 1",
				LectureStart,
				LectureEnd,
				new HashSet<>(Arrays.asList(johannes, ragnar)),
				HC218);
		
		System.out.println(String.format("============\nOriginal event\n============\n%s", firstOomLecture.toString()));
		System.out.println();
		System.out.println(String.format("========================\nEvent shifted to Boston time\n========================\n%s", shiftEventTimeZone(firstOomLecture, TimeZone.CENTRAL_EUROPEAN_TIME, TimeZone.US_EASTERN).toString()));
	}
}
