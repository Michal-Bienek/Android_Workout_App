package com.example.workout_appv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.workout_appv1.entities.Plan;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WorkoutPlannerDb workoutPlannerDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        workoutPlannerDb=WorkoutPlannerDb.getInstance(MainActivity.this);
    }
}