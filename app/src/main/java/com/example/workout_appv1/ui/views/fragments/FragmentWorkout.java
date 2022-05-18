package com.example.workout_appv1.ui.views.fragments;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.daos.ExercisesInRoutineDao;
import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.data.joinEntities.ExerciseInRoutineWorkoutParams;
import com.example.workout_appv1.data.joinEntities.ExerciseWithOneSeries;
import com.example.workout_appv1.data.joinEntities.ExerciseWithSeries;
import com.example.workout_appv1.viewmodels.FragmentWorkoutViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

public class FragmentWorkout extends Fragment {

    Context context;
    private int routineId;
    private TextView tvRestTimeFragmentWorkout, tvSeriesCountFragmentWorkout, tvExerciseNameFragmentWorkout;
    private EditText etRepsFragmentWorkout, etWeightFragmentWorkout;
    private FloatingActionButton btnNextFragmentWorkout;
    private ImageView imgExerciseFragmentWorkout;
    private FragmentWorkoutViewModel viewModel;
    private ExerciseWithOneSeries exercise;


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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        //Initialize viewmodel
        viewModel = new ViewModelProvider(this).get(FragmentWorkoutViewModel.class);
        //Get argument
        FragmentWorkoutArgs args = FragmentWorkoutArgs.fromBundle(getArguments());
        routineId = args.getRoutineId();
        //BindViews
        bindViews(view);

        exercise = viewModel.initializeVariables(routineId);
        setViews(exercise);

        btnNextFragmentWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExerciseWithOneSeries nextExercise = viewModel.getNextSeries(exercise);
                if (nextExercise == null) {
                    Toast.makeText(context, "ni ma wiyncyj ćwiczeń", Toast.LENGTH_SHORT).show();
                } else {
                    setExercise(nextExercise);
                }
            }
        });

        return view;
    }

    private void setExercise(ExerciseWithOneSeries newExercise) {
        this.exercise = newExercise;
        setViews(exercise);
    }

    private void bindViews(View view) {
        this.tvRestTimeFragmentWorkout = view.findViewById(R.id.tvRestTimeFragmentWorkout);
        this.tvSeriesCountFragmentWorkout = view.findViewById(R.id.tvSeriesCountFragmentWorkout);
        this.tvExerciseNameFragmentWorkout = view.findViewById(R.id.tvExerciseNameFragmentWorkout);
        this.etRepsFragmentWorkout = view.findViewById(R.id.etRepsFragmentWorkout);
        this.etWeightFragmentWorkout = view.findViewById(R.id.etWeightFragmentWorkout);
        this.btnNextFragmentWorkout = view.findViewById(R.id.btnNextFragmentWorkout);
        this.imgExerciseFragmentWorkout = view.findViewById(R.id.imgExerciseFragmentWorkout);

    }

    private void setViews(ExerciseWithOneSeries exercise) {
        this.tvRestTimeFragmentWorkout.setText(String.valueOf(exercise.getSeries().getRestTime()));
        int seriesPosition = exercise.getSeriesPosition() + 1;
        String seriesCounter = seriesPosition + "/" + exercise.getSeries_Count();
        this.tvSeriesCountFragmentWorkout.setText(seriesCounter);
        this.tvExerciseNameFragmentWorkout.setText(exercise.getExercise().getName());
        this.etRepsFragmentWorkout.setText(String.valueOf(exercise.getSeries().getReps()));
        this.etWeightFragmentWorkout.setText(String.valueOf(exercise.getSeries().getWeight()));
    }
}