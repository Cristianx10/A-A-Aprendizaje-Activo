package Induccion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.aprendizajeactivo.app_comunity.IOnBackPressed;
import com.example.aprendizajeactivo.app_comunity.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InduccionOpcion.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InduccionOpcion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InduccionOpcion extends Fragment implements IOnBackPressed, View.OnClickListener,
        InduccionRegister.OnFragmentInteractionListener,
        InduccionLogin.OnFragmentInteractionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View vista;
    private Fragment page_login;
    private Fragment page_registro;

    private OnFragmentInteractionListener mListener;

    public InduccionOpcion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InduccionOpcion.
     */
    // TODO: Rename and change types and number of parameters
    public static InduccionOpcion newInstance(String param1, String param2) {
        InduccionOpcion fragment = new InduccionOpcion();
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
        // Inflate the layout for this fragment

        page_login = new InduccionLogin();
        page_registro = new InduccionRegister();

        vista = inflater.inflate(R.layout.fragment_induccion_opcion, container, false);

        Button btn_induccion_registrar = vista.findViewById(R.id.btn_induccion_registrar);
        Button btn_Induccion_login = vista.findViewById(R.id.btn_induccion_login);

        btn_induccion_registrar.setOnClickListener(this);
        btn_Induccion_login.setOnClickListener(this);




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
        
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(
                R.animator.enter_from_right,
                R.animator.exit_to_left,
                R.animator.enter_from_left,
                R.animator.exit_to_right);


        switch (v.getId()){
            case R.id.btn_induccion_registrar:
                fragmentTransaction.replace(R.id.frame_induccion, page_registro);
                fragmentTransaction.addToBackStack("registro");
                break;
            case R.id.btn_induccion_login:
               fragmentTransaction.replace(R.id.frame_induccion, page_login);
                fragmentTransaction.addToBackStack(null);
                break;

        }

        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onBackPressed() {
        return true;
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
