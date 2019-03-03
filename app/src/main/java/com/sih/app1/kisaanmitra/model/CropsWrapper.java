package com.sih.app1.kisaanmitra.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sih.app1.kisaanmitra.model.Authrization.GenericResponse;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CropsWrapper extends GenericResponse implements Serializable {

    @SerializedName("crop_list")
    @Expose
    private List<Crop> crop_list;

    public List<Crop> getCrop_list() {
        return crop_list;
    }

    public void setCrop_list(List<Crop> crop_list) {
        this.crop_list = crop_list;
    }
}
