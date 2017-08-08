package com.kurantsou.balinasofttestproject.api;

import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit2.adapter.rxjava.HttpException;

public class ErrorProcessor {

    private static final String TAG = "ErrorProcessor";

    /*@Nullable
    public static TaskResponse extractResponse(HttpException retrofitException) {
        String responseBody = null;
        try {
            responseBody = retrofitException.response().errorBody().string();
            return new Gson().fromJson(responseBody, TaskResponse.class);
        } catch (IOException e) {
            return null;
        }
    }

    @Nullable
    public static String extractErrorMessage(HttpException retrofitException) {
        if (retrofitException.code() == HttpURLConnection.HTTP_NOT_FOUND)
            return "Server not found";
        if (retrofitException.code() == HttpURLConnection.HTTP_INTERNAL_ERROR)
            return "Server error";
        String responseBody = null;
        try {
            responseBody = retrofitException.response().errorBody().string();
            return new Gson().fromJson(responseBody, TaskResponse.class).getMessage();
        } catch (IOException e) {
            return null;
        }
    }*/

    public static boolean isAuthError(Throwable retrofitException) {
        if (retrofitException instanceof HttpException)
            if (((HttpException) retrofitException).code() == HttpURLConnection.HTTP_UNAUTHORIZED)
                return true;
        return false;
    }

    @Nullable
    public static String extractErrorMessage(Throwable retrofitException) {
        try {
            return extractErrorMessage((HttpException) retrofitException);
        } catch (ClassCastException e) {
            return retrofitException.getMessage();
        }
    }
}
