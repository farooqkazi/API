package com.example.laptop.myfirstapi.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class InteractorService {

    public static ICakesAPI getConnection(){

        Retrofit retrofit = null;
        OkHttpClient okHttpClient = null;

        /**
         * Used to print the log statements of the parsed json data in the logcat
         */

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        /**
         * Add HttpLoginInterecptor to okhttp
         */
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();


        if (retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    /**
                     * used to parse json to pojos
                     */
                    .addConverterFactory(GsonConverterFactory.create())
                    /**
                     * Display data received to RecyclerView
                     */
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    /**
                     * Add Okthhp as a friend
                     */
                    .client(okHttpClient)
                    .build();
        }

        return retrofit.create(ICakesAPI.class);
    }
}
