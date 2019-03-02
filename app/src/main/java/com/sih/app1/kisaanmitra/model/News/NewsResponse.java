package com.sih.app1.kisaanmitra.model.News;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sih.app1.kisaanmitra.model.GenericResponse;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsResponse extends GenericResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;

    @SerializedName("articles")
    @Expose
    private List<ArticlesResponse> articlesResponse;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticlesResponse> getArticlesResponse() {
        return articlesResponse;
    }

    public void setArticlesResponse(List<ArticlesResponse> articlesResponse) {
        this.articlesResponse = articlesResponse;
    }

}
