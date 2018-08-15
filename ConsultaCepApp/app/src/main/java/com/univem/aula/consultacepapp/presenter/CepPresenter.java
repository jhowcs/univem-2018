package com.univem.aula.consultacepapp.presenter;

import com.univem.aula.consultacepapp.CepRepository;
import com.univem.aula.consultacepapp.model.Cep;
import com.univem.aula.consultacepapp.view.QueryCepView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CepPresenter {
    private CepRepository cepRepository;
    private QueryCepView view;

    public CepPresenter(CepRepository cepRepository, QueryCepView view) {
        this.cepRepository = cepRepository;
        this.view = view;
    }

    public void callCep(String cep) {
        cepRepository
                .getCepService()
                .fetchCepData(cep)
                .enqueue(new Callback<Cep>() {
                    @Override
                    public void onResponse(Call<Cep> call, Response<Cep> response) {

                        if (response.code() == 200) {
                            Cep cepResult = response.body();
                            view.onSuccess(cepResult);
                        } else {
                            view.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Cep> call, Throwable t) {
                        view.onError();
                    }
                });
    }
}
