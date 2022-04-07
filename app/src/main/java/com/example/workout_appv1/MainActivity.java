package com.example.workout_appv1;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.workout_appv1.databinding.ActivityMainBinding;
import com.example.workout_appv1.View.fragments.FragmentHome;
import com.example.workout_appv1.View.fragments.FragmentProgram;
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
        navigationView=findViewById(R.id.bottomNavigationView);
        homeFragment=FragmentHome.newInstance();
        programFragment= FragmentProgram.newInstance();
        fragmentManager=getSupportFragmentManager();

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setCurrentFragment(homeFragment);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.miHome:
                    setCurrentFragment(new FragmentHome());
                    break;
                case R.id.miPlan:
                    setCurrentFragment(new FragmentProgram());
                    break;
            }
            return true;
        });

    }
    private void setCurrentFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment,fragment);
        fragmentTransaction.commit();
    }


}