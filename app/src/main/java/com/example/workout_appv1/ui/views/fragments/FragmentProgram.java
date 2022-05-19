package com.example.workout_appv1.ui.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workout_appv1.R;
import com.example.workout_appv1.ui.adapters.ProgramAdapter;
import com.example.workout_appv1.data.entities.Plan;
import com.example.workout_appv1.ui.views.dialogs.DialogAddEditPlan;
import com.example.workout_appv1.viewmodels.FragmentProgramViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentProgram extends Fragment {

    //Declare variables
    Context context;
    FragmentProgramViewModel fragmentProgramViewModel;
    RecyclerView rvProgram;
    FloatingActionButton btnAddPlan;
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
        fragmentProgramViewModel = new ViewModelProvider(this).get(FragmentProgramViewModel.class);

        //Set recyclerView
        programAdapter = new ProgramAdapter(context, new ProgramAdapter.IOnPlanListener() {
            @Override
            public void onPlanClick(Plan plan) {
                NavController navController = getNavController();
                NavDirections action = FragmentProgramDirections.actionFragmentProgramToFragmentPlan(plan.getPlanId());
                navController.navigate(action);
            }

            @Override
            public void onPlanDelete(Plan plan) {
                fragmentProgramViewModel.deletePlan(plan);

            }

            @Override
            public void onEditPlan(Plan plan) {
                DialogAddEditPlan dialogAddEditPlan = DialogAddEditPlan.newEditInstance(plan.getPlanId());
                dialogAddEditPlan.show(getChildFragmentManager(), "DialogEditPlan");

            }
        });
        rvProgram.setLayoutManager(new LinearLayoutManager(context));
        rvProgram.setAdapter(programAdapter);
        fragmentProgramViewModel.getSortedPlans().observe(getViewLifecycleOwner(), plans -> programAdapter.setPlanList(plans));
        btnAddPlan.setOnClickListener(view1 -> showAddDialog());
        return view;
    }

    private void showAddDialog() {
        DialogAddEditPlan dialog = DialogAddEditPlan.newAddInstance();
        dialog.show(getChildFragmentManager(), "DialogAddEditPlan");
    }

    private NavController getNavController() {
        return NavHostFragment.findNavController(this);
    }
}