package com.example.hired;

public class Company {

	private String name;
	private String email;
	private String ageMinimum;
	private String town;
	private String state;
	private Location location;
	private String experienceReq;
	private String companyType;
	
	public Company() {
		name = "No name provided";
		email = "No email provided";
		ageMinimum = "16";
		location = new Location();
		experienceReq = "No prior experience";
		companyType = "No occupation type provided";
	}
	
	public Company(String nameIn, String emailIn,  String companyAgeMin, Location l, String companyExperienceReq, String companyOccupationType) {
		name = nameIn;
		email = emailIn;
		ageMinimum = companyAgeMin;
		experienceReq = companyExperienceReq;
		companyType = companyOccupationType;
		location = l;
		town = l.getCity();
		state =l.getState();
	}

	public Company(Company c){
		name = c.getName();
		email = c.getEmail();
		ageMinimum = c.getAgeMinimum();
		//location = c.getLocation();
		experienceReq = c.getExperienceReq();
		companyType = c.getCompanyType();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAgeMinimum() {
		return ageMinimum;
	}

	public void setAgeMinimum(String ageMinimum) {
		this.ageMinimum = ageMinimum;
	}

	public Location getLocation() {
		return location; }

	public String getTown(){
		return town;
	}

	public String getState(){
		return state;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getExperienceReq() {
		return experienceReq;
	}

	public void setExperienceReq(String experienceReq) {
		this.experienceReq = experienceReq;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
}
