package com.sih.app1.kisaanmitra.model;

import com.google.gson.annotations.SerializedName;
import com.sih.app1.kisaanmitra.model.Authrization.GenericResponse;

import java.io.Serializable;
import java.util.List;

public class RentResponse extends GenericResponse implements Serializable {

        @SerializedName("rent_list")
        private List<RentData> rentDataList;

    public List<RentData> getRentDataList() {
        return rentDataList;
    }

    public void setRentDataList(List<RentData> rentDataList) {
        this.rentDataList = rentDataList;
    }
}
