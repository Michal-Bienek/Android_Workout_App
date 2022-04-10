package com.example.workout_appv1.ui.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.workout_appv1.R;

import android.os.Bundle;
import android.widget.Toast;

public class RoutineActivity extends AppCompatActivity {

    //Variables
    int routineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            routineId=extras.getInt("routineId",0);
            Toast.makeText(this, "routineId: "+routineId, Toast.LENGTH_SHORT).show();
        }
    }
}