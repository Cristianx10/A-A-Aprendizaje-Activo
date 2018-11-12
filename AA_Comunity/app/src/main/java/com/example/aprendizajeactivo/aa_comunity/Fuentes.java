package com.example.aprendizajeactivo.aa_comunity;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.TextView;

import Interfaz.ActionActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Fuentes extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActionActivity.cargarFuentes();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
