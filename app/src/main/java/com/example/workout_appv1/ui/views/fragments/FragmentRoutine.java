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
import android.widget.Button;
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.joinEntities.ExerciseInRoutineExercise;
import com.example.workout_appv1.ui.adapters.RoutineAdapter;
import com.example.workout_appv1.viewmodels.FragmentRoutineViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FragmentRoutine extends Fragment {

    //variables
    private int routineId;
    private Context context;
    private Button btnStartWorkout;
    private RecyclerView rvRoutine;
    private FloatingActionButton fabAddExerciseToPlan;




    public FragmentRoutine() {
        // Required empty public constructor
    }

    public static FragmentRoutine newInstance() {
        return new FragmentRoutine();
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
        View view= inflater.inflate(R.layout.fragment_routine, container, false);
        FragmentRoutineViewModel viewModel = new ViewModelProvider(this).get(FragmentRoutineViewModel.class);
        FragmentRoutineArgs args= FragmentRoutineArgs.fromBundle(getArguments());
        routineId=args.getRoutineId();


        initViews(view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        RoutineAdapter adapter = new RoutineAdapter(context, new RoutineAdapter.IOnExerciseInRoutine() {
            @Override
            public void onDelete(int exerciseInRoutineId) {
                viewModel.deleteExerciseInRoutineById(exerciseInRoutineId);
            }
        });
        rvRoutine.setLayoutManager(layoutManager);
        rvRoutine.setAdapter(adapter);

        viewModel.getExerciseInRoutineAndExerciseByRoutineId(routineId).observe(getViewLifecycleOwner(), new Observer<List<ExerciseInRoutineExercise>>() {
            @Override
            public void onChanged(List<ExerciseInRoutineExercise> exerciseInRoutineExercises) {
                adapter.setExerciseInRoutineExerciseList(exerciseInRoutineExercises);
                Toast.makeText(context, ""+exerciseInRoutineExercises.size(), Toast.LENGTH_SHORT).show();
            }
        });



        fabAddExerciseToPlan.setOnClickListener(view1 -> {
            NavController navController= NavHostFragment.findNavController(this);
            NavDirections action= FragmentRoutineDirections.actionFragmentRoutineToFragmentExercise(routineId);
            navController.navigate(action);

        });

        return view;
    }


    private void initViews(View view){
        btnStartWorkout=view.findViewById(R.id.btnStartWorkout);
        rvRoutine=view.findViewById(R.id.rvRoutine);
        fabAddExerciseToPlan=view.findViewById(R.id.fabAddExerciseToPlan);
    }
}