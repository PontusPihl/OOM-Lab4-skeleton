package se.ju.csi.oom.lab4;

public class Person implements Participant {
	
	// Constructors
	public Person(String name, DateTime dateOfBirth) {
		this(name);
		this.dateOfBirth = dateOfBirth;
	}
	public Person(String name) {
		super();
		this.name = name;
	}
	
	// Private fields
	private String name;
	private String socialSecurityNumber;
	private DateTime dateOfBirth;
	
	// Getters
	public String getName() {
		return name;
	}
	public DateTime getDateOfBirth() {
		return dateOfBirth;
	}
	public String getIdNr() {
		return socialSecurityNumber;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setDateOfBirth(DateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public void setIdNr(String idNr) {
		this.socialSecurityNumber = idNr;
	}
	
	// String representation
	public String toString() {
		return String.format("%s", this.name);
	}
}
