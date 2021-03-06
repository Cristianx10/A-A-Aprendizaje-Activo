package HomePrincipal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aprendizajeactivo.app_comunity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import FirebaseConexion.FirebaseAU;
import FirebaseConexion.Firebase_value;
import Interfaz.ActionActivity;
import ObjetosList.OUsuario;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomePage extends AppCompatActivity implements
        HomePrincipal.OnFragmentInteractionListener,
        HomeCalendario.OnFragmentInteractionListener,
        HomeForos.OnFragmentInteractionListener,
        HomeGrupos.OnFragmentInteractionListener,
        HomeGroupCreate.OnFragmentInteractionListener
        {



            private FirebaseAU au;
    private TextView mTextMessage;

    private Fragment frame_inicio;
    private Fragment frame_grupos;
    private Fragment frame_calendario;
    private Fragment frame_foros;

    private TextView tv_logo_usuario;

            static public String name;
            static public String UID;

    public ImageView iv_opciones_page_index;

    private BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener  = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.setCustomAnimations(
                    R.animator.enter_from_right,
                    R.animator.exit_to_left,
                    R.animator.enter_from_left,
                    R.animator.exit_to_right);

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    fragmentTransaction.replace(R.id.frama_home_page_principal, frame_inicio);
                    iv_opciones_page_index.setImageResource(R.drawable.ic_settings);

                    break;
                case R.id.navigation_group:
                    mTextMessage.setText("Grupos");
                    fragmentTransaction.replace(R.id.frama_home_page_principal, frame_grupos);
                    iv_opciones_page_index.setImageResource(R.drawable.add);
                    break;

                case R.id.navigation_calendar:
                    mTextMessage.setText("Calendario");
                    fragmentTransaction.replace(R.id.frama_home_page_principal, frame_calendario);
                    iv_opciones_page_index.setImageResource(R.drawable.add);
                    break;

                case R.id.navigation_foros:
                    mTextMessage.setText("Foros");
                    fragmentTransaction.replace(R.id.frama_home_page_principal, frame_foros);
                    iv_opciones_page_index.setImageResource(R.drawable.add);
                    break;

            }
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            return true;


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        au.getIntance();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        tv_logo_usuario = findViewById(R.id.tv_name_usuario);
        iv_opciones_page_index = findViewById(R.id.iv_opciones_page_index);


        au.readObjectRealTime(new FirebaseAU.DataObjectListener() {
            @Override
            public DatabaseReference getReferenceDataBase() {
                return au.getReferencia().child(Firebase_value.USUARIOS).child(au.getUserUid()).child(Firebase_value.USUARIOS_PERFIL);
            }

            @Override
            public void getObjectReference(@NonNull DataSnapshot dataSnapshot) {
                OUsuario user = dataSnapshot.getValue(OUsuario.class);
                tv_logo_usuario.setText(user.name);
                name = user.name;
                UID = user.uid;
            }
        });

        ActionActivity.interfazTranslucida(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        frame_inicio = new HomePrincipal();
        frame_grupos = new HomeGrupos();
        frame_calendario = new HomeCalendario();
        frame_foros = new HomeForos();



        fragmentTransaction.add(R.id.frama_home_page_principal, frame_inicio).commit();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
