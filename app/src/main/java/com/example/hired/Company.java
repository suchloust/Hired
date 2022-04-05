package com.example.hired;

public class Company {
	
	private int ageMinimum;
	private String location;
	private String experienceReq;
	private String companyType;
	
	public Company() {
		ageMinimum = 16;
		location = "No location provided";
		experienceReq = "No prior experience";
		companyType = "No occupation type provided";
	}
	
	public Company(int companyAgeMin, String companyLocation, String companyExperienceReq, String companyOccupationType) {
		ageMinimum = companyAgeMin;
		location = companyLocation;
		experienceReq = companyExperienceReq;
		companyType = companyOccupationType;
	}
	
	public int getAgeReq() {

		return ageMinimum;
	}
	
	public String getLocation() {

		return location;
	}
	
	public String getExperienceReq() {

		return experienceReq;
	}
	
	public String getCompanyType() {

		return companyType;
	}

}
