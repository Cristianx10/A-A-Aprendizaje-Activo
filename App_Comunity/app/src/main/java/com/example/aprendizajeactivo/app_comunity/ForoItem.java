package com.example.aprendizajeactivo.app_comunity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import FirebaseConexion.FirebaseAU;
import FirebaseConexion.Firebase_value;
import ListFirebase.ListFirebase;
import ObjetosList.ForoU;

public class ForoItem extends AppCompatActivity {

    private TextView tv_titulo_foro_interno;
    private TextView tv_autor_foro_interno;
    private TextView tv_descripcion_foro_interno;
    private ImageButton ib_menu_foro;
    private TextView tv_num_likes_foro;
    private CheckBox cb_like_foro;
    private EditText et_comentario_foro;
    private ImageButton btn_enviar_comentario_foro;

    private ListView lv_foro_item;

    private FirebaseAU au;

    private ListFirebase<ForoU> listFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro_item);

        au.getIntance();

        Bundle bundle = getIntent().getExtras();

        lv_foro_item = findViewById(R.id.lv_foro_item);

        tv_titulo_foro_interno = findViewById(R.id.tv_titulo_foro_interno);
        tv_autor_foro_interno = findViewById(R.id.tv_autor_foro_interno);
        tv_descripcion_foro_interno = findViewById(R.id.tv_descripcion_foro_interno);
        ib_menu_foro = findViewById(R.id.ib_menu_foro);
        tv_num_likes_foro = findViewById(R.id.tv_num_likes_foro);
        cb_like_foro = findViewById(R.id.cb_like_foro);
        et_comentario_foro = findViewById(R.id.et_comentario_foro);
        btn_enviar_comentario_foro = findViewById(R.id.btn_enviar_comentario_foro);


        tv_titulo_foro_interno.setText(bundle.getString("titulo"));
        tv_autor_foro_interno.setText(bundle.getString("autor"));
        tv_descripcion_foro_interno.setText(bundle.getString("descrip"));
        tv_num_likes_foro.setText(bundle.getString("titulo"));

        String uid = bundle.getString("uid");

        final DatabaseReference dato = au.getReferencia().child(Firebase_value.FORO).child(Firebase_value.FOROS).child(uid);


        listFirebase = new ListFirebase<ForoU>(new ListFirebase.getVariables<ForoU>() {
            @Override
            public ListView getViewListas() {
                return lv_foro_item;
            }

            @Override
            public Query getUbicacionBase() {
                return dato;
            }

            @Override
            public Class getClaseModelo() {
                return ForoU.class;
            }

            @Override
            public int getLayoutList() {
                return R.layout.renglon_foros_interno;
            }

            @Override
            public void populateView(@NonNull View v, @NonNull ForoU model, int position) {


                ImageView iv_imagen_comentario_foros_interno = v.findViewById(R.id.iv_imagen_comentario_foros_interno);
                TextView tv_nombre_comentario_foros = v.findViewById(R.id.tv_nombre_comentario_foros);
                TextView tv_descripcion_foro_interno = v.findViewById(R.id.tv_descripcion_foro_interno);
                ImageButton ib_menu_foro = v.findViewById(R.id.ib_menu_foro);
                TextView tv_num_likes_comentarios_foro_interno;tv_num_likes_comentarios_foro_interno = v.findViewById(R.id.tv_num_likes_comentarios_foro_interno);
                CheckBox cb_like_foro_comentario_interno;cb_like_foro_comentario_interno = v.findViewById(R.id.cb_like_foro_comentario_interno);



                tv_descripcion_foro_interno.setText(model.mensaje);
                tv_nombre_comentario_foros.setText(model.autor);

                if(cb_like_foro_comentario_interno.isChecked()){
                    
                }else{

                }

            }
        });


    }
}
