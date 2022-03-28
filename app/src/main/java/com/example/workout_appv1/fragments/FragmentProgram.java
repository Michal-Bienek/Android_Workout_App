package com.example.workout_appv1.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.workout_appv1.WorkoutPlannerDb;
import com.example.workout_appv1.adapters.ProgramAdapter;
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
    LinearLayoutManager layoutManager;
    ProgramAdapter programAdapter;


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

        //Set recyclerView
        ProgramAdapter programAdapter=new ProgramAdapter(getActivity(),planList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        rvProgram.setLayoutManager(layoutManager);
        rvProgram.setAdapter(programAdapter);


        //Set listener on button add

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBtnAddPlanListener(view);
    }

    private void setBtnAddPlanListener(View v){
        btnAddPlan.setOnClickListener(view -> showCustomDialog());
    }

    private void showCustomDialog(){
        //Initialize dialog
        Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.program_dialog);
        //Initialize Window Manager
        WindowManager.LayoutParams lp=new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width=WindowManager.LayoutParams.MATCH_PARENT;

        //Initialize dialog Variables
        EditText etNameProgramDialog=dialog.findViewById(R.id.etNameProgramDialog);
        EditText etGoalProgramDialog= dialog.findViewById(R.id.etGoalProgramDialog);
        CheckBox cbProgramDialog=dialog.findViewById(R.id.cbProgramDialog);
        Button btnCancelDialogProgram=dialog.findViewById(R.id.btnCancelDialogProgram);
        Button btnAddDialogProgram= dialog.findViewById(R.id.btnAddDialogProgram);
        btnAddDialogProgram.setOnClickListener(view -> {
            String name=etNameProgramDialog.getText().toString();
            String goal=etGoalProgramDialog.getText().toString();
            if(!name.equals("")&&!goal.equals("")){
                Plan p=new Plan();
                p.setPlanName(name);
                p.setGoal(goal);
                p.setActive(cbProgramDialog.isChecked());
                database.planDao().insertPlan(p);
                Toast.makeText(getActivity(), "Dodano plan", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getActivity(), "Nie dodano planu", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });
        btnCancelDialogProgram.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

}