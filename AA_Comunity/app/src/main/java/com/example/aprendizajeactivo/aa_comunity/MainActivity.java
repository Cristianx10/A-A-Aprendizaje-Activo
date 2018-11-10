package com.example.aprendizajeactivo.aa_comunity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import FirebaseConexion.FirebaseConexion;
import FirebaseConexion.Interfaz.ActionActivity;

public class MainActivity extends AppCompatActivity {

    private FirebaseConexion fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fc.getIntance();



        ActionActivity.goToActivity(MainActivity.this, Registro.class);
    }
}
