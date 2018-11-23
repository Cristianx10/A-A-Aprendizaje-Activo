package Induccion;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aprendizajeactivo.app_comunity.R;

import FirebaseConexion.FragmentPagerAdapter;
import Interfaz.ActionActivity;

public class Induccion extends AppCompatActivity implements InduccionOpcion.OnFragmentInteractionListener,
        InduccionRol.OnFragmentInteractionListener,
        InduccionLogin.OnFragmentInteractionListener,
        InduccionRegister.OnFragmentInteractionListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private Fragment page_opcion;


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_induccion);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        page_opcion = new InduccionOpcion();
        fragmentTransaction.add(R.id.frame_induccion, page_opcion).commit();
        ActionActivity.interfazTranslucida(this);

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}

