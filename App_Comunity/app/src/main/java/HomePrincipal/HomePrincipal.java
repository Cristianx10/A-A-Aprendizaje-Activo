package HomePrincipal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aprendizajeactivo.app_comunity.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import FirebaseConexion.FirebaseAU;
import FirebaseConexion.Firebase_value;
import ListFirebase.ListFirebase;
import ObjetosList.Grupo;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomePrincipal.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomePrincipal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePrincipal extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View vista;
    private ListView lv_home_notificacion;

    private FirebaseAU au;
    private ListFirebase<Grupo> listGroup;

    private OnFragmentInteractionListener mListener;

    public HomePrincipal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePrincipal.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePrincipal newInstance(String param1, String param2) {
        HomePrincipal fragment = new HomePrincipal();
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
        vista =inflater.inflate(R.layout.fragment_home_principal, container, false);

        lv_home_notificacion = vista.findViewById(R.id.lv_home_notificacion);

        au.getIntance();

        final DatabaseReference ref = au.getReferencia().child(Firebase_value.GRUPOS).child(Firebase_value.GRUPOS_GRUPOS);
        //  au.writeObjeto(ref, g);

        listGroup = new ListFirebase<Grupo>(new ListFirebase.getVariables<Grupo>() {
            @Override
            public ListView getViewListas() {
                return lv_home_notificacion;
            }

            @Override
            public Query getUbicacionBase() {
                return ref;
            }

            @Override
            public Class getClaseModelo() {
                return Grupo.class;
            }

            @Override
            public int getLayoutList() {
                return R.layout.renglon_grupos_icon;
            }

            @Override
            public void populateView(@NonNull View v, @NonNull Grupo model, int position) {

            }
        });

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
}
