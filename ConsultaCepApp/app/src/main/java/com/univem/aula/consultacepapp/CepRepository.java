package com.univem.aula.consultacepapp;

import android.support.annotation.VisibleForTesting;

import com.univem.aula.consultacepapp.api.CepService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CepRepository {

    private static final String BASE_URL = "https://viacep.com.br/";

    private static Retrofit retrofit;

    public CepRepository() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    public CepService getCepService() {
        return retrofit.create(CepService.class);
    }

    @VisibleForTesting
    public static void swapRetrofitForTest(Retrofit swapRetrofit) {
        retrofit = swapRetrofit;
    }
}
