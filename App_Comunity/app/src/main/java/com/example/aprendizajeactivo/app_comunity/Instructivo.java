package com.example.aprendizajeactivo.app_comunity;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Interfaz.ActionActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Instructivo extends AppCompatActivity implements InstructivoRol.OnFragmentInteractionListener,HomeCalendar.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructivo);

        ActionActivity.interfazTranslucidaInv(this);

        InstructivoRol instructivoRol = new InstructivoRol();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.place_holder_instructivo, instructivoRol, null);
        fragmentTransaction.commit();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
