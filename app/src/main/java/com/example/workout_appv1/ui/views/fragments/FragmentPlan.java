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
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.ui.adapters.PlanAdapter;
import com.example.workout_appv1.ui.views.dialogs.DialogAddEditRoutine;
import com.example.workout_appv1.viewmodels.FragmentPlanViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentPlan extends Fragment {
    //Variables
    Context context;
    private PlanAdapter planAdapter;


    public FragmentPlan() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);

        FragmentPlanViewModel viewModel = new ViewModelProvider(this).get(FragmentPlanViewModel.class);

        FragmentPlanArgs args = FragmentPlanArgs.fromBundle(getArguments());
        int planId = args.getPlanId();

        RecyclerView rvPlan = view.findViewById(R.id.rvPlan);
        FloatingActionButton btnAddRoutine = view.findViewById(R.id.btnAddRoutine);

        //Initialize RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        planAdapter = new PlanAdapter(context, new PlanAdapter.IOnRoutineAction() {
            @Override
            public void onRoutineClick(Routine routine) {
                NavController navController = getNavController();
                NavDirections action = FragmentPlanDirections.actionFragmentPlanToFragmentRoutine(routine.getRoutineId());
                navController.navigate(action);
            }

            @Override
            public void onRoutineEdit(Routine routine) {
                DialogAddEditRoutine dialog = DialogAddEditRoutine.newEditInstance(routine.getFk_planId(), routine.getRoutineId());
                dialog.show(getChildFragmentManager(), "EditRoutineDialog");
            }

            @Override
            public void onRoutineDelete(Routine routine) {
                viewModel.deleteRoutine(routine);
            }

            @Override
            public String getDayShortcut(Routine routine) {
                return viewModel.getDayShortcut(routine.getDayOfWeek());
            }
        });
        rvPlan.setLayoutManager(layoutManager);
        rvPlan.setAdapter(planAdapter);

        //Set observer
        viewModel.getAllPlanRoutines(planId).observe(getViewLifecycleOwner(), routines -> planAdapter.setRoutines(routines));


        btnAddRoutine.setOnClickListener(view1 -> showDialog(planId));
        return view;
    }

    private void showDialog(int planId) {
        DialogAddEditRoutine dialogAddEditRoutine = DialogAddEditRoutine.newAddInstance(planId);
        dialogAddEditRoutine.show(getChildFragmentManager(), "AddRoutine");
    }

    private NavController getNavController() {
        return NavHostFragment.findNavController(this);
    }
}