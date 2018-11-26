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

import java.util.UUID;

import FirebaseConexion.FirebaseAU;
import FirebaseConexion.Firebase_value;
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

                final DatabaseReference auto = au.getReferencia().child(Firebase_value.USUARIOS).child(au.getUserUid());



                au.readObjectRealTime(new FirebaseAU.DataObjectListener() {
                    @Override
                    public DatabaseReference getReferenceDataBase() {
                        return auto;
                    }

                    @Override
                    public void getObjectReference(@NonNull DataSnapshot dataSnapshot) {
                        OUsuario user = dataSnapshot.getValue(OUsuario.class);
                        name = user.name;
                    }
                });

                OForo  f = new OForo(titulo, descripcion, name, fecha);


                DatabaseReference ref = au.getReferencia().child(Firebase_value.FORO).child(Firebase_value.FORO);
                DatabaseReference ref2 = au.getReferencia().child(Firebase_value.FORO).child(Firebase_value.FOROS);

                UUID ruta = UUID.randomUUID();
                String r = ruta.toString();

                ref.child(r).setValue(f);
                ref2.child(r).setValue(f);


                Toast.makeText(this, "Foro Agregado", Toast.LENGTH_SHORT).show();

                onBackPressed();

                break;


        }
    }
}
