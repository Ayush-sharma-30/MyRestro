package com.example.minerest;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderDTO {
    @SerializedName("price")
    int price;

    @SerializedName("foodItem")
    ArrayList<FooditemDTO> fooditem;

    public CreateOrderDTO(int price, ArrayList<FooditemDTO> fooditem) {
        this.price = price;
        this.fooditem = fooditem;
    }

    public CreateOrderDTO() {
    }

    public List<FooditemDTO> getFooditem() {
        return fooditem;
    }

    public void setFooditem(ArrayList<FooditemDTO> fooditem) {
        this.fooditem = fooditem;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
