package com.example.workout_appv1.ui.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentExercise extends Fragment {

    private int routineId;
    private Context context;
    private FloatingActionButton fabAddExercisesToRoutine;
    private FloatingActionButton fabCreateExercise;
    private RecyclerView rvExercise;

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
        initViews(view);
        handleConfirmClick();
        return view;
    }

    private void initViews(View view){
        this.fabAddExercisesToRoutine=view.findViewById(R.id.fabAddExercisesToRoutine);
        this.fabCreateExercise=view.findViewById(R.id.fabCreateExercise);
        this.rvExercise=view.findViewById(R.id.rvExercise);
    }

    private void handleConfirmClick(){
        this.fabAddExercisesToRoutine.setOnClickListener(view -> {
            NavController navController= NavHostFragment.findNavController(this);
            NavDirections action=FragmentExerciseDirections.actionFragmentExerciseToFragmentRoutine(routineId);
            navController.navigate(action);
        });
    }
}