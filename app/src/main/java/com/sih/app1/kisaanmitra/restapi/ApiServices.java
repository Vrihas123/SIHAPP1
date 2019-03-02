package com.sih.app1.kisaanmitra.restapi;
import com.sih.app1.kisaanmitra.model.AdvisoryListResponse;
import com.sih.app1.kisaanmitra.model.GenericResponse;
import com.sih.app1.kisaanmitra.model.ProductListResponse;
import com.sih.app1.kisaanmitra.model.News.NewsResponse;
import com.sih.app1.kisaanmitra.model.ProductPostData;
import com.sih.app1.kisaanmitra.utils.AppConstants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {


    @GET(AppConstants.REQUEST_ADVISORY)
    Call<AdvisoryListResponse> getAdvisoryList();

    @GET(AppConstants.REQUEST_PRODUCTS)
    Call<ProductListResponse> getProductList();

    @GET(AppConstants.NEWS_URL)
    Call<NewsResponse> getNews(@Query("query") String query);

    @GET(AppConstants.REQUEST_PRODUCTS_AVAILABILITY)
    Call<GenericResponse> getProductAvailabilityResult(@Body ProductPostData productPostData);
}
