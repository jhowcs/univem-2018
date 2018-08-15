package com.univem.aula.consultacepapp.view;

import com.univem.aula.consultacepapp.model.Cep;

public interface QueryCepView {

    void onSuccess(Cep cep);

    void onError();
}
