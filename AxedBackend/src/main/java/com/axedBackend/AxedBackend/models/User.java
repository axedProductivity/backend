package com.axedBackend.AxedBackend.models;

public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;
    private String gender;
    private String profilePicture;
    private String id;

    public User() {
    }

    public User(String email, String firstName, String lastName, String phoneNumber, int age, String gender,
            String profilePicture, String id) {
        this.email = email;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
        this.profilePicture = profilePicture;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
