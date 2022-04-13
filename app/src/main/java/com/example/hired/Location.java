package com.example.hired;

public class Location {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    public Location(){
        streetAddress = "No address provided yet.";
        city = "No city provided";
        state = "No state provided";
        zipCode = "No zip code provided";
    }
    public Location(String street, String cityIn, String stateIn, String zipCodeIn){
        streetAddress = street;
        city = cityIn;
        state = stateIn;
        zipCode = zipCodeIn;
    }

    public String getStreetAddress(){
        return streetAddress;
    }

    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }

    public String getZipCode(){
        return zipCode;
    }
}
