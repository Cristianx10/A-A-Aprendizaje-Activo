package com.example.aprendizajeactivo.app_comunity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ForoInterno.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ForoInterno#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForoInterno extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView tv_titulo_foro_interno;
    private TextView tv_autor_foro_interno;
    private TextView tv_descripcion_foro_interno;
    private ImageButton ib_menu_foro;
    private TextView tv_num_likes_foro;
    private CheckBox cb_like_foro;
    private EditText et_comentario_foro;
    private Button btn_enviar_comentario_foro;


    private View view;


    private OnFragmentInteractionListener mListener;

    public ForoInterno() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForoInterno.
     */
    // TODO: Rename and change types and number of parameters
    public static ForoInterno newInstance(String param1, String param2) {
        ForoInterno fragment = new ForoInterno();
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

        view = inflater.inflate(R.layout.fragment_foro_interno2, container, false);

        tv_titulo_foro_interno = view.findViewById(R.id.tv_titulo_foro_interno);
        tv_autor_foro_interno = view.findViewById(R.id.tv_autor_foro_interno);
        tv_descripcion_foro_interno = view.findViewById(R.id.tv_descripcion_foro_interno);
        ib_menu_foro = view.findViewById(R.id.ib_menu_foro);
        tv_num_likes_foro = view.findViewById(R.id.tv_num_likes_foro);
        cb_like_foro = view.findViewById(R.id.cb_like_foro);
        et_comentario_foro = view.findViewById(R.id.et_comentario_foro);
        btn_enviar_comentario_foro = view.findViewById(R.id.btn_enviar_comentario_foro);


        // Inflate the layout for this fragment
        return view;
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
