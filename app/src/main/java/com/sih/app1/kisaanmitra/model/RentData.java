package com.sih.app1.kisaanmitra.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RentData implements Serializable {

    @SerializedName("rent_id")
    private int rent_id;

    @SerializedName("product_name")
    private String product_name;

    @SerializedName("price")
    private float price;

    @SerializedName("quantity")
    private float quantity;

    @SerializedName("status")
    private boolean status;

    @SerializedName("duration_start")
    private String duration_start;

    @SerializedName("duration_end")
    private String duration_end;

    public int getRent_id() {
        return rent_id;
    }

    public void setRent_id(int rent_id) {
        this.rent_id = rent_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDuration_start() {
        return duration_start;
    }

    public void setDuration_start(String duration_start) {
        this.duration_start = duration_start;
    }

    public String getDuration_end() {
        return duration_end;
    }

    public void setDuration_end(String duration_end) {
        this.duration_end = duration_end;
    }
}
