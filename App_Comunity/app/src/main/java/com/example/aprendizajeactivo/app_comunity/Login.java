package com.example.aprendizajeactivo.app_comunity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import FirebaseConexion.FirebaseConexion;
import Interfaz.ActionActivity;

public class Login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);







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
