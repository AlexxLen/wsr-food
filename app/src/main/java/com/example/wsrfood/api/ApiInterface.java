package com.example.wsrfood.api;

import com.example.wsrfood.api.dish.DishResponse;
import com.example.wsrfood.api.user.SignUpUserBody;
import com.example.wsrfood.api.user.UserBody;
import com.example.wsrfood.api.user.UserResponse;
import com.example.wsrfood.api.version.VersionResponse;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @POST("auth/login")
    Single<UserResponse> logIn(@Body UserBody user);

    @POST("auth/register")
    Completable signUp(@Body SignUpUserBody user);


    @GET("dishes")
    Single<DishResponse> getDishes();

    @GET("dishes/{version}")
    Single<DishResponse> getDishesByVersion(@Path("version") String version);

    @GET("versions")
    Single<VersionResponse> loadVersions();
}
