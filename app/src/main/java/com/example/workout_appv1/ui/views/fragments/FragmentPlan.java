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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.ui.adapters.PlanAdapter;
import com.example.workout_appv1.viewmodels.FragmentPlanViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Objects;

public class FragmentPlan extends Fragment implements PlanAdapter.OnRoutineClickListener {
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
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_plan, container, false);

        //Initialize  ViewModel
        FragmentPlanViewModel viewModel = new ViewModelProvider(this).get(FragmentPlanViewModel.class);

        //Get passed planId
        FragmentPlanArgs args=FragmentPlanArgs.fromBundle(getArguments());
        int planId = args.getPlanId();
        Toast.makeText(context, ""+ planId, Toast.LENGTH_SHORT).show();

        //Initialize views
        RecyclerView rvPlan = view.findViewById(R.id.rvPlan);
        FloatingActionButton btnAddRoutine = view.findViewById(R.id.btnAddRoutine);
        String[] dayShortcuts = getResources().getStringArray(R.array.day_shortcuts_array);

        //Initialize RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        planAdapter=new PlanAdapter(context, dayShortcuts, this);
        rvPlan.setLayoutManager(layoutManager);
        rvPlan.setAdapter(planAdapter);

        //Set observer
        viewModel.getAllPlanRoutines(planId).observe(getViewLifecycleOwner(), routines -> planAdapter.setRoutines(routines));



//        btnAddRoutine.setOnClickListener(view1 -> {
//            showAddDialog();
//        });
        return view;
    }

//    private void showAddDialog(){
//        Dialog dialog=new Dialog(context);
//        dialog.setCancelable(true);
//        dialog.setContentView(R.layout.plan_add_dialog);
//
//        WindowManager.LayoutParams lp= new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width=WindowManager.LayoutParams.MATCH_PARENT;
//
//        TextInputEditText etName=dialog.findViewById(R.id.etAddRoutineName);
//        Spinner spinner=dialog.findViewById(R.id.spinnerDayOfWeek);
//        ImageView btnConfirm=dialog.findViewById(R.id.btnConfirmPlanAddDialog);
//        ImageView btnCancel=dialog.findViewById(R.id.btnCancelPlanAddDialog);
//
//        ArrayAdapter<CharSequence> spinnerAdapter=ArrayAdapter.createFromResource(context,R.array.day_spinner_array, android.R.layout.simple_spinner_item);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(spinnerAdapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) { }
//        });
//
//        btnConfirm.setOnClickListener(view -> {
//            String routineName= Objects.requireNonNull(etName.getText()).toString();
//            if(!routineName.equals("")){
//                Routine r = new Routine();
//                r.setFk_planId(planId);
//                r.setRoutineName(routineName);
//                r.setDayOfWeek(spinner.getSelectedItemPosition());
//                database.routineDao().insertRoutine(r);
//                routineList.clear();
//                //routineList.addAll(database.routineDao().GetAllPlanRoutines(planId));
//                planAdapter.notifyDataSetChanged();
//            }
//            dialog.dismiss();
//
//        });
//        btnCancel.setOnClickListener(view -> {dialog.dismiss();});
//
//        dialog.show();
//        dialog.getWindow().setAttributes(lp);
//    }

    @Override
    public void OnRoutineClick(Routine routine) {
        NavController navController = NavHostFragment.findNavController(FragmentPlan.this);
        NavDirections action= FragmentPlanDirections.actionFragmentPlanToFragmentRoutine(routine.getRoutineId());
        navController.navigate(action);
    }
}