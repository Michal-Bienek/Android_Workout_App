package com.example.workout_appv1.ui.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.workout_appv1.R;

public class FragmentExercise extends Fragment {

    private int routineId;
    private Context context;

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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_exercise, container, false);

        FragmentExerciseArgs args=FragmentExerciseArgs.fromBundle(getArguments());
        routineId=args.getRoutineId();
        Toast.makeText(context, ""+routineId, Toast.LENGTH_SHORT).show();


        return view;
    }
}