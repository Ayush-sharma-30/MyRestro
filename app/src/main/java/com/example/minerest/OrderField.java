package com.example.minerest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderField {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("totalPrice")
    @Expose
    private String totalPrice;
    @SerializedName("foodItem")
    @Expose
    private Fooditems[] fooditems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Fooditems[] getFooditems() {
        return fooditems;
    }

    public void setFooditems(Fooditems[] fooditems) {
        this.fooditems = fooditems;
    }
}
