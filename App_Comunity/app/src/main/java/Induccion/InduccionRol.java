package Induccion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.aprendizajeactivo.app_comunity.R;

import FirebaseConexion.Firebase_value;
import Interfaz.ActionActivity;
import Interfaz.Comunicador;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InduccionRol.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InduccionRol#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InduccionRol extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private Comunicador comunicador;
    private View vista;


    private Button btn_induccion_rol_profe;
    private Button btn_induccion_rol_estu;

    private OnFragmentInteractionListener mListener;

    public InduccionRol() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InduccionRol.
     */
    // TODO: Rename and change types and number of parameters
    public static InduccionRol newInstance(String param1, String param2) {
        InduccionRol fragment = new InduccionRol();
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

        vista = inflater.inflate(R.layout.fragment_induccion_rol, container, false);

        btn_induccion_rol_profe = vista.findViewById(R.id.btn_induccion_rol_profe);
        btn_induccion_rol_estu = vista.findViewById(R.id.btn_induccion_rol_estu);

        comunicador = (Comunicador) getActivity();

        btn_induccion_rol_profe.setOnClickListener(this);
        btn_induccion_rol_estu.setOnClickListener(this);




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

            case R.id.btn_induccion_rol_profe:

                comunicador.enviado("rol", Firebase_value.USUARIO_PROFESOR);

                break;

            case R.id.btn_induccion_rol_estu:


                comunicador.enviado("rol", Firebase_value.USUARIO_ESTUDIANTE);
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
