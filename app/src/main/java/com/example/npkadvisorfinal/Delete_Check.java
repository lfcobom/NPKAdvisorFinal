 package com.example.npkadvisorfinal;

 import android.os.Bundle;
 import android.util.Log;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.AdapterView;
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
 import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

 /**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.npkadvisorfinal.Delete_Check#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Delete_Check extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner spinnerdelete1;
    private ImageView btn_delete;
    private String ID;
    private ArrayList<String> cropss = new ArrayList<>();

    public Delete_Check() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Delete_Check.
     */
    // TODO: Rename and change types and number of parameters
    @NonNull
    public static com.example.npkadvisorfinal.Delete_Check newInstance(String param1, String param2) {
        com.example.npkadvisorfinal.Delete_Check fragment = new com.example.npkadvisorfinal.Delete_Check();
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
        View view = inflater.inflate(R.layout.fragment_delete__check, container, false);
        spinnerdelete1 = view.findViewById(R.id.spinnerdelete);
        btn_delete = view.findViewById(R.id.btn_eliminar);
        DeleteCrop();
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletecropdb();
                btn_delete.setEnabled(false);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

     public void DeleteCrop() {
         Call<CropResponse> cropResponseCall = ApiClient.getUserService().findAllC();

         cropResponseCall.enqueue(new Callback<CropResponse>() {
             @Override
             public void onResponse(Call<CropResponse> call, Response<CropResponse> response) {
                 if (response.isSuccessful()) {
                     ArrayList<CropResponse2> cropResponses2 = response.body().getCultivosBuscados();
                     for (int i = 0; i < cropResponses2.size(); i++) {
                         cropss.add(cropResponses2.get(i).getCNombre());
                     }
                     ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, cropss);
                     spinnerdelete1.setAdapter(adaptador);
                     spinnerdelete1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                         @Override
                         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                             ID = cropResponses2.get(position).getId();
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

     public void deletecropdb(){

         Call<CropResponse> cropResponseCall = ApiClient.getUserService().delete(ID);
         ArrayList<String> cropssId = new ArrayList<>();
         cropResponseCall.enqueue(new Callback<CropResponse>() {
             @Override
             public void onResponse(Call<CropResponse> call, Response<CropResponse> response) {
                 if (response.isSuccessful()) {
                     Toast.makeText(getContext(), "Registro Eliminado", Toast.LENGTH_LONG).show();
                 }
             }
             @Override
             public void onFailure(Call<CropResponse> call, Throwable t) {
                 Toast.makeText(getContext(), "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
             }
         });


     }

}