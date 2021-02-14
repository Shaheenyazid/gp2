package com.example.group0;

public class UserProfile {
    public String userAge;
    public String userEmail;
    public String userName;
    public String userNo;

    public UserProfile(){ //constructor tnpa parameter

    }
    public UserProfile(String userAge, String userEmail, String userName, String userNo) {
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userNo = userNo;
    }

    public String getUserAge() {
        return userAge;
    } //getter/accessor

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    } //setter/mutator

    public String getUserEmail() {
        return userEmail;
    } //getter/accessor

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    } //setter/mutator

    public String getUserName() {
        return userName;
    } //getter/accessor

    public void setUserName(String userName) {
        this.userName = userName;
    } //setter/mutator

    public String getUserNo() {
        return userNo;
    } //getter/accessor

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    } //setter/mutator
}


