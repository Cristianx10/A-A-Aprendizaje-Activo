package com.example.aprendizajeactivo.aa_comunity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import FirebaseConexion.FirebaseConexion;
import FirebaseConexion.Interfaz.ActionActivity;

public class Registro extends AppCompatActivity {

    FirebaseConexion fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        fc.getIntance();
/*
        String name = "";
        String password = "";

        fc.registroActivity(name, password, new FirebaseConexion.ValidarAutentication() {
            @Override
            public void taskIsSuccessful(@NonNull Task<AuthResult> task) {

            }

            @Override
            public void errorTaskException(@NonNull Task<AuthResult> task) {

            }
        });

*/

    }
}
