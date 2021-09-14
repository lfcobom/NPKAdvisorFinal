package com.example.npkadvisorfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.npkadvisorfinal.add_#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_ extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText cropnombre;
    private EditText croparea;
    private ImageView button;

    public add_() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_.
     */
    // TODO: Rename and change types and number of parameters
    @NonNull
    public static add_ newInstance(String param1, String param2) {
        com.example.npkadvisorfinal.add_ fragment = new com.example.npkadvisorfinal.add_();
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
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        cropnombre = view.findViewById(R.id.cropname);
        croparea = view.findViewById(R.id.croparea);
        button = view.findViewById(R.id.btn_cropadd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCrop(createRequest());
            }
        });

        return view;
    }

    @NonNull
    public CropResponse2 createRequest(){
        CropResponse2 cropRequest = new CropResponse2();
        cropRequest.setCNombre(cropnombre.getText().toString());
        cropRequest.setCArea( Double.parseDouble(croparea.getText().toString()));
        return cropRequest;
    }

    public void saveCrop(CropResponse2 cropRequest) {
                Call<CropResponse> userResponseCall = ApiClient.getUserService().saveCrop(cropRequest);
                userResponseCall.enqueue(new Callback<CropResponse>() {
                    @Override
                    public void onResponse(Call<CropResponse> call, Response<CropResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CropResponse> call, Throwable t) {
                        Toast.makeText(getContext(), "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
