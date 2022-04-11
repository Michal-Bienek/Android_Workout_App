package com.example.workout_appv1.ui.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workout_appv1.R;

public class FragmentRoutine extends Fragment {



    public FragmentRoutine() {
        // Required empty public constructor
    }

    public static FragmentRoutine newInstance() {
        FragmentRoutine fragment = new FragmentRoutine();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_routine, container, false);
    }
}