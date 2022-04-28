package com.example.workout_appv1.ui.views.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.ui.adapters.ProgramAdapter;
import com.example.workout_appv1.data.entities.Plan;
import com.example.workout_appv1.ui.views.dialogs.DialogAddEditPlan;
import com.example.workout_appv1.viewmodels.ProgramViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FragmentProgram extends Fragment implements ProgramAdapter.OnPlanListener {

    //Declare variables
    Context context;
    ProgramViewModel programViewModel;
    RecyclerView rvProgram;
    FloatingActionButton btnAddPlan;
    LinearLayoutManager layoutManager;
    ProgramAdapter programAdapter;


    public FragmentProgram() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_program, container, false);
        //Initialize variables
        rvProgram = view.findViewById(R.id.rvProgram);
        btnAddPlan = view.findViewById(R.id.btnAddPlan);

        //Initialize ViewModel
        programViewModel = new ViewModelProvider(this).get(ProgramViewModel.class);

        //Set recyclerView
        programAdapter = new ProgramAdapter(context, this);
        layoutManager = new LinearLayoutManager(context);
        rvProgram.setLayoutManager(layoutManager);
        rvProgram.setAdapter(programAdapter);


        programViewModel.getSortedPlans().observe(getViewLifecycleOwner(), plans -> programAdapter.setPlanList(plans));
        btnAddPlan.setOnClickListener(view1 -> showAddDialog());

        return view;
    }

    private void showAddDialog(){
        DialogAddEditPlan dialog = DialogAddEditPlan.newAddInstance();
        dialog.show(getChildFragmentManager(),"DialogAddEditPlan");
    }

    @Override
    public void onPlanClick(Plan plan) {
        int planId = plan.getPlanId();
        Toast.makeText(context, "" + planId, Toast.LENGTH_SHORT).show();
        NavController navController = NavHostFragment.findNavController(FragmentProgram.this);
        NavDirections action = FragmentProgramDirections.actionFragmentProgramToFragmentPlan(planId);
        navController.navigate(action);
    }
}