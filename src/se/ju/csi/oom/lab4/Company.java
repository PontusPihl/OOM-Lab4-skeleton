package se.ju.csi.oom.lab4;

public class Company implements Participant {
	
	// Constructors
	public Company(String name) {
		super();
		this.companyName = name;
	}
	public Company(String name, String idNr) {
		this(name);
		this.vatNo = idNr;
	}
	
	private String companyName;
	private String vatNo;

	// Getters
	public String getName() {
		return this.companyName;
	}
	public String getIdNr() {
		return this.vatNo;
	}

	// Setters
	public void setName(String name) {
		this.companyName = name;
	}
	public void setIdNr(String idNr) {
		this.vatNo = idNr;
	}

	// String representation
	public String toString() {
		return String.format("%s (%s)", this.companyName, this.vatNo);
	}
}
