package com.kurantsou.balinasofttestproject.api;

import com.kurantsou.balinasofttestproject.model.LoginAnswer;
import com.kurantsou.balinasofttestproject.model.UserCredentials;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by artem on 07.08.2017.
 */

public interface SwaggerUiApi {

    @POST("/api/account/signin")
    Observable<LoginAnswer> login(@Body UserCredentials userCredentials);

    @POST("/api/account/signup")
    Observable<LoginAnswer> register(@Body UserCredentials userCredentials);
}
