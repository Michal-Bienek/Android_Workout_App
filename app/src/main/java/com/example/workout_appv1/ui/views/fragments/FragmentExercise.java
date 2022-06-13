package com.example.workout_appv1.ui.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Exercise;
import com.example.workout_appv1.ui.adapters.ExerciseAdapter;
import com.example.workout_appv1.ui.views.dialogs.DialogAddExerciseToRoutine;
import com.example.workout_appv1.viewmodels.FragmentExerciseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FragmentExercise extends Fragment implements ExerciseAdapter.OnExerciseListener{

    private int routineId;
    private Context context;
    private FloatingActionButton fabAddExercisesToRoutine;
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

        //Initialize ViewModel
        FragmentExerciseViewModel viewModel = new ViewModelProvider(this).get(FragmentExerciseViewModel.class);

        FragmentExerciseArgs args=FragmentExerciseArgs.fromBundle(getArguments());
        routineId=args.getRoutineId();
        initViews(view);
        //Initialize recyclerview
        ExerciseAdapter adapter=new ExerciseAdapter(context, this);
        LinearLayoutManager layoutManager= new LinearLayoutManager(context);
        this.rvExercise.setLayoutManager(layoutManager);
        this.rvExercise.setAdapter(adapter);
        viewModel.getAllExercises().observe(getViewLifecycleOwner(), adapter::setExerciseList);
        this.fabAddExercisesToRoutine.setOnClickListener(view1 -> {
            NavController navController= getNavController();
            NavDirections action = FragmentExerciseDirections.actionFragmentExerciseToFragmentRoutine(routineId);
            navController.navigate(action);
        });


        return view;
    }

    private void initViews(View view){
        this.fabAddExercisesToRoutine=view.findViewById(R.id.fabAddExercisesToRoutine);
        this.rvExercise=view.findViewById(R.id.rvExercise);
    }
    private NavController getNavController(){
        return NavHostFragment.findNavController(this);
    }


    @Override
    public void onExerciseClick(Exercise exercise) {
        DialogAddExerciseToRoutine dialog = DialogAddExerciseToRoutine.newAddDialog(routineId,exercise.getExerciseId(),exercise.getName());
        dialog.show(getChildFragmentManager(),"AddExerciseToRoutine");
    }
}