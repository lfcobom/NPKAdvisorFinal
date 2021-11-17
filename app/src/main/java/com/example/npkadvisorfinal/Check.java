package com.example.npkadvisorfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
 * Use the {@link com.example.npkadvisorfinal.Check#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Check extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner spinner;
    private ImageView btn_check;
    public Check() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Check.
     */
    // TODO: Rename and change types and number of parameters
    @NonNull
    public static com.example.npkadvisorfinal.Check newInstance(String param1, String param2) {
        com.example.npkadvisorfinal.Check fragment = new com.example.npkadvisorfinal.Check();
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

        View view = inflater.inflate(R.layout.fragment_check, container, false);
        spinner = view.findViewById(R.id.spinnercheck);
        btn_check = view.findViewById(R.id.btn_check);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), History.class);
                getActivity().startActivity(intent);

            }
        });
        ShowCrop();

        // Inflate the layout for this fragment
        return view;
    }

    public void ShowCrop() {
        Call<CropResponse> cropResponseCall = ApiClient.getUserService().findAllC();
        cropResponseCall.enqueue(new Callback<CropResponse>() {
            @Override
            public void onResponse(Call<CropResponse> call, Response<CropResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<CropResponse2> cropResponses = response.body().getCultivosBuscados();
                    ArrayList<String> cropss = new ArrayList<>();
                    for (int i = 0; i < cropResponses.size(); i++) {
                       // Log.d(TAG, "onResponse: \n " +
                         //       "Cultivo " + cropResponses.get(i).getCNombre());
                       cropss.add(cropResponses.get(i).getCNombre());
                    }
                        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, cropss);
                        spinner.setAdapter(adaptador);
                }
            }
            @Override
            public void onFailure(Call<CropResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
                //System.out.println("causes" + t.fillInStackTrace());
            }
        });
    }
}