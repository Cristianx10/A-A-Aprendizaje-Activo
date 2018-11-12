package com.example.aprendizajeactivo.aa_comunity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Interfaz.ActionActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Intructivo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intructivo);

        ActionActivity.interfazTranslucida(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
