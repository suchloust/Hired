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

	public int getAge() { return age;	}
	
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
	public double matchWithCompany(Company company) {
		int compatibilityScore = 0;
		if (location.equals(company.getLocation())) {
			//Did this now with a String match, but will refine in future.
			compatibilityScore++;
		}
		int expScore = getExperienceScore(experience);
		int companyExpScore = getExperienceScore(company.getExperienceReq());
		if (expScore==companyExpScore){
			compatibilityScore ++;
		}
		else if (expScore>companyExpScore){
			int diffy = expScore-companyExpScore + 1;
			compatibilityScore += diffy;
		}
		if (age >= Integer.parseInt(company.getAgeMinimum())) {
			compatibilityScore++;
		}
		if (occupationType.equals(company.getCompanyType())) {
			compatibilityScore+=3;
		}
		return (compatibilityScore);
	}
	public int getExperienceScore(String experience){
		int expScore=0;
		if(experience.equals("No prior experience")){
			expScore = 0;
		}
		else if (experience.equals("Have held a non-paying internship/job")){
			expScore = 1;
		}
		else if (experience.equals("Have held a paying job")){
			expScore = 2;
		}
		else if (experience.equals("Have held a paying job within the target industry")){
			expScore = 3;
		}
		return expScore;
	}
	//To do:
	//work on location comparison
	
}
