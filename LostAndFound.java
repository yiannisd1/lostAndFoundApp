package com.example.lostandfoundappfinal.model;

public class LostAndFound {

    private String typeOfAdvert;
    private String name;
    private String phone;
    private String description;
    private String date;
    private String location;

    public LostAndFound(String typeOfAdvert, String textName, String textPhone, String textDescription, String textDate, String textLocation) {
        this.typeOfAdvert = String.valueOf(typeOfAdvert);
        this.name = String.valueOf(textName);
        this.phone = String.valueOf(textPhone);
        this.description = String.valueOf(textDescription);
        this.date = String.valueOf(textDate);
        this.location = String.valueOf(textLocation);
    }

    public LostAndFound() {

    }

    public String getTypeOfAdvert() {
        return  typeOfAdvert;
    }

    public void setTypeOfAdvert(String lost) {
        this.typeOfAdvert = lost;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}

