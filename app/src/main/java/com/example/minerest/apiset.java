package com.example.minerest;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface apiset {

    @FormUrlEncoded
    @POST("customer/new/")
    Call<signup_response_model> getregister(
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("authentication/login/")
    Call<login_response_model> getlogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("foodItem")
    Call<List<dashboard_response_model>> getdata(@Query("token") String token);
}
