package com.univem.aula.consultacepapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.univem.aula.consultacepapp.model.Cep;
import com.univem.aula.consultacepapp.presenter.CepPresenter;
import com.univem.aula.consultacepapp.view.QueryCepView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, QueryCepView {

    private EditText edtCep;
    private Button btnBuscar;
    private TextView txtResult;

    private CepPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCep = findViewById(R.id.edtCep);
        btnBuscar = findViewById(R.id.btnBusca);
        txtResult = findViewById(R.id.txtResult);

        btnBuscar.setOnClickListener(this);

        presenter = new CepPresenter(new CepRepository(), this);
    }

    @Override
    public void onClick(View v) {
        String cep = edtCep.getText().toString();
        presenter.callCep(cep);

        edtCep.getText().clear();
    }

    @Override
    public void onSuccess(Cep cep) {
        txtResult.setText(cep.toString());
    }

    @Override
    public void onError() {
        txtResult.setText("generic error");
    }
}
