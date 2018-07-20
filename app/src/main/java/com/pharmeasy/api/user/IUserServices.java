package com.pharmeasy.api.user;

import com.pharmeasy.model.UserEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Rajeev Ranjan on 20-Jul-18.
 */

public interface IUserServices {

    @GET("/api/users?")
    Call<UserEntity> getUser(@Query("page") int page);
}
