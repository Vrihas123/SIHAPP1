package com.sih.app1.kisaanmitra.restapi;
import com.sih.app1.kisaanmitra.model.AdvisoryListResponse;
import com.sih.app1.kisaanmitra.model.Authrization.AuthenticationResponse;
import com.sih.app1.kisaanmitra.model.Authrization.GenericResponse;
import com.sih.app1.kisaanmitra.model.Authrization.LoginRequest;
import com.sih.app1.kisaanmitra.model.Authrization.RegisterRequest;
import com.sih.app1.kisaanmitra.model.CropsWrapper;
import com.sih.app1.kisaanmitra.model.ProductListResponse;
import com.sih.app1.kisaanmitra.model.News.NewsResponse;
import com.sih.app1.kisaanmitra.model.ProductPostData;
import com.sih.app1.kisaanmitra.model.RentResponse;
import com.sih.app1.kisaanmitra.utils.AppConstants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {


    @GET(AppConstants.REQUEST_ADVISORY)
    Call<AdvisoryListResponse> getAdvisoryList();

    @GET(AppConstants.REQUEST_PRODUCTS)
    Call<ProductListResponse> getProductList();

    @GET(AppConstants.NEWS_URL)
    Call<NewsResponse> getNews(@Query("query") String query);
    @POST(AppConstants.SIGN_UP_URL)
    Call<GenericResponse> requestRegister(@Body RegisterRequest registerRequest);

    @GET(AppConstants.ALL_CROPS_URL)
    Call<CropsWrapper> getAllCrops();

    @GET(AppConstants.DELETE_CROPS_URL)
    Call<CropsWrapper> deleteCrops(@Query("crop_id") Integer id);

    @GET(AppConstants.ADD_CROPS_URL)
    Call<CropsWrapper> addCrops(@Query("crop_id") Integer id);
    @POST(AppConstants.REQUEST_PRODUCTS_AVAILABILITY)
    Call<GenericResponse> getProductAvailabilityResult(@Body ProductPostData productPostData);


    @GET(AppConstants.REQUEST__PREVIOUS_RENT)
    Call<RentResponse> getPreviousRentLists();

//
    @POST(AppConstants.SIGN_IN_URL)
    Call<AuthenticationResponse> requestLogin(@Body LoginRequest loginRequest);

    @GET(AppConstants.MY_CROPS_URL)
    Call<CropsWrapper> getMyCrops();
//
}
