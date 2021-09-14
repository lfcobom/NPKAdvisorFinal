package com.example.npkadvisorfinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CropsMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CropsMenu extends Fragment {
    ImageView buttonadd, buttonmodify, buttondelete, buttonverify;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CropsMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CropsMenu.
     */
    // TODO: Rename and change types and number of parameters
    public static CropsMenu newInstance(String param1, String param2) {
        CropsMenu fragment = new CropsMenu();
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
        }    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_crops_menu, container, false);
        buttonadd = vista.findViewById(R.id.vercultivos);
        buttonmodify= vista.findViewById(R.id.reginfo);
        buttondelete = vista.findViewById(R.id.maps);
        buttonverify = vista.findViewById(R.id.graphics);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
               // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(R.id.InicialFragment, add_.newInstance("",""));
                // Commit the transaction
                transaction.commit();
            }
        });

        buttonmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(R.id.InicialFragment, Add_Modify.newInstance("",""));
                // Commit the transaction
                transaction.commit();
            }
        });

        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(R.id.InicialFragment, Delete_Check.newInstance("",""));
                // Commit the transaction
                transaction.commit();
            }
        });

        buttonverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(R.id.InicialFragment, Check.newInstance("",""));
                // Commit the transaction
                transaction.commit();
            }
        });

        return vista;
    }
}