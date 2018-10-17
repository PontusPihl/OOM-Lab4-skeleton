package se.ju.csi.oom.lab4;

import java.util.Set;

public class Event {
	
	// Constructor
	public Event(String label, DateTime startDate, DateTime endDate, Set<Participant> attendees, Place location) {
		super();
		this.label = label;
		this.startDate = startDate;
		this.endDate = endDate;
		this.attendees = attendees;
		this.location = location;
	}
	
	// Private fields
	private String label;
	private DateTime startDate;
	private DateTime endDate;
	private Set<Participant> attendees;
	private Place location;
	
	// Getters
	public String getLabel() {
		return label;
	}
	public DateTime getStartDate() {
		return startDate;
	}
	public DateTime getEndDate() {
		return endDate;
	}
	public Set<Participant> getAttendees() {
		return attendees;
	}
	public Place getLocation() {
		return location;
	}
	
	// Setters
	public void setLabel(String label) {
		this.label = label;
	}
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
	public void setAttendees(Set<Participant> attendees) {
		this.attendees = attendees;
	}
	public void setLocation(Place location) {
		this.location = location;
	}
	
	// Methods
	public String toString() {
		return String.format("Event label:\t%s\nStart date:\t%s\nEnd date:\t%s\nAttendees:\t%s\nPlace:\t\t%s",
				this.label,
				this.startDate,
				this.endDate,
				this.attendees,
				this.location.getName());
	}
}
