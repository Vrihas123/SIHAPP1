package com.sih.app1.kisaanmitra.restapi;

import com.sih.app1.kisaanmitra.model.AdvisoryListResponse;
import com.sih.app1.kisaanmitra.model.ProductListResponse;
import com.sih.app1.kisaanmitra.utils.AppConstants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {


    @GET(AppConstants.REQUEST_ADVISORY)
    Call<AdvisoryListResponse> getAdvisoryList();

    @GET(AppConstants.REQUEST_PRODUCTS)
    Call<ProductListResponse> getProductList();
}
