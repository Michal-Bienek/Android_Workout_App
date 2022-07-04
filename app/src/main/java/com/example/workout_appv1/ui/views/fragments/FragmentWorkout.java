package com.example.workout_appv1.ui.views.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.workout_appv1.helpers.CustomTextWatcher;
import com.example.workout_appv1.ui.views.dialogs.DialogAddExerciseToRoutine;
import com.example.workout_appv1.viewmodels.FragmentWorkoutViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FragmentWorkout extends Fragment {

    Context context;
    private int routineId;
    //region View Declaration
    private TextView tvRestTimeFragmentWorkout, tvSeriesCountFragmentWorkout, tvExerciseNameFragmentWorkout;
    private TextInputEditText etRepsFragmentWorkout, etWeightFragmentWorkout;
    private TextInputLayout tilReps, tilWeight;
    private FloatingActionButton btnNextFragmentWorkout, btnPause;
    private ImageView imgExerciseFragmentWorkout;
    private FragmentWorkoutViewModel viewModel;
    private ExerciseWithOneSeries exercise;
    //endregion


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
        addEtWatchers();

        exercise = viewModel.initializeVariables(routineId);
        setViews(exercise);

        viewModel.getRemainingTimeM().observe(getViewLifecycleOwner(), aLong -> {
            if(aLong==0){
                tvRestTimeFragmentWorkout.setTextColor(Color.rgb(220,20,60));
            }
            tvRestTimeFragmentWorkout.setText(String.valueOf(aLong));
        });

        btnNextFragmentWorkout.setOnClickListener(view1 -> {
            if (validateInputs()) {
                String rep = Objects.requireNonNull(etRepsFragmentWorkout.getText()).toString().trim();
                String weight = Objects.requireNonNull(etWeightFragmentWorkout.getText()).toString().trim();
                ExerciseWithOneSeries nextExercise = viewModel.getNextSeries(exercise, rep, weight);
                if (nextExercise == null) {
                    openFinishDialog();
                    btnNextFragmentWorkout.setEnabled(false);
                } else {
                    setExercise(nextExercise);
                }
            }
        });
        btnPause.setOnClickListener(view12 -> {
            btnNextFragmentWorkout.setEnabled(false);
            this.openPauseDialog();

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
        this.btnPause = view.findViewById(R.id.fabPauseWorkout);
        this.imgExerciseFragmentWorkout = view.findViewById(R.id.imgExerciseFragmentWorkout);
        this.tilReps = view.findViewById(R.id.tilRepsFragmentWorkout);
        this.tilWeight = view.findViewById(R.id.tilWeightFragmentWorkout);

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

    private void addEtWatchers() {
        etRepsFragmentWorkout.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void onEditTextChanged(Editable s) {
                String repMessage = viewModel.validateReps(s.toString().trim());
                if (repMessage.equals("")) {
                    tilReps.setError(null);
                    btnNextFragmentWorkout.setEnabled(true);
                } else {
                    tilReps.setError(repMessage);
                    btnNextFragmentWorkout.setEnabled(false);
                }
            }
        });
        etWeightFragmentWorkout.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void onEditTextChanged(Editable s) {
                String weightMessage = viewModel.validateWeight(s.toString().trim());
                if (weightMessage.equals("")) {
                    tilWeight.setError(null);
                    btnNextFragmentWorkout.setEnabled(true);
                } else {
                    tilWeight.setError(weightMessage);
                    btnNextFragmentWorkout.setEnabled(false);
                }
            }
        });
    }

    private boolean validateInputs() {
        String repMessage = viewModel.validateReps(Objects.requireNonNull(etRepsFragmentWorkout.getText()).toString().trim());
        String weightMessage = viewModel.validateWeight(Objects.requireNonNull(etWeightFragmentWorkout.getText()).toString().trim());
        return repMessage.equals("") && weightMessage.equals("");
    }

    private void openFinishDialog() {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.finish_workout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        Button btnOk = dialog.findViewById(R.id.btnAlertFinishWorkout);
        btnOk.setOnClickListener(view -> {
            NavController navController = getNavController();
            NavDirections action = FragmentWorkoutDirections.actionFragmentWorkoutToFragmentRoutine(routineId);
            navController.navigate(action);
            dialog.dismiss();
        });
        dialog.show();
    }

    private void openPauseDialog() {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.break_workout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        Button btnYes = dialog.findViewById(R.id.btnYesBreakWorkout);
        Button btnNo = dialog.findViewById(R.id.btnNoBreakWorkout);

        btnYes.setOnClickListener(view -> {
            viewModel.pauseWorkout();
            NavController navController = getNavController();
            NavDirections action = FragmentWorkoutDirections.actionFragmentWorkoutToFragmentRoutine(routineId);
            navController.navigate(action);
            dialog.dismiss();
        });

        btnNo.setOnClickListener(view -> dialog.dismiss());
        dialog.show();

    }

    private NavController getNavController() {
        return NavHostFragment.findNavController(this);
    }
}