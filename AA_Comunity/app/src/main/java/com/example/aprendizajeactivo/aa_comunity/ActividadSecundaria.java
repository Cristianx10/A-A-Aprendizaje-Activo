package com.example.aprendizajeactivo.aa_comunity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.view.animation.DecelerateInterpolator;

public class ActividadSecundaria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fade fadeIn = new Fade(Fade.IN);
        fadeIn.setDuration(Trasicion.DURATION_TRANSITION);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        getWindow().setEnterTransition(fadeIn);


        setContentView(R.layout.activity_actividad_secundaria);




    }
}
