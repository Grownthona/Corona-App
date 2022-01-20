package com.example.sd;

public class Member {
     String Email;
     String Phone;
     String FullName;
     String Address;
     String url;
     String catagory;

    public Member(){

    }


    public Member(String email, String phone, String fullName, String address, String url, String catagory) {
        Email = email;
        Phone = phone;
        FullName = fullName;
        Address = address;
        this.url = url;
        this.catagory = catagory;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

}
