package Interfaz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class ActionActivity {



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

    public interface goToActivit{
        public void accionActivity(Intent intent);
        public boolean irActividad();
    }
}
