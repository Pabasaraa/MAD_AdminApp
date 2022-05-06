package com.example.kuppiya_admin;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class jobsHelperClass implements Serializable {
    @Exclude
    private String key;

    private String title, salary, companyName, location, contactNo, email;

    public jobsHelperClass(String title, String salary, String companyName, String location, String contactNo, String email) {
        this.title = title;
        this.salary = salary;
        this.companyName = companyName;
        this.location = location;
        this.contactNo = contactNo;
        this.email = email;
    }

    public jobsHelperClass() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }
}
