package com.univem.aula.consultacepapp.api;

import com.univem.aula.consultacepapp.model.Cep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CepService {

    @GET("ws/{cep}/json")
    Call<Cep> fetchCepData(@Path("cep") String cep);
}
