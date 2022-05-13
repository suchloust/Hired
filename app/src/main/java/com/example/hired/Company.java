package com.example.hired;
/**
 * A class that stores the information of a Company object.
 */
public class Company {

	private String name;
	private String email;
	private String ageMinimum;
	private Location location;
	private String experienceReq;
	private String companyType;
	private String url;
	private String certification;

	/**
	 * Default constructor for the Company class.
	 * Defaults the minimum working age to be 16 years old.
	 * Defaults the experience requirement to "No prior experience"
	 * Defaults the certification requirement to "No certification required"
	 * Default url for advertisement goes to YouTube homepage.
	 */
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

	/**
	 * Parameterized constructor for the Company class.
	 * @param name, name of company
	 * @param email, primary email of the company
	 * @param companyAgeMin, minimum working age of the Company
	 * @param companyStreet, Street address of the company. Used in the Location class constructor.
	 * @param compCity, City or town of the company. Used in the Location class constructor.
	 * @param compState, State or territory of the company. Used in the Location class constructor.
	 * @param compZipCode, Zip code of the company address. Used in the Location class constructor.
	 * @param companyExperienceReq, Required company experience
	 * @param companyOccupationType
	 * @param urlIn, a link (could be YouTube) to a video advertisement of the Company establishment for hiring.
	 * @param certification, required prior certification for the Company.
	 */
	public Company(String name, String email,  String companyAgeMin, String companyStreet,String compCity,String compState, String compZipCode, String companyExperienceReq, String companyOccupationType, String urlIn, String certification) {
		this.name = name;
		this.email = email;
		ageMinimum = companyAgeMin;
		location = new Location(companyStreet, compCity, compState, compZipCode);
		experienceReq = companyExperienceReq;
		companyType = companyOccupationType;
		url = urlIn;
		this.certification = certification;
	}

	/**
	 * Another constructor for a Company object using another already constructor video address,s
	 * @param c
	 */
	public Company(Company c){
		name = c.getName();
		email = c.getEmail();
		ageMinimum = c.getAgeMinimum();
		location = c.getLocation();
		experienceReq = c.getExperienceReq();
		companyType = c.getCompanyType();
		url = c.getUrl();
	}

	/**
	 * Returns the name of the company
	 * @return name, name of the Company object.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the company to the given value,
	 * @param name, name of the Company object.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the email of the company
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the Company to a given email.
	 * @param email, email of the Company.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the minimum working age of the Company dlass
	 * @return
	 */
	public String getAgeMinimum() {
		return ageMinimum;
	}

	/**
	 * Sets the working age minimum for the company to a given minimum age.
	 * @param ageMinimum, minimum working age for the Company
	 */
	public void setAgeMinimum(String ageMinimum) {
		this.ageMinimum = ageMinimum;
	}

	/**
	 * Returns the Location of the Company based on their address.
	 * @return the Location of the Coany.
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets the Location of the Company
	 * @param location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Returns the experience requirement of the Company
	 * @return
	 */
	public String getExperienceReq() {
		return experienceReq;
	}

	/**
	 * Sets the experience requirement of the company to a given experience requirement
	 * @param experienceReq, the experience requirement of the company;
	 */
	public void setExperienceReq(String experienceReq) {
		this.experienceReq = experienceReq;
	}

	/**
	 * Returns the companyOce
	 * @return
	 */
	public String getCompanyType() {
		return companyType;
	}

	/**
	 * Set the occupation field of the Company object.
	 * @param companyType
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	/**
	 * Returns the advertisement URL of the Company object
	 * This is usually a link to a YouTube advertisement, but any valid web link will render on the page.
	 * @return url, the advertisement url of the Company
	 */
	public String getUrl() { return url; }

	/**
	 * Set the advertisement URL of the Company object.
	 * This is usually a link to a YouTube advertisement, but any valid web link will render on the page.
	 * @param url, the advertisement url of the Company
	 */
	public void setUrl(String url) { this.url = url;}

	/**
	 * Returns the certification requirements of the Company.
	 * @return certification, the certification requirements of the Company.
	 * In future, we will update this structure to take in multiple requirements.
	 */
	public String getCertification() {
		return certification;
	}

	/**
	 * Set the certification requirements of the Company.
	 * @param certification, the certification requirements of the Company.
	 * In future, we will update this structure to take in multiple requirements. 	 */
	public void setCertification(String certification) {
		this.certification = certification;
	}
}