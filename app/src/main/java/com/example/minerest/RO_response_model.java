package com.example.minerest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RO_response_model {

//    @SerializedName("orders")
//    @Expose
//    private List<OrderField> orderFields = null;

    @SerializedName("orders")
    @Expose
    private OrderField[] orderFields;

//    public List<OrderField> getOrderFields() { return orderFields; }
//
//    public void setOrderFields(List<OrderField> orderFields) { this.orderFields = orderFields; }


    public OrderField[] getOrderFields() {
        return orderFields;
    }

    public void setOrderFields(OrderField[] orderFields) {
        this.orderFields = orderFields;
    }
}
