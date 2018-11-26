package HomePrincipal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aprendizajeactivo.app_comunity.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import FirebaseConexion.FirebaseAU;
import FirebaseConexion.Firebase_value;
import ListFirebase.ListFirebase;
import ObjetosList.OForo;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeForos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeForos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeForos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseAU au;

    private View vista;
    private ListView lv_lista_foros;

    private ListFirebase<OForo> foroListFirebase;

    private OnFragmentInteractionListener mListener;

    public HomeForos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeForos.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeForos newInstance(String param1, String param2) {
        HomeForos fragment = new HomeForos();
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

        vista = inflater.inflate(R.layout.fragment_home_foros, container, false);


        lv_lista_foros = vista.findViewById(R.id.lv_lista_foros);

        final DatabaseReference reference = au.getReferencia().child(Firebase_value.FORO);


        foroListFirebase = new ListFirebase<OForo>(new ListFirebase.getVariables<OForo>() {
            @Override
            public ListView getViewListas() {
                return lv_lista_foros;
            }

            @Override
            public Query getUbicacionBase() {
                return reference;
            }

            @Override
            public Class getClaseModelo() {
                return OForo.class;
            }

            @Override
            public int getLayoutList() {
                return R.layout.renglon_foros;
            }

            @Override
            public void populateView(@NonNull View v, @NonNull OForo model, int position) {

                ImageView iv_foto_foro = v.findViewById(R.id.iv_foto_foro);
                TextView tv_titulo_foro = v.findViewById(R.id.tv_titulo_foro);
                TextView tv_autor_foro = v.findViewById(R.id.tv_autor_foro);
                TextView tv_num_respuestas_foro = v.findViewById(R.id.tv_num_respuestas_foro);
                TextView tv_fecha_foro = v.findViewById(R.id.tv_fecha_foro);

                String num = foroListFirebase.getAdapter().getSnapshots().size() + "";

                tv_titulo_foro.setText(model.titulo);
                tv_autor_foro.setText(model.autor);
                tv_num_respuestas_foro.setText(num);
                tv_fecha_foro.setText(model.fecha);

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
}
