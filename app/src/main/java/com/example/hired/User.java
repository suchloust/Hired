package com.example.hired;

/**
 * A class that models a User and stores their information.
 */
public class User {

	private int age;
	private String location;
	private String experience;
	private String occupationType;
	private String userState;
	private String userTown;
	private String certification;
	
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
		for (int i = 0; i<userLocation.length(); i++){
			if(userLocation.charAt(i)==','){
				userLocation = userLocation.substring(i+2);
				break;
			}
		}
		for (int i = 0; i<userLocation.length(); i++){
			if(userLocation.charAt(i)==','){
				userState = userLocation.substring(i+2);
				userTown = userLocation.substring(0,i);
				break;
			}
		}
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

	/**
	 * Returns the age of the User.
	 * @return age of the user.
	 */
	public int getAge() { return age;	}

	/**
	 * Returns the location of the user
	 * @return location, location of the user
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Returns the experience levels of the user
	 * @return, experience, experience levels of the user.
	 */
	public String getExperienceLevel() {
		return experience;
	}

	/**
	 * Returns the occupation type of the user
	 * @return occupationType, occupation preference of the user.
	 */
	public String getOccupationType() {
		return occupationType;
	}

	/**
	 * Sets the age of the user
	 * @param age, age of the user.
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Sets the location of the user
	 * @param location, location of the user
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Returns the experience of the user
	 * @return experience
	 */
	public String getExperience() {
		return experience;
	}

	/**
	 * Sets the experience of the user.
	 * @param experience, experience of the user.
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}

	/**
	 * Sets the occupation preference of the user
	 * @param occupationType
	 */
	public void setOccupationType(String occupationType) {
		this.occupationType = occupationType;
	}

	/**
	 * Returns the state of the user
	 * @return userState
	 */
	public String getUserState() {
		return userState;
	}

	/**
	 * Sets the state of the user
	 * @param userState
	 */
	public void setUserState(String userState) {
		this.userState = userState;
	}

	/**
	 * Returns the town of the user
	 * @return useTown
	 */
	public String getUserTown() {
		return userTown;
	}

	/**
	 * Sets the town of the user
	 * @param userTown
	 */
	public void setUserTown(String userTown) {
		this.userTown = userTown;
	}

	/**
	 * Returns the certifications of the user
	 * @return certification
	 */
	public String getCertification() {
		return certification;
	}

	/**
	 * Sets the certifications of the user.
	 * @param certification
	 */
	public void setCertification(String certification) {
		this.certification = certification;
	}

	/**
	 * A method that calculates a compatibility score for the user and a parameter Company object.
	 * Prioritized (in order) occupation type, location, experience, and age
	 * For occupation type, if the type of the user matches the type of the Company (from the given options), 3 points are given.
	 * For location, checks if the user location is equal to the state and town of the Company (2x points are given if also the town is equal).
	 * For experience, it calls the getExperienceScore method for the user and company to compare them. If the user experience is greater than the required company experience requirement, more points are given.
	 * For age, checks if the user age is greater than the company minimum working age.
	 * @param company
	 * @return
	 */
	public double matchWithCompany(Company company) {
		int compatibilityScore = 0;
		if (userState.equals(company.getLocation().getState())){
			compatibilityScore++;
			if(userTown.equals(company.getLocation().getCity())){
				compatibilityScore+=2;
			}
		}

		int expScore = getExperienceScore(experience);
		int companyExpScore = getExperienceScore(company.getExperienceReq());
		if (expScore==companyExpScore){
			compatibilityScore ++;
		}
		else if (expScore>companyExpScore){
			int diff = expScore-companyExpScore + 1;
			compatibilityScore += diff;
		}
		if (age >= Integer.parseInt(company.getAgeMinimum())) {
			compatibilityScore++;
		}
		if (occupationType.equals(company.getCompanyType())) {
			compatibilityScore+=3;
		}
		return compatibilityScore*10;
	}

	/**
	 * Calculates the experience score for a user or company object. Assigns a numerical values to each of the experience levels.
	 * @param experience
	 * @return
	 */
	private int getExperienceScore(String experience){
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
}
