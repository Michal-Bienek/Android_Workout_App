package com.example.workout_appv1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.example.workout_appv1.WorkoutPlannerDb;
import com.example.workout_appv1.entities.Plan;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProgram#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProgram extends Fragment {

    //Declare variables
    WorkoutPlannerDb database;
    List<Plan> planList=new ArrayList<>();
    RecyclerView rvProgram;
    Button btnAddPlan;


    public FragmentProgram() {
        // Required empty public constructor
    }

    public static FragmentProgram newInstance() {
        return new FragmentProgram();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_program, container, false);

        //Initialize variables
        database=WorkoutPlannerDb.getInstance(getActivity());
        planList=database.planDao().getSortedPlans();
        rvProgram=view.findViewById(R.id.rvProgram);
        btnAddPlan=view.findViewById(R.id.btnAddPlan);
        setBtnAddPlanListener();
        return view;
    }
    
    private void setBtnAddPlanListener(){
        btnAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Kliknięto", Toast.LENGTH_SHORT).show();
            }
        });
    }
}