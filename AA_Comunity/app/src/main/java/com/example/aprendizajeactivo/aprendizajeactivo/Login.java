package com.example.aprendizajeactivo.aprendizajeactivo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import FirebaseConexion.FirebaseConexion;

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
