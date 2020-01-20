package com.example.weathercity.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    private RetrofitClient() {

    }

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            retrofit = new Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }
}
