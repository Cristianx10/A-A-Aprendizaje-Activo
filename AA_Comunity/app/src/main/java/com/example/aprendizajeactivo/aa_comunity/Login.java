package com.example.aprendizajeactivo.aa_comunity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import FirebaseConexion.FirebaseConexion;
import FirebaseConexion.Interfaz.ActionActivity;
import FirebaseConexion.Usuario;

public class Login extends AppCompatActivity {

    private FirebaseConexion fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fc.getIntance();

        String name = "";
        String correo = "";
/*

        fc.loginActivity(name, correo, new FirebaseConexion.ValidarAutentication() {
            @Override
            public void taskIsSuccessful(@NonNull Task<AuthResult> task) {
                String uid = fc.getUser().getUid();

                //Completar los requerimientos del usuario
                Usuario user = new Usuario();

                fc.getRef().child(fc.usuarios).setValue(user);


            }

            @Override
            public void errorTaskException(@NonNull Task<AuthResult> task) {

            }
        });*/








    }
}
