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
import com.example.workout_appv1.viewmodels.ProgramViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProgram#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProgram extends Fragment implements ProgramAdapter.OnPlanListener {

    //Declare variables
    Context context;
    ProgramViewModel programViewModel;
    WorkoutPlannerDb database;
    RecyclerView rvProgram;
    FloatingActionButton btnAddPlan;
    LinearLayoutManager layoutManager;
    ProgramAdapter programAdapter;


    public FragmentProgram() {
        // Required empty public constructor
    }

    public static FragmentProgram newInstance() {
        return new FragmentProgram();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
        this.database=WorkoutPlannerDb.getInstance(context);

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
        rvProgram=view.findViewById(R.id.rvProgram);
        btnAddPlan=view.findViewById(R.id.btnAddPlan);

        //Initialize ViewModel
        programViewModel = new ViewModelProvider(this).get(ProgramViewModel.class);

        //Set recyclerView
        programAdapter=new ProgramAdapter(context,this);
        layoutManager=new LinearLayoutManager(context);
        rvProgram.setLayoutManager(layoutManager);
        rvProgram.setAdapter(programAdapter);


        programViewModel.getSortedPlans().observe(getViewLifecycleOwner(), plans -> programAdapter.setPlanList(plans));

        return view;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        navController=NavHostFragment.findNavController(this);
//        setBtnAddPlanListener(view);
//    }
//
//    private void setBtnAddPlanListener(View v){
//        btnAddPlan.setOnClickListener(view -> showAddDialog());
//    }

//    private void showAddDialog(){
//        //Initialize dialog
//        Dialog dialog = new Dialog(context);
//        dialog.setCancelable(true);
//        dialog.setContentView(R.layout.program_dialog);
//        //Initialize Window Manager
//        WindowManager.LayoutParams lp=new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width=WindowManager.LayoutParams.MATCH_PARENT;
//
//        //Initialize dialog Variables
//        EditText etNameProgramDialog=dialog.findViewById(R.id.etNameProgramDialog);
//        EditText etGoalProgramDialog= dialog.findViewById(R.id.etGoalProgramDialog);
//        CheckBox cbProgramDialog=dialog.findViewById(R.id.cbProgramDialog);
//        Button btnCancelDialogProgram=dialog.findViewById(R.id.btnCancelDialogProgram);
//        Button btnAddDialogProgram= dialog.findViewById(R.id.btnAddDialogProgram);
//        btnAddDialogProgram.setOnClickListener(view -> {
//            String name=etNameProgramDialog.getText().toString();
//            String goal=etGoalProgramDialog.getText().toString();
//            if(!name.equals("")&&!goal.equals("")){
//                Plan p=new Plan();
//                p.setPlanName(name);
//                p.setGoal(goal);
//                p.setActive(cbProgramDialog.isChecked());
//                database.planDao().insertPlan(p);
//                planList.clear();
//                //planList.addAll(database.planDao().getSortedPlans());
//                programAdapter.notifyDataSetChanged();
//
//                Toast.makeText(context, "Dodano plan", Toast.LENGTH_SHORT).show();
//            }
//            else{
//                Toast.makeText(context, "Nie dodano planu", Toast.LENGTH_SHORT).show();
//            }
//            dialog.dismiss();
//        });
//        btnCancelDialogProgram.setOnClickListener(view -> dialog.dismiss());
//        dialog.show();
//        dialog.getWindow().setAttributes(lp);
//    }

    @Override
    public void onPlanClick(Plan plan) {
        int planId=plan.getPlanId();
        Toast.makeText(context, ""+planId, Toast.LENGTH_SHORT).show();
        NavController navController = NavHostFragment.findNavController(FragmentProgram.this);
        NavDirections action=FragmentProgramDirections.actionFragmentProgramToFragmentPlan(planId);
        navController.navigate(action);
    }
}