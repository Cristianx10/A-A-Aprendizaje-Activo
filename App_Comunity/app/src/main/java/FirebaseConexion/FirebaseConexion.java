package FirebaseConexion;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConexion implements Firebase_value {

    static FirebaseConexion fc;
    static FirebaseAuth auth;
    static FirebaseDatabase database;
    static DatabaseReference ref;
    static FirebaseUser user;
    static Activity activity;

    public FirebaseConexion(){
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
    }

    public static void inicilizarExtra(){

    }

    public static void getIntance(){
        if(fc == null){
            fc = new FirebaseConexion();
        }
        user = auth.getCurrentUser();
        inicilizarExtra();
    }

    public static void getIntance(Activity main){
        if(fc == null){
            fc = new FirebaseConexion();
        }
        user = auth.getCurrentUser();
        activity = main;
        inicilizarExtra();
    }



    public static void goToActitivity(Activity activity, Activity objetivo){
        Intent intent = new Intent(activity, objetivo.getClass());
    }

    public static void goToActitivity(Activity objetivo){
        Intent intent = new Intent(activity, objetivo.getClass());
    }

    /*
        Escribir en la base de datos
     */

    public static void setRef(DatabaseReference reference){
        ref = reference;
    }

    public static void writeDatabase(Object object){
        ref.setValue(object);
    }

    public static void writeDatabaseAdd(Object object){
        ref.push().setValue(object);
    }


    public static void writeDatabase(String name, Object object){
        ref.child(name).setValue(object);
    }

    public static void writeDatabase(DatabaseReference reference, Object object){
        reference.setValue(object);
    }


    public static FirebaseAuth getAuth() {
        return auth;
    }

    public static FirebaseDatabase getDatabase() {
        return database;
    }

    public static DatabaseReference getRef() {
        return ref;
    }

    public static FirebaseUser getUser() {
        return user;
    }



    //Actividad de login del usuario

    public static void loginActivity(String user, String email, final ValidarAutentication formulario){
        auth.signInWithEmailAndPassword(user, email).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    formulario.taskIsSuccessful(task);
                }else{
                    formulario.errorTaskException(task);
                }
            }
        });
    }

    public static void registroActivity(String user, String email, final ValidarAutentication formulario){
        auth.createUserWithEmailAndPassword(user, email).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    formulario.taskIsSuccessful(task);
                }else{
                    formulario.errorTaskException(task);
                }
            }
        });
    }


    public interface ValidarAutentication{
        public void taskIsSuccessful(@NonNull Task<AuthResult> task);
        public void errorTaskException(@NonNull Task<AuthResult> task);
    }

}


/**
 * classpath 'com.google.gms:google-services:4.0.1'
 *
 * implementation 'com.google.firebase:firebase-core:16.0.4'
 * implementation 'com.google.firebase:firebase-auth:16.0.4'
 * implementation 'com.google.firebase:firebase-database:16.0.3'
 *
 * implementation 'com.firebaseui:firebase-ui-database:4.2.1'
 * */
