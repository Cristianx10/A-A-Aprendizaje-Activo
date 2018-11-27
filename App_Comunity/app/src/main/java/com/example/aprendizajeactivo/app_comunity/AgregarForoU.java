package com.example.aprendizajeactivo.app_comunity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;
import java.util.UUID;

import FirebaseConexion.FirebaseAU;
import FirebaseConexion.Firebase_value;
import HomePrincipal.HomePage;
import ObjetosList.OForo;
import ObjetosList.OUsuario;

public class AgregarForoU extends AppCompatActivity implements View.OnClickListener{


    FirebaseAU au;

    private EditText et_tituloActividad;
    private EditText et_descripcionActividad;
    private EditText et_fecha_entrega;
    private Button btn_actividad_cancelar;
    private Button btn_actividad_finish;

    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_foro_u);
        au.getIntance();


        et_tituloActividad = findViewById(R.id.et_tituloActividad);
        et_descripcionActividad = findViewById(R.id.et_descripcionActividad);
        et_fecha_entrega = findViewById(R.id.et_fecha_entrega);
        btn_actividad_cancelar = findViewById(R.id.btn_actividad_cancelar);
        btn_actividad_finish = findViewById(R.id.btn_actividad_finish);

        btn_actividad_cancelar.setOnClickListener(this);
        btn_actividad_finish.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_actividad_cancelar:

                onBackPressed();


                break;

            case R.id.btn_actividad_finish:

                String titulo = et_tituloActividad.getText().toString();
                String descripcion = et_descripcionActividad.getText().toString();
                String fecha =  et_fecha_entrega.getText().toString();

                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                String id = UUID.randomUUID().toString();

                OForo  f = new OForo(titulo, descripcion, HomePage.name, day + "/" + month + "/" + year, id);
                DatabaseReference ref = au.getReferencia().child(Firebase_value.FORO).child(Firebase_value.FOROS);



                ref.child(id).setValue(f);

                Toast.makeText(this, "Foro Agregado", Toast.LENGTH_SHORT).show();

                onBackPressed();

                break;


        }
    }
}
