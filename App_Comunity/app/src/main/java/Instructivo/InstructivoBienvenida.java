package Instructivo;

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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InstructivoBienvenida.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InstructivoBienvenida#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InstructivoBienvenida extends Fragment implements InstructivoOrganiza.OnFragmentInteractionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btn_instructivo_continue;
    private View view;

    private Fragment page_organiza;


    private OnFragmentInteractionListener mListener;

    public InstructivoBienvenida() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InstructivoBienvenida.
     */
    // TODO: Rename and change types and number of parameters
    public static InstructivoBienvenida newInstance(String param1, String param2) {
        InstructivoBienvenida fragment = new InstructivoBienvenida();
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

        view = inflater.inflate(R.layout.fragment_instructivo_bienvenida, container, false);

        btn_instructivo_continue = view.findViewById(R.id.btn_instructivo_continue);

        page_organiza = new InstructivoOrganiza();

        btn_instructivo_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAFrament(page_organiza);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    int time;
    boolean proceso = true;

    public void time() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (proceso) {
                            time++;
                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
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
    public void onFragmentInteraction(Uri uri) {

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

    public void irAFrament(Fragment page){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(
                R.animator.enter_from_right,
                R.animator.exit_to_left,
                R.animator.enter_from_left,
                R.animator.exit_to_right);

        fragmentTransaction.replace(R.id.activity_instructivo, page,null);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }
}
