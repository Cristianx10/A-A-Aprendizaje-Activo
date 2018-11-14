package Interfaz;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * This class is a generic way of backing an Android {@link ListView} with a Firebase
 * location. It handles all of the child events at the given Firebase location. It marshals received
 * data into the given class type.
 * <p>
 * See the <a href="https://github.com/firebase/FirebaseUI-Android/blob/master/database/README.md">README</a>
 * for an in-depth tutorial on how to set up the FirebaseListAdapter.
 *
 * @param <T> The class type to use as a model for the data contained in the children of the given
 *            Firebase location
 */


public class ListaFirebase<T>{

    FirebaseDatabase database;
    ListView lista;

    //1. Crear adaptador
    FirebaseListAdapter adapter;

    public ListaFirebase(final getVariables<T> variables){
        this.lista = variables.getViewListas();
        Query ref = variables.getUbicacionBase();

        FirebaseListOptions options = new FirebaseListOptions.Builder()
                .setLayout(variables.getLayoutList())
                .setQuery(ref, variables.getClaseModelo())
                .build();


        adapter = new FirebaseListAdapter<T>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull T model, int position) {
                variables.populateView(v, model, position);

            }
        };

        lista.setAdapter(adapter);
    }

    public interface getVariables<T>{
        public ListView getViewListas();
        public Query getUbicacionBase();
        public Class getClaseModelo();
        public int getLayoutList();
        public void populateView(@NonNull View v, @NonNull T model, int position);
        /*

        Debe implementar la interfaz settings o escribir los metodos manualmente

         */
    }


    public void startList(){
        adapter.startListening();
    }
    public void stopList(){
        adapter.startListening();
    }
}

interface settingList{
    void onStart();
    void onStop();
}
