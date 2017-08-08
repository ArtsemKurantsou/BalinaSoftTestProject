package com.kurantsou.balinasofttestproject.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by artem on 07.08.2017.
 */

public class Api {

    private static SwaggerUiApi api;

    public static SwaggerUiApi getApi() {
        if (api == null)
            initApi();
        return api;
    }

    private static void initApi() {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory
                .createWithScheduler(Schedulers.io());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtils.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .client(createLogClient())
                .build();


        api = retrofit.create(SwaggerUiApi.class);
    }

    private static OkHttpClient createLogClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);
        return clientBuilder.build();
    }

}
