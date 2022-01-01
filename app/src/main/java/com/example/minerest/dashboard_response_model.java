package com.example.minerest;

public class dashboard_response_model {
    String name;
    String type;
    String price;
    String descrition;
    String url;
    String rating;

    public dashboard_response_model(String name, String type, String price, String descrition, String url, String rating) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.descrition = descrition;
        this.url = url;
        this.rating=rating;
    }

    public dashboard_response_model() {
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getRating() { return rating; }

    public void setRating(String rating) { this.rating = rating; }
}
