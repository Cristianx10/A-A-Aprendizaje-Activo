package HomePrincipal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aprendizajeactivo.app_comunity.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import FirebaseConexion.FirebaseAU;
import FirebaseConexion.Firebase_value;
import Interfaz.ActionActivity;
import ListFirebase.ListFirebase;
import ObjetosList.OTarea;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeCalendario.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeCalendario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeCalendario extends Fragment  implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View vista;


    private FirebaseAU au;

    private ListView lv_calendarActividades;

    private ListFirebase<OTarea> listFirebase;

    private OnFragmentInteractionListener mListener;

    public HomeCalendario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeCalendario.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeCalendario newInstance(String param1, String param2) {
        HomeCalendario fragment = new HomeCalendario();
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
        HomePage index = (HomePage) getActivity();
        index.iv_opciones_page_index.setOnClickListener(this);


        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_home_calendario, container, false);

        lv_calendarActividades = vista.findViewById(R.id.lv_calendarActividades);

        final DatabaseReference reference = au.getReferencia().child(Firebase_value.USUARIOS).child(au.getUserUid()).child(Firebase_value.TARAEA);

        listFirebase = new ListFirebase<OTarea>(new ListFirebase.getVariables<OTarea>() {
            @Override
            public ListView getViewListas() {
                return lv_calendarActividades;
            }

            @Override
            public Query getUbicacionBase() {
                return reference;
            }

            @Override
            public Class getClaseModelo() {
                return OTarea.class;
            }

            @Override
            public int getLayoutList() {
                return R.layout.renglon_actividades;
            }

            @Override
            public void populateView(@NonNull View v, @NonNull OTarea model, final int position) {


                TextView tv_diaActividadRenglon = v.findViewById(R.id.tv_diaActividadRenglon);
                TextView tv_fechaActividadRenglon = v.findViewById(R.id.tv_fechaActividadRenglon);
                TextView tv_tituloActividadRenglon = v.findViewById(R.id.tv_tituloActividadRenglon);
                TextView tv_descripcionActividadRenglon = v.findViewById(R.id.tv_descripcionActividadRenglon);
                ImageButton ib_delete_actividad = v.findViewById(R.id.ib_delete_actividad);

                tv_diaActividadRenglon.setText(model.fecha);
                tv_fechaActividadRenglon.setText(model.dia);
                tv_tituloActividadRenglon.setText(model.name);
                tv_descripcionActividadRenglon.setText(model.description);

                ib_delete_actividad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listFirebase.getAdapter().getRef(position).removeValue();
                    }
                });



            }
        });

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_opciones_page_index:
                ActionActivity.goToActivity(getActivity(), AgregarCalendar.class);

                Toast.makeText(getActivity(), "AgregarCalendario", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
