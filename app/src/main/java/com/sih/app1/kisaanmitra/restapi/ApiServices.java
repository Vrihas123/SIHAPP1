package com.sih.app1.kisaanmitra.restapi;
import com.sih.app1.kisaanmitra.model.AdvisoryListResponse;
import com.sih.app1.kisaanmitra.model.ProductListResponse;
import com.sih.app1.kisaanmitra.model.News.NewsResponse;
import com.sih.app1.kisaanmitra.utils.AppConstants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {


    @GET(AppConstants.REQUEST_ADVISORY)
    Call<AdvisoryListResponse> getAdvisoryList();

    @GET(AppConstants.REQUEST_PRODUCTS)
    Call<ProductListResponse> getProductList();

    @GET(AppConstants.NEWS_URL)
    Call<NewsResponse> getNews(@Query("query") String query);
//    @POST(AppConstants.SIGN_UP_URL)
//    Call<AuthenticationResponse> sendRegisterDetails(@Body UserDetails userDetails);
//
//    @POST(AppConstants.SEND_OTP_URL)
//    Call<SendOtpResponse> sendMobileNo(@Body otpSendNumber otpSendNumber);
//
//    @POST(AppConstants.VERIFY_OTP_URL)
//    Call<VerifyOtp> sendOtpEntered(@Body sendOtp sendOtp);
//
//    @POST(AppConstants.RETRY_OTP_URL)
//    Call<AuthenticationResponse> getAnotherOtp(@Body UserDetails userDetails);
//
//    @POST(AppConstants.FB_SIGN_UP_URL)
//    Call<AuthenticationResponse> sendFacebookRegistrationDetails(@Body FacebookSignInUserDetails details);
//
//    @GET(AppConstants.ABOUT_US_URL)
//    Call<AboutUsResponse> getAboutUsDetails();
//
//    @GET(AppConstants.SPEAKER_URL)
//    Call<SpeakerResponse> getSpeakerDetails();
//
//    @POST(AppConstants.MESSAGE_URL)
//    Call<GenericResponse> sendMessage(@Body MessageDetails details);
//
//    @GET(AppConstants.BQUIZ_STATUS)
//    Call<BQuizStatusResponse> getBquizStatus();
//
//    @GET(AppConstants.BQUIZ_LEADERBOARD)
//    Call<BQuizLeaderboardResponse> getBquizLeaderboard(@Query("questionsetId") int questionsetId);
//
//    @GET(AppConstants.EVENTS_URL)
//    Call<EventsResponse> getEventsResponse();
//
//    @POST(AppConstants.SIGN_IN_URL)
//    Call<LoginResponse> sendLoginDetails(@Body LoginDetails loginDetails);
//
//    @GET(AppConstants.SPONSOR_URL)
//    Call<SponsorsResponse> getSponsorsResponce();
//
//    @GET(AppConstants.BQUIZ_QUESTION)
//    Call<BQuizQuestionResponse> getQuestion(@Query("retryQuestion") Boolean retryQuestion);
//
//    @GET(AppConstants.SPLASHSCREEN_URL)
//    Call<SplashScreenResponse> getAppUpdate();
//
//    @POST(AppConstants.BQUIZ_SUBMIT_ANSWER)
//    Call<GenericResponse> submitAnswer(@Body Answer answer);
//
//    @GET(AppConstants.ESUMMIT_REGISTRATION)
//    Call<ESummitRegistrationResponse> getRegistrationLink();
}
