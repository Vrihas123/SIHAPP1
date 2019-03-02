package com.sih.app1.kisaanmitra.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductListResponse extends GenericResponse implements Serializable {

    @SerializedName("product_list")
    private List<ProductData> productDataList;

    public List<ProductData> getProductDataList() {
        return productDataList;
    }

    public void setProductDataList(List<ProductData> productDataList) {
        this.productDataList = productDataList;
    }
}
