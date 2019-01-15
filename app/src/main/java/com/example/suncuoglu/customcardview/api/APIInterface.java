package com.example.suncuoglu.customcardview.api;

import com.example.suncuoglu.customcardview.model.User;
import retrofit2.Call;
import retrofit2.http.GET;


public interface APIInterface {

    @GET("users/1")
    Call<User> getUser();

}
