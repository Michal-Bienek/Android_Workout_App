package com.example.workout_appv1.ui.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.entities.Exercise;
import com.example.workout_appv1.ui.adapters.ExerciseAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FragmentExercise extends Fragment implements ExerciseAdapter.OnExerciseListener{

    private int routineId;
    private Context context;
    private FloatingActionButton fabAddExercisesToRoutine;
    private FloatingActionButton fabCreateExercise;
    private RecyclerView rvExercise;
    private List<Exercise> exerciseList;

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
        initRecyclerView();
        handleConfirmClick();
        return view;
    }

    private void initViews(View view){
        this.fabAddExercisesToRoutine=view.findViewById(R.id.fabAddExercisesToRoutine);
        this.fabCreateExercise=view.findViewById(R.id.fabCreateExercise);
        this.rvExercise=view.findViewById(R.id.rvExercise);
    }

    private void initRecyclerView(){
        WorkoutPlannerDb database= WorkoutPlannerDb.getInstance(context);
        this.exerciseList= database.exerciseDao().getAllExercises();
        ExerciseAdapter adapter=new ExerciseAdapter(context,exerciseList,this::onExerciseClick);
        LinearLayoutManager layoutManager= new LinearLayoutManager(context);
        this.rvExercise.setLayoutManager(layoutManager);
        this.rvExercise.setAdapter(adapter);

    }

    private void handleConfirmClick(){
        this.fabAddExercisesToRoutine.setOnClickListener(view -> {
            NavController navController= NavHostFragment.findNavController(this);
            NavDirections action=FragmentExerciseDirections.actionFragmentExerciseToFragmentRoutine(routineId);
            navController.navigate(action);
        });
    }

    @Override
    public void onExerciseClick(int position) {
        Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
        NavController navController=NavHostFragment.findNavController(this);
        NavDirections action= FragmentExerciseDirections.actionFragmentExerciseToDialogFragmentAddExerciseWithParams();
        navController.navigate(action);
    }
}