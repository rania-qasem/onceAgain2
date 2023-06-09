package com.example.onceagain;

public class User {
    private String fullName;
    private String userAddress;
    public String phoneNum;
    public String emailAddress;

    public User() {
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmailAdress() {
        return emailAddress;
    }
}
