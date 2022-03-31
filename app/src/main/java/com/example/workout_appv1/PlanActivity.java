package com.example.workout_appv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import com.example.workout_appv1.adapters.PlanAdapter;
import com.example.workout_appv1.entities.Routine;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

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
        database=WorkoutPlannerDb.getInstance(this);
        routineList=database.routineDao().GetAllPlanRoutines(planId);
        dayShortcuts=getResources().getStringArray(R.array.day_shortcuts_array);


        //Initialize recycler view
        layoutManager=new LinearLayoutManager(this);
        planAdapter= new PlanAdapter(routineList,this,dayShortcuts);
        rvPlan.setLayoutManager(layoutManager);
        rvPlan.setAdapter(planAdapter);



    }
}