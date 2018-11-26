package com.example.aprendizajeactivo.app_comunity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

import FirebaseConexion.FirebaseAU;
import FirebaseConexion.Firebase_value;
import ObjetosList.OTarea;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalendarioAgregar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalendarioAgregar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarioAgregar extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FirebaseAU au;

    private EditText et_tituloActividad;
    private EditText et_descripcionActividad;
    private EditText et_fechaEsperada;
    private EditText et_fechaMaxima;
    private Button btn_actividad_cancelar;
    private Button btn_actividad_finish;

    private View vista;

    private OnFragmentInteractionListener mListener;

    public CalendarioAgregar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarioAgregar.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarioAgregar newInstance(String param1, String param2) {
        CalendarioAgregar fragment = new CalendarioAgregar();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        au.getIntance();

        vista = inflater.inflate(R.layout.fragment_calendario_agregar, container, false);

        et_tituloActividad = vista.findViewById(R.id.et_tituloActividad);
        et_descripcionActividad = vista.findViewById(R.id.et_descripcionActividad);
        et_fechaEsperada = vista.findViewById(R.id.et_fechaEsperada);
        et_fechaMaxima = vista.findViewById(R.id.et_fechaMaxima);
        btn_actividad_cancelar = vista.findViewById(R.id.btn_actividad_cancelar);
        btn_actividad_finish = vista.findViewById(R.id.btn_actividad_finish);


        // Inflate the layout for this fragment
        return vista;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_actividad_cancelar:

                break;

            case R.id.btn_actividad_finish:

                DatabaseReference reference = au.getReferencia().child(Firebase_value.USUARIOS).child(au.getUserUid()).child(Firebase_value.TARAEA);

                String name = et_tituloActividad.getText().toString();
                String descripcion = et_descripcionActividad.getText().toString();
                String fechaEspera = et_fechaEsperada.getText().toString();
                String fechaMax = et_fechaMaxima.getText().toString();

                OTarea tarea = new OTarea(name, descripcion, fechaEspera, fechaMax);



                break;
        }
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
}
