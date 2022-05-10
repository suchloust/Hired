package com.example.hired;

public class Company {

	private String name;
	private String email;
	private String ageMinimum;
	private Location location;
	private String experienceReq;
	private String companyType;
	private String url;
	private String certification;
	
	public Company() {
		name = "No name provided";
		email = "No email provided";
		ageMinimum = "16";
		location = new Location();
		experienceReq = "No prior experience";
		companyType = "No occupation type provided";
		url = "https://www.youtube.com/";
		certification = "No certifications required";
	}
	
	public Company(String nameIn, String emailIn,  String companyAgeMin, String companyStreet,String compCity,String compState, String compZipCode, String companyExperienceReq, String companyOccupationType, String urlIn, String certification) {
		name = nameIn;
		email = emailIn;
		ageMinimum = companyAgeMin;
		location = new Location(companyStreet, compCity, compState, compZipCode);
		experienceReq = companyExperienceReq;
		companyType = companyOccupationType;
		url = urlIn;
		this.certification = certification;
	}

	public Company(Company c){
		name = c.getName();
		email = c.getEmail();
		ageMinimum = c.getAgeMinimum();
		location = c.getLocation();
		experienceReq = c.getExperienceReq();
		companyType = c.getCompanyType();
		url = c.getUrl();
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
		return location;
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

	public String getUrl() { return url; }

	public void setUrl(String url) { this.url = url;}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}
}
