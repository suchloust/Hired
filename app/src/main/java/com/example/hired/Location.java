package com.example.hired;

/**
 * A class that models the Location of a User or Company.
 */
public class Location {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    /**
     * Default constructor for the Location class.
     */
    public Location(){
        streetAddress = "No address provided yet.";
        city = "No city provided";
        state = "No state provided";
        zipCode = "No zip code provided";
    }

    /**
     * Parameterized constructor that takes in the street address, city, state, and zipcode of a location.
     * @param street, street address of location.
     * @param cityIn, city of the location.
     * @param stateIn, state of the location.
     * @param zipCodeIn, zipCode of the location.
     */
    public Location(String street, String cityIn, String stateIn, String zipCodeIn){
        streetAddress = street;
        city = cityIn;
        state = stateIn;
        zipCode = zipCodeIn;
    }

    /**
     * Sets the city of the Location.
     * @param city, city of the Location.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the state of the Location.
     * @param state, state of the Location.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Sets the zipCode of the Location.
     * @param zipCode, zipCode of the Location.
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Sets the street address of the Location.
     * @param streetAddress, street address of the location.
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Returns the streetAddress of the Location object.
     * @return streetAddress
     */
    public String getStreetAddress(){
        return streetAddress;
    }

    /**
     * Returns the city of the Location object.
     * @return city
     */
    public String getCity(){
        return city;
    }

    /**
     * Returns the state of the Location object.
     * @return state
     */
    public String getState(){
        return state;
    }

    /**
     * Returns the zip code of the Location object.
     * @return zipCode
     */
    public String getZipCode(){
        return zipCode;
    }

    /**
     * toString method for the Location class.
     * @return the streetAddress, city, state zipCode
     */
    public String toString(){
        return streetAddress + ", " + city + ", " + state + " " + zipCode;
    }
}
