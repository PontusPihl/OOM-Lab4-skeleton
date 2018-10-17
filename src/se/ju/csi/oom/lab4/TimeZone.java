package se.ju.csi.oom.lab4;

public enum TimeZone {
	INTERNATIONAL_DATE_LINE_WEST(-12),
	AMERICAN_SAMOA(-11),
	HAWAII(-10),
	ALASKA(-9),
	US_PACIFIC(-8),
	US_MOUNTAIN(-7),
	US_CENTRAL(-6),
	US_EASTERN(-5),
	HALIFAX(-4),
	BUENOS_AIRES(-3),
	GREENLAND(-2),
	AZORES(-1),
	GREENWICH_UTC(0),
	CENTRAL_EUROPEAN_TIME(1),
	EASTERN_EUROPEAN_TIME(2),
	MOSCOW(3),
	UNITED_ARAB_EMIRATES(4),
	PAKISTAN(5),
	BANGLADESH(6),
	THAILAND(7),
	SINGAPORE(8),
	JAPAN(9),
	SYDNEY(10),
	SOLOMON_ISLANDS(11),
	NEW_ZEALAND(12)
	;
	
	// Enumeration constructor
	TimeZone(int offset) {
		this.offset = offset;
	}
	
	// Enumeration value private field
	private final int offset;
	
	// Enumeration value getter
	public int getOffset() {
		return this.offset;
	}
}
