package Interfaz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aprendizajeactivo.app_comunity.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class ActionActivity {


    private static int DURATION_TRANSITION = 1000;

    public static void ocularClickTeclado(final Activity activity, View v){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                v.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
    }


    public static void ocularKeyTeclado(final Activity activity, View v){
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    v.clearFocus();
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return true;
            }
        });
    }



    //Ir a actividades-------------------

    public static void goToActivity(Activity activity, Class destino) {
        Intent intent = new Intent(activity, destino);
        activity.startActivity(intent);
    }

    public static void goToActivity(Activity activity, Class destino, goToActivit activit) {
        Intent intent = new Intent(activity, destino);
        activit.accionActivity(intent);
        if(activit.irActividad()) {
            activity.startActivity(intent);
        }
    }

    public static void goToActivity(Activity activity, Class destino, Transition transition){
        transition.setDuration(DURATION_TRANSITION);
        transition.setInterpolator(new DecelerateInterpolator());
        activity.getWindow().setExitTransition(transition);
        activity.getWindow().setAllowEnterTransitionOverlap(false);

        Intent intent = new Intent(activity, destino);
        activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    public static void transcionEnter(Activity activity, Transition transition){
        transition.setDuration(DURATION_TRANSITION);
        transition.setInterpolator(new DecelerateInterpolator());
        activity.getWindow().setAllowEnterTransitionOverlap(false);
        activity.getWindow().setEnterTransition(transition);
    }

    public interface goToActivit{
        public void accionActivity(Intent intent);
        public boolean irActividad();
    }


    //Barras transparentes-----------------------

    public static void translucidaBar(Activity activity){
        Window w = activity.getWindow();
        w.getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        w.setStatusBarColor(Color.TRANSPARENT);
    }

    public static void translucidaStatus(Activity activity){
       Window w = activity.getWindow();
       w.setNavigationBarColor(Color.GRAY);
       /* w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);*/
    }

    public static void interfazTranslucida(Activity activity){
        translucidaBar(activity);
        translucidaStatus(activity);
    }


    //Public void cargar interfaz---------------------------------------


    public static void cargarFuentes(){
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Muli-Italic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static void setFuente(Activity activity,View view, String fuente){

// Seteamos en una Variable donde tenemos la fuente (podemos omitir este paso y ponerla directamente cuando cargamos la fuente)
        String carpetaFuente = "fonts/" + fuente;

// Obtenemos la id del TextView donde queremos cambiar la fuente

// Cargamos la fuente
        Typeface font = Typeface.createFromAsset(activity.getAssets(), carpetaFuente);

// Aplicamos la fuente
        if(view instanceof  TextView){
            TextView t = (TextView) view;
            t.setTypeface(font);
        }else if(view instanceof  EditText){
            EditText t = (EditText) view;
            t.setTypeface(font);
        }
    }
}
