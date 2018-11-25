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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aprendizajeactivo.app_comunity.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import FirebaseConexion.FirebaseAU;
import FirebaseConexion.Firebase_value;
import ListFirebase.ListFirebase;
import ObjetosList.Grupo;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeGrupos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeGrupos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeGrupos extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    FirebaseAU au;

    private View vista;
    private GridView gridview_grupos;

    private ListFirebase<Grupo> listGroup;

    public HomeGrupos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeGrupos.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeGrupos newInstance(String param1, String param2) {
        HomeGrupos fragment = new HomeGrupos();
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

        vista = inflater.inflate(R.layout.fragment_home_grupos, container, false);

        gridview_grupos = vista.findViewById(R.id.lv_gridview_grupos);

       // Grupo g = new Grupo("Matematicas", "https://firebasestorage.googleapis.com/v0/b/aacomunity-8ac35.appspot.com/o/ic_matematica.png?alt=media&token=95f4894e-a263-47c5-a510-ca55fc524685");

        au.getIntance();

        final DatabaseReference ref = au.getReferencia().child(Firebase_value.GRUPOS).child(Firebase_value.GRUPOS_GRUPOS);
       // au.writeObjeto(ref, g);

        listGroup = new ListFirebase<Grupo>(new ListFirebase.getVariablesGrid<Grupo>() {
            @Override
            public GridView getViewListas() {
                return gridview_grupos;
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

                TextView titulo = v.findViewById(R.id.tv_renglon_grupo_name);
                CircleImageView imagen = v.findViewById(R.id.iv_renglon_grupo_image);

                titulo.setText(model.name);

                String url = model.imagen;

                Glide   .with(getActivity())
                        .load(url)
                        .into(imagen);

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
