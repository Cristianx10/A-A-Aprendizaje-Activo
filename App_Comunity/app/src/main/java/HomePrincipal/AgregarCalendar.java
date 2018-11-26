package HomePrincipal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aprendizajeactivo.app_comunity.R;
import com.google.firebase.database.DatabaseReference;

import FirebaseConexion.FirebaseAU;
import FirebaseConexion.Firebase_value;
import ObjetosList.OTarea;

public class AgregarCalendar extends AppCompatActivity implements View.OnClickListener {

    FirebaseAU au;

    private EditText et_tituloActividad;
    private EditText et_descripcionActividad;
    private EditText et_fechaEsperada;
    private EditText et_fechaMaxima;
    private Button btn_actividad_cancelar;
    private Button btn_actividad_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_calendar);


        au.getIntance();

        et_tituloActividad = findViewById(R.id.et_tituloActividad);
        et_descripcionActividad = findViewById(R.id.et_descripcionActividad);
        et_fechaEsperada = findViewById(R.id.et_fecha_entrega);
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

                DatabaseReference reference = au.getReferencia().child(Firebase_value.USUARIOS).child(au.getUserUid()).child(Firebase_value.TARAEA);

                String name = et_tituloActividad.getText().toString();
                String descripcion = et_descripcionActividad.getText().toString();
                String fechaEspera = et_fechaEsperada.getText().toString();


                OTarea tarea = new OTarea(name, descripcion, "Martes", fechaEspera);

                au.writeObjeto(reference, tarea);


                break;
        }
    }


}
