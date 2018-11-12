package com.example.aprendizajeactivo.app_comunity;

import android.app.Application;
import android.content.Context;

import Interfaz.ActionActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Application {

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
