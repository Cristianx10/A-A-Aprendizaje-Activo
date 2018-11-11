package com.example.aprendizajeactivo.aprendizajeactivo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import FirebaseConexion.FirebaseConexion;
import Interfaz.ActionActivity;

public class Registro extends AppCompatActivity {

    FirebaseConexion fc;

    private EditText et_registro_name;
    private EditText et_registro_email;
    private EditText et_registro_password;
    private EditText et_registro_password_confir;
    private Button btn_registro_registrar;

    private LinearLayout ll_registro_principal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        fc.getIntance();

        ll_registro_principal = findViewById(R.id.ll_registro_principal);
        et_registro_name = findViewById(R.id.et_registro_name);
        et_registro_email = findViewById(R.id.et_registro_email);
        et_registro_password = findViewById(R.id.et_registro_password);
        et_registro_password_confir = findViewById(R.id.et_registro_password_confir);
        btn_registro_registrar = findViewById(R.id.btn_registro_registrar);


        ActionActivity.ocularKeyTeclado(this, et_registro_name);
        ActionActivity.ocularKeyTeclado(this, et_registro_email);
        ActionActivity.ocularKeyTeclado(this, et_registro_password);
        ActionActivity.ocularKeyTeclado(this, et_registro_password_confir);

        ActionActivity.ocularClickTeclado(this, ll_registro_principal);




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
