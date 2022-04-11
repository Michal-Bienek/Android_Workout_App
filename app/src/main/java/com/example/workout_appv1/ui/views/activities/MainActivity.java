package com.example.workout_appv1.ui.views.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.workout_appv1.R;
import com.example.workout_appv1.databinding.ActivityMainBinding;
import com.example.workout_appv1.ui.views.fragments.FragmentHome;
import com.example.workout_appv1.ui.views.fragments.FragmentProgram;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    Fragment homeFragment,programFragment;
    FragmentManager fragmentManager;
    ActivityMainBinding binding;

    //texts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize variables

    }
}