package com.example.hired;

public class User {

	private int age;
	//Right now I have the location as a String, but I was thinking of making that its own class? Would that make it easier to
	//set an actual location? Maybe the class object could hold coordinates of the specified location?
	private String location;
	private String experience;
	private String occupationType;
	
	/**
	 * Default constructor. Assumes an age of 14 and no prior experience.
	 */
	public User() {
		age = 14;
		location = "No location provided.";
		experience = "No prior experience";
		occupationType = "No field of interest specificed";
	}
	
	/**
	 * Constructs a user with the specified age, location, experience level, and occupation field preference. 
	 * @param userAge
	 * @param userLocation
	 * @param userExperience
	 * @param userOccupation
	 */
	public User(int userAge, String userLocation, String userExperience, String userOccupation) {
		age = userAge;
		location = userLocation;
		experience = userExperience;
		occupationType = userOccupation;
	}
	
	/**
	 * Constructs a user with the location, experience level, and occupation field preference. Assumes age of 16 for the user.
	 * @param userLocation
	 * @param userExperience
	 * @param userOccupation
	 */
	public User(String userLocation, String userExperience, String userOccupation) {
		age = 16;
		location = userLocation;
		experience = userExperience;
		occupationType = userOccupation;
	}
	
	/**
	 * Constructs a user with the specified age, experience level, and occupation field preference. 
	 * @param userAge
	 * @param userExperience
	 * @param userOccupation
	 */
	public User(int userAge, String userExperience, String userOccupation) {
		age = userAge;
		location = "No location provided.";
		experience = userExperience;
		occupationType = userOccupation;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getExperienceLevel() {
		return experience;
	}
	
	public String getOccupationPreference() {
		return occupationType;
	}
	
	//a method to set an actual location from the user provided String input? 
	public void setLocation() {
		
	}
	
	//a method to match the user with companies who match with their filters. 
	//Would accept an input of a Company class?
	//Would return void or with instance of Company class?
	//How many matches do we consider a "match" or compatible company? Should we return a percent match?
	public int matchWithCompany(Company company) {
		int compatibilityScore = 0;
		/*if (age.equals(company.getAgeReq())) {
			compatibilityScore++;
		}*/
		if (location.equals(company.getLocation())) {
			//Did this now with a String match, but will refine in future.
			compatibilityScore++;
		}
		if (experience.equals(company.getExperienceReq())) {
			//need to implement some logic for this-- should we apply a number system to the ExperienceReq to make 
			//Comparisons easier? That way if you are over-qualified, you still match?
			compatibilityScore++;
		}
		if (occupationType.equals(company.getCompanyType())) {
			compatibilityScore++;
		}
		return (compatibilityScore/5)*100;
	}
	
	
}
