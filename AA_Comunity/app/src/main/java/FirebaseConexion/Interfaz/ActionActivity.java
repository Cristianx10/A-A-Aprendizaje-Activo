package FirebaseConexion.Interfaz;

import android.app.Activity;
import android.content.Intent;


public class ActionActivity {


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
