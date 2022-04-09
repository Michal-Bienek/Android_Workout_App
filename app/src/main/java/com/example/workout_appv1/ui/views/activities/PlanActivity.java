package com.example.workout_appv1.ui.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.example.workout_appv1.ui.adapters.PlanAdapter;
import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.entities.Routine;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Objects;

public class PlanActivity extends AppCompatActivity {
    private int planId;
    private RecyclerView rvPlan;
    private WorkoutPlannerDb database;
    private List<Routine> routineList;
    private String[]dayShortcuts;
    private FloatingActionButton btnAddRoutine;
    private LinearLayoutManager layoutManager;
    private PlanAdapter planAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            planId=extras.getInt("planId",0);
            Toast.makeText(this, "planId: "+planId, Toast.LENGTH_SHORT).show();
        }

        //Initialize variables
        rvPlan=findViewById(R.id.rvPlan);
        btnAddRoutine=findViewById(R.id.btnAddRoutine);
        database= WorkoutPlannerDb.getInstance(this);
        routineList=database.routineDao().GetAllPlanRoutines(planId);
        dayShortcuts=getResources().getStringArray(R.array.day_shortcuts_array);


        //Initialize recycler view
        layoutManager=new LinearLayoutManager(this);
        planAdapter= new PlanAdapter(routineList,this,dayShortcuts);
        rvPlan.setLayoutManager(layoutManager);
        rvPlan.setAdapter(planAdapter);

        //Set listener on btnAddRoutine
        btnAddRoutine.setOnClickListener(view -> {
            showAddDialog(PlanActivity.this);
        });

    }

    private void showAddDialog(Context context){
        Dialog dialog=new Dialog(context);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.plan_add_dialog);

        WindowManager.LayoutParams lp= new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width=WindowManager.LayoutParams.MATCH_PARENT;

        TextInputEditText etName=dialog.findViewById(R.id.etAddRoutineName);
        Spinner spinner=dialog.findViewById(R.id.spinnerDayOfWeek);
        ImageView btnConfirm=dialog.findViewById(R.id.btnConfirmPlanAddDialog);
        ImageView btnCancel=dialog.findViewById(R.id.btnCancelPlanAddDialog);

        ArrayAdapter<CharSequence>spinnerAdapter=ArrayAdapter.createFromResource(context,R.array.day_spinner_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        btnConfirm.setOnClickListener(view -> {
            String routineName= Objects.requireNonNull(etName.getText()).toString();
            if(!routineName.equals("")){
                Routine r = new Routine();
                r.setFk_planId(planId);
                r.setRoutineName(routineName);
                r.setDayOfWeek(spinner.getSelectedItemPosition());
                database.routineDao().insertRoutine(r);
                routineList.clear();
                routineList.addAll(database.routineDao().GetAllPlanRoutines(planId));
                planAdapter.notifyDataSetChanged();
            }
            dialog.dismiss();

        });
        btnCancel.setOnClickListener(view -> {dialog.dismiss();});

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }



}