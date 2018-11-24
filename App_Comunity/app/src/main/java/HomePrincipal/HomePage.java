package HomePrincipal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.aprendizajeactivo.app_comunity.R;

import Interfaz.ActionActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomePage extends AppCompatActivity implements
        HomePrincipal.OnFragmentInteractionListener,
        HomeCalendar.OnFragmentInteractionListener,
        HomeForos.OnFragmentInteractionListener,
        HomeGrupos.OnFragmentInteractionListener {

    private TextView mTextMessage;

    private Fragment frame_inicio;
    private Fragment frame_grupos;
    private Fragment frame_calendario;
    private Fragment frame_foros;


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

                    break;
                case R.id.navigation_group:
                    mTextMessage.setText("Grupos");
                    fragmentTransaction.replace(R.id.frama_home_page_principal, frame_grupos);

                    break;
                case R.id.navigation_foros:
                    mTextMessage.setText("Calendario");
                    fragmentTransaction.replace(R.id.frama_home_page_principal, frame_calendario);

                    break;
                case R.id.navigation_calendar:
                    mTextMessage.setText("Foros");
                    fragmentTransaction.replace(R.id.frama_home_page_principal, frame_foros);

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

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ActionActivity.interfazTranslucida(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        frame_inicio = new HomePrincipal();
        frame_grupos = new HomeGrupos();
        frame_calendario = new HomeCalendar();
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
