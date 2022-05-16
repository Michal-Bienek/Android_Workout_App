package com.example.workout_appv1.ui.views.fragments;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.workout_appv1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class FragmentWorkout extends Fragment {


    private int routineId;
    private TextView tvRestTimeFragmentWorkout, tvSeriesCountFragmentWorkout, tvExerciseNameFragmentWorkout;
    private EditText etRepsFragmentWorkout, etWeightFragmentWorkout;
    private FloatingActionButton btnNextFragmentWorkout;
    private ImageView imgExerciseFragmentWorkout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        //Get argument
        FragmentWorkoutArgs args = FragmentWorkoutArgs.fromBundle(getArguments());
        routineId = args.getRoutineId();
        //BindViews
        bindViews(view);

        return view;
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
}