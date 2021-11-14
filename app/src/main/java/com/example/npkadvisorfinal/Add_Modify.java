package com.example.npkadvisorfinal;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.npkadvisorfinal.Add_Modify#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Add_Modify extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner spinnermodify;
    private ImageButton btn_modify;
    private String ID;
    private String cropname;
    private String croparea;
    private ArrayList<String> cropss = new ArrayList<>();

    public Add_Modify() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Add_Modify.
     */
    // TODO: Rename and change types and number of parameters
    @NonNull
    public static com.example.npkadvisorfinal.Add_Modify newInstance(String param1, String param2) {
        com.example.npkadvisorfinal.Add_Modify fragment = new com.example.npkadvisorfinal.Add_Modify();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add__modify, container, false);


        ShowCrop();
        spinnermodify = view.findViewById(R.id.spinnermodify);
        btn_modify = view.findViewById(R.id.modifyy);
        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enviar(view);

            }
        });
        return view;

    }

    public void ShowCrop() {
        Call<CropResponse> cropResponseCall = ApiClient.getUserService().findAllC();
        cropResponseCall.enqueue(new Callback<CropResponse>() {
            @Override
            public void onResponse(Call<CropResponse> call, Response<CropResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<CropResponse2> cropResponses = response.body().getCultivosBuscados();
                    for (int i = 0; i < cropResponses.size(); i++) {
                        cropss.add(cropResponses.get(i).getCNombre());
                    }
                    ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, cropss);
                    spinnermodify.setAdapter(adaptador);
                    spinnermodify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ID = cropResponses.get(position).getId();
                            cropname = cropResponses.get(position).getCNombre();
                            croparea = cropResponses.get(position).getCArea().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<CropResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
                //System.out.println("causes" + t.fillInStackTrace());
            }
        });
    }
    public void Enviar(View view){
        Intent intent = new Intent(getActivity(), Modify.class);
        intent.putExtra("id", ID);
        intent.putExtra("name", cropname);
        intent.putExtra("area", croparea);
        getActivity().startActivity(intent);
    }



}