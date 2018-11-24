package Interfaz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aprendizajeactivo.app_comunity.R;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class ActionActivity {


    private static int DURATION_TRANSITION = 2000;

    public static void ocularClickTeclado(final Activity activity, View v) {
      /*  v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                activity.onBackPressed();
                v.clearFocus();
            }
        });*/

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm =
                        (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                v.clearFocus();
            }
        });

        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    public static void ocularKeyTeclado(final Activity activity, View v) {

/*
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    activity.onBackPressed();
                    v.clearFocus();
                }


               // v.clearFocus();

               // activity.onBackPressed();
                return true;
            }


        });*/

    /*    InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
*/




    }


    //Ir a actividades-------------------

    public static void goToActivity(Activity activity, Class destino) {
        Intent intent = new Intent(activity, destino);
        activity.startActivity(intent);
    }

    public static void goToActivity(Activity activity, Class destino, goToActivit activit) {
        Intent intent = new Intent(activity, destino);
        activit.accionActivity(intent);
        if (activit.irActividad()) {
            activity.startActivity(intent);
        }
    }

    public static void goToActivity(Activity activity, Class destino, Transition transition) {
        transition.setDuration(DURATION_TRANSITION);
        transition.setInterpolator(new DecelerateInterpolator());
        activity.getWindow().setExitTransition(transition);
        activity.getWindow().setAllowEnterTransitionOverlap(false);

        Intent intent = new Intent(activity, destino);
        activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    public static void transcionEnter(Activity activity, Transition transition) {
        transition.setDuration(DURATION_TRANSITION);
        transition.setInterpolator(new DecelerateInterpolator());
        activity.getWindow().setAllowEnterTransitionOverlap(false);
        activity.getWindow().setEnterTransition(transition);
    }

    public interface goToActivit {
        public void accionActivity(Intent intent);

        public boolean irActividad();
    }


    //Barras transparentes-----------------------

    public static void translucidaBar(Activity activity) {
        Window w = activity.getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        w.setStatusBarColor(Color.TRANSPARENT);
    }

    public static void translucidaStatus(Activity activity) {
        Window w = activity.getWindow();
        w.setNavigationBarColor(Color.GRAY);
        w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static void translucidaStatusInv(Activity activity) {
        Window w = activity.getWindow();
        w.setNavigationBarColor(Color.TRANSPARENT);
       /* w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);*/
    }


    public static void interfazTranslucida(Activity activity) {
        translucidaBar(activity);
        translucidaStatus(activity);
    }

    public static void interfazTranslucidaInv(Activity activity) {
        translucidaBar(activity);
        translucidaStatusInv(activity);
    }


    //Public void cargar interfaz---------------------------------------


    public static void cargarFuentes() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Muli-Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static void setFuente(Activity activity, View view, String fuente) {

// Seteamos en una Variable donde tenemos la fuente (podemos omitir este paso y ponerla directamente cuando cargamos la fuente)
        String carpetaFuente = "fonts/" + fuente;

// Obtenemos la id del TextView donde queremos cambiar la fuente

// Cargamos la fuente
        Typeface font = Typeface.createFromAsset(activity.getAssets(), carpetaFuente);

// Aplicamos la fuente
        if (view instanceof TextView) {
            TextView t = (TextView) view;
            t.setTypeface(font);
        } else if (view instanceof EditText) {
            EditText t = (EditText) view;
            t.setTypeface(font);
        }
    }

    public static android.app.FragmentTransaction inicilizarTrasicion(Activity activity){
        android.app.FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(
                R.animator.enter_from_right,
                R.animator.exit_to_left,
                R.animator.enter_from_left,
                R.animator.exit_to_right);

        return fragmentTransaction;
    }

    public static void limpiarCajas(ArrayList<EditText> editTexts){
        for (EditText editText : editTexts){
            editText.setText("");
        }
    }


    public static int getPantalla(View view) {
        int i = view.getDisplay().getHeight();
        return i;
    }
}
