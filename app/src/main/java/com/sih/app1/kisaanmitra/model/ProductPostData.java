package com.sih.app1.kisaanmitra.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductPostData implements Serializable {

    @SerializedName("product_id")
    private int product_id;

    @SerializedName("start")
    private String start;

    @SerializedName("end")
    private String end;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
