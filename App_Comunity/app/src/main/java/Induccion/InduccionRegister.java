package Induccion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aprendizajeactivo.app_comunity.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.ArrayList;
import java.util.List;

import FirebaseConexion.FirebaseAU;
import FirebaseConexion.Firebase_value;
import Interfaz.ActionActivity;
import ObjetosList.OUsuario;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InduccionRegister.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InduccionRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InduccionRegister extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FirebaseAU au;
    private View viewPrincipal;

    public String tipoUsuario="USUARIO";

    private EditText et_registro_name;
    private EditText et_registro_email;
    private EditText et_registro_password;
    private EditText et_registro_password_confir;
    private Button btn_registro_registrar;

    private LinearLayout ll_registro_principal;

    private OnFragmentInteractionListener mListener;

    public InduccionRegister() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InduccionRegister.
     */
    // TODO: Rename and change types and number of parameters
    public static InduccionRegister newInstance(String param1, String param2) {
        InduccionRegister fragment = new InduccionRegister();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            tipoUsuario = getArguments().getString("rol");
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        viewPrincipal = inflater.inflate(R.layout.fragment_induccion_register, container, false);

        au.getIntance();

        ll_registro_principal = viewPrincipal.findViewById(R.id.ll_registro_principal);
        et_registro_name = viewPrincipal.findViewById(R.id.et_registro_name);
        et_registro_email = viewPrincipal.findViewById(R.id.et_registro_email);
        et_registro_password = viewPrincipal.findViewById(R.id.et_registro_password);
        et_registro_password_confir = viewPrincipal.findViewById(R.id.et_registro_password_confir);
        btn_registro_registrar = viewPrincipal.findViewById(R.id.btn_registro_registrar);

        btn_registro_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });





        return viewPrincipal;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void registrar(){
        au.createWithEmailAndPassword(et_registro_email, et_registro_password, new FirebaseAU.DataUserValidation() {
            @Override
            public void actionIsSuccessful(@NonNull Task<AuthResult> task) {
                String name = et_registro_name.getText().toString();
                String email = et_registro_email.getText().toString();
                OUsuario usuario = new OUsuario(name, email, au.getUserUid(), tipoUsuario);
                au.writeEnUidUsuario(au.getReferencia().child(Firebase_value.USUARIOS), usuario);
            }

            @Override
            public void actionErrorException(@NonNull Task<AuthResult> task) {
                ArrayList<EditText>editTexts = new ArrayList<>();
                editTexts.add(et_registro_name);
                editTexts.add(et_registro_email);
                editTexts.add(et_registro_password_confir);
                editTexts.add(et_registro_password);
                ActionActivity.limpiarCajas(editTexts);
            }

        });
    }
}
