package com.sih.app1.kisaanmitra.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductData implements Serializable {

    @SerializedName("product_id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("product_type")
    private String product_type;

    @SerializedName("sell_price")
    private float sell_price;

    @SerializedName("rent_price")
    private float rent_price;

    @SerializedName("quantity")
    private float quantity;

    @SerializedName("product_image")
    private String product_image;

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    @SerializedName("period")
    private String period;

    @SerializedName("quantity_type")
    private String quantity_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public float getSell_price() {
        return sell_price;
    }

    public void setSell_price(float sell_price) {
        this.sell_price = sell_price;
    }

    public float getRent_price() {
        return rent_price;
    }

    public void setRent_price(float rent_price) {
        this.rent_price = rent_price;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getQuantity_type() {
        return quantity_type;
    }

    public void setQuantity_type(String quantity_type) {
        this.quantity_type = quantity_type;
    }
}
