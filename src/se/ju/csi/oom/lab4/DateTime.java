package se.ju.csi.oom.lab4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTime {

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	
	public String toString() {
		return String.format("%04d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
	}
	
	public DateTime(int year, int month, int day, int hour, int minute, int second) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public DateTime(String dateString) {
		Pattern p = Pattern.compile("(\\d\\d\\d\\d)-(\\d\\d)-(\\d\\d) (\\d\\d):(\\d\\d):(\\d\\d)");
		Matcher m = p.matcher(dateString);
		m.find();
		this.year = Integer.parseInt(m.group(1));
		this.month = Integer.parseInt(m.group(2));
		this.day = Integer.parseInt(m.group(3));
		this.hour = Integer.parseInt(m.group(4));
		this.minute = Integer.parseInt(m.group(5));
		this.second = Integer.parseInt(m.group(6));
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}
}
