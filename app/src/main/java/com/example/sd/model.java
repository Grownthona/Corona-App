package com.example.sd;

public class model {
    String name,price,catagory,description,id,url,phone,date;
    model()
    {

    }

    public model(String name, String price, String catagory, String description, String id, String url, String phone, String date) {
        this.name = name;
        this.price = price;
        this.catagory = catagory;
        this.description = description;
        this.id = id;
        this.url = url;
        this.phone = phone;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
