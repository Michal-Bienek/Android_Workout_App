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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.data.entities.RoutineStats;
import com.example.workout_appv1.data.joinEntities.ExerciseInRoutineExercise;
import com.example.workout_appv1.ui.adapters.RoutineAdapter;
import com.example.workout_appv1.ui.views.dialogs.DialogAddExerciseToRoutine;
import com.example.workout_appv1.viewmodels.FragmentRoutineViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class FragmentRoutine extends Fragment {

    //variables
    private int routineId;
    TextView tvLastWorkoutDate, tvTrainingVolume;
    ImageView imgGrow;
    private Context context;
    private Button btnStartWorkout;
    private RecyclerView rvRoutine;
    private FloatingActionButton fabAddExerciseToPlan;


    public FragmentRoutine() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine, container, false);
        initViews(view);
        FragmentRoutineViewModel viewModel = new ViewModelProvider(this).get(FragmentRoutineViewModel.class);
        FragmentRoutineArgs args = FragmentRoutineArgs.fromBundle(getArguments());
        routineId = args.getRoutineId();

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        RoutineAdapter adapter = new RoutineAdapter(context, new RoutineAdapter.IOnExerciseInRoutineAction() {
            @Override
            public void onDelete(ExerciseInRoutineExercise exercise) {
                viewModel.deleteExerciseInRoutineById(exercise.exInRoutineId);
            }

            @Override
            public void onEdit(ExerciseInRoutineExercise exercise) {
                DialogAddExerciseToRoutine dialog = DialogAddExerciseToRoutine.newEditDialog(exercise.exInRoutineId, exercise.exerciseName);
                dialog.show(getChildFragmentManager(), "EditExerciseInRoutine");
            }
        });
        rvRoutine.setLayoutManager(layoutManager);
        rvRoutine.setAdapter(adapter);

        viewModel.getExerciseInRoutineAndExerciseByRoutineId(routineId).observe(getViewLifecycleOwner(), exerciseInRoutineExercises -> {
            btnStartWorkout.setEnabled(exerciseInRoutineExercises.size() > 0);
            adapter.setExerciseInRoutineExerciseList(exerciseInRoutineExercises);
        });

        fabAddExerciseToPlan.setOnClickListener(view1 -> {
            NavController navController = NavHostFragment.findNavController(this);
            NavDirections action = FragmentRoutineDirections.actionFragmentRoutineToFragmentExercise(routineId);
            navController.navigate(action);

        });

        btnStartWorkout.setOnClickListener(view12 -> {
            NavController navController = NavHostFragment.findNavController(this);
            NavDirections action = FragmentRoutineDirections.actionFragmentRoutineToFragmentWorkout(routineId);
            navController.navigate(action);
        });

        viewModel.getAllRoutineStatsById(routineId).observe(getViewLifecycleOwner(), routineStats -> {
            int list_length = routineStats.size();
            if (list_length > 0) {
                RoutineStats rs = routineStats.get(0);
                String volume = rs.getTotal_volume() + " kg";
                tvTrainingVolume.setText(volume);
                tvLastWorkoutDate.setText(viewModel.getConvertedDate(rs.getWorkout_date()));
                if (viewModel.isTrainingVolumeGrowing(routineStats)) {
                    imgGrow.setVisibility(View.VISIBLE);
                } else {
                    imgGrow.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }

    private void initViews(View view) {
        btnStartWorkout = view.findViewById(R.id.btnStartWorkout);
        rvRoutine = view.findViewById(R.id.rvRoutine);
        fabAddExerciseToPlan = view.findViewById(R.id.fabAddExerciseToPlan);
        tvLastWorkoutDate = view.findViewById(R.id.tvLastWorkoutDate);
        tvTrainingVolume = view.findViewById(R.id.tvTrainingVolume);
        imgGrow = view.findViewById(R.id.imgGrowFragmentRoutine);
    }
}