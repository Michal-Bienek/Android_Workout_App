package com.example.workout_appv1.ui.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workout_appv1.R;

public class FragmentExercise extends Fragment {


    public FragmentExercise() {
    }
    public static FragmentExercise newInstance() {
        return new FragmentExercise();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_exercise, container, false);
        return view;
    }
}