package com.sih.app1.kisaanmitra.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AdvisoryListResponse extends GenericResponse implements Serializable {

    @SerializedName("advisory_list")
    @Expose
    private List<AdvisoryData> advisoryDataList;

    public List<AdvisoryData> getAdvisoryDataList() {
        return advisoryDataList;
    }

    public void setAdvisoryDataList(List<AdvisoryData> advisoryDataList) {
        this.advisoryDataList = advisoryDataList;
    }
}
