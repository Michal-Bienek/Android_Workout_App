package com.example.workout_appv1.ui.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.helpers.CustomTextWatcher;
import com.example.workout_appv1.ui.adapters.ExerciseParamsAdapter;
import com.example.workout_appv1.viewmodels.DialogAddExerciseToRoutineViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;

public class DialogAddExerciseToRoutine extends DialogFragment implements ExerciseParamsAdapter.OnEtParamsChanged{
    public static final String ARG_ROUTINE_ID = "ROUTINE_ID";
    public static final String ARG_EXERCISE_Name = "EXERCISE_NAME";
    public static final String ARG_EXERCISE_ID = "EXERCISE_ID";
    public static final String ARG_EXERCISE_IN_ROUTINE_ID = "EXERCISE_IN_ROUTINE_ID";
    ConstraintLayout constraintLayout;
    Context context;
    TextView tvExNameDialogAddExercise;
    TextView tvNumberOfSeries;
    FloatingActionButton btnMinusSeries,btnPlusSeries;
    RecyclerView  rvFragmentDialogExercise;
    private DialogAddExerciseToRoutineViewModel viewModel;
    private ExerciseParamsAdapter adapter;
    private Button btnAdd;

    public static DialogAddExerciseToRoutine newAddDialog(int routineId, int exerciseId, String exerciseName){
        DialogAddExerciseToRoutine dialog = new DialogAddExerciseToRoutine();
        Bundle args = new Bundle();
        args.putInt(ARG_ROUTINE_ID,routineId);
        args.putInt(ARG_EXERCISE_ID,exerciseId);
        args.putString(ARG_EXERCISE_Name,exerciseName);
        dialog.setArguments(args);
        return dialog;
    }

    public static DialogAddExerciseToRoutine newEditDialog(int exerciseInRoutineId, String exerciseName){
        DialogAddExerciseToRoutine dialog = new DialogAddExerciseToRoutine();
        Bundle args = new Bundle();
        args.putInt(ARG_EXERCISE_IN_ROUTINE_ID,exerciseInRoutineId);
        args.putString(ARG_EXERCISE_Name,exerciseName);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.dialog_fragment_add_exercise_to_routine,container,false);
        viewModel = new ViewModelProvider(this).get(DialogAddExerciseToRoutineViewModel.class);
       //Initialize views
        tvExNameDialogAddExercise=view.findViewById(R.id.tvExNameDialogAddExercise);
        tvNumberOfSeries=view.findViewById(R.id.tvNumberOfSeries);
        btnMinusSeries=view.findViewById(R.id.fabMinusSeries);
        btnPlusSeries=view.findViewById(R.id.fabPlusSeries);
        rvFragmentDialogExercise= view.findViewById(R.id.rvFragmentDialogExercise);
        Button btnCancel = view.findViewById(R.id.btnCancelDialogAddExerciseWithParams);
        btnAdd = view.findViewById(R.id.btnOkDialogAddExerciseWithParams);
        constraintLayout = view.findViewById(R.id.clAddExerciseToRoutine);

        //Initialize RecyclerView
        LinearLayoutManager layoutManager= new LinearLayoutManager(context);
        adapter = new ExerciseParamsAdapter(context, this);
        this.rvFragmentDialogExercise.setLayoutManager(layoutManager);
        this.rvFragmentDialogExercise.setAdapter(adapter);

        btnCancel.setOnClickListener(view14 -> dismiss());


        Bundle args = getArguments();
        if(args==null){
            dismiss();
        }
        else {
            if (args.containsKey(ARG_ROUTINE_ID)&&args.containsKey(ARG_EXERCISE_Name)&&args.containsKey(ARG_EXERCISE_ID)){
                handleInsertion(args);
            } else if(args.containsKey(ARG_EXERCISE_IN_ROUTINE_ID)&&args.containsKey(ARG_EXERCISE_Name)){
                handleEdit(args);
            }
            else{
                dismiss();
            }
        }
       return view;
    }

    private void handleInsertion(Bundle args){
        int routineId = args.getInt(ARG_ROUTINE_ID);
        int exerciseId = args.getInt(ARG_EXERCISE_ID);
        String exerciseName = args.getString(ARG_EXERCISE_Name);
        tvExNameDialogAddExercise.setText(exerciseName);
        viewModel.getExerciseSeriesList().observe(this, seriesList -> {
            adapter.setSeriesList(seriesList);
            tvNumberOfSeries.setText(String.valueOf(seriesList.size()));
        });
        btnPlusSeries.setOnClickListener(view1 -> viewModel.addSeries(adapter.getSeriesList()));
        btnMinusSeries.setOnClickListener(view12 -> viewModel.removeSeries(adapter.getSeriesList()));
        btnAdd.setOnClickListener(view13 -> {
            viewModel.addExerciseWithParameters(routineId,exerciseId,adapter.getSeriesList());
            dismiss();
        });
    }

    private void handleEdit(Bundle args){
        int exerciseInRoutineId = args.getInt(ARG_EXERCISE_IN_ROUTINE_ID);
        String exerciseName = args.getString(ARG_EXERCISE_Name);
        tvExNameDialogAddExercise.setText(exerciseName);
        btnAdd.setText("Zapisz");
        viewModel.getExerciseSeriesList(exerciseInRoutineId).observe(this, seriesList -> {
            adapter.setSeriesList(seriesList);
            tvNumberOfSeries.setText(String.valueOf(seriesList.size()));
        });
        btnPlusSeries.setOnClickListener(v -> viewModel.addSeries(adapter.getSeriesList()));
        btnMinusSeries.setOnClickListener(view12 -> viewModel.removeSeries(adapter.getSeriesList()));
        btnAdd.setOnClickListener(view13 -> {
            viewModel.editExerciseWithParameters(exerciseInRoutineId,adapter.getSeriesList());
            constraintLayout.requestFocus();
            dismiss();
        });
    }

    public void buildParamDialog(Series series, int position,List<Series>seriesList){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.params_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        //region Views initialization
        TextInputLayout tilReps = dialog.findViewById(R.id.tilRepsParamsDialog);
        TextInputEditText etReps = dialog.findViewById(R.id.etRepsParamDialog);
        TextInputLayout tilWeight = dialog.findViewById(R.id.tilWeightParamsDialog);
        TextInputEditText etWeight = dialog.findViewById(R.id.etWeightParamsDialog);
        TextInputLayout tilRest= dialog.findViewById(R.id.tilRestParamsDialog);
        TextInputEditText etRest = dialog.findViewById(R.id.etRestParamsDialog);
        Button btnOk = dialog.findViewById(R.id.btnOkParamsDialog);
        Button btnCancel = dialog.findViewById(R.id.btnCancelParamsDialog);
        //endregion
        etReps.setText(String.valueOf(series.getReps()));
        etWeight.setText(String.valueOf(series.getWeight()));
        etRest.setText(String.valueOf(series.getRestTime()));
        //region Input fields validation
        etReps.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void onEditTextChanged(Editable s) {
                if(viewModel.validateEtReps(s.toString())){
                    btnOk.setEnabled(true);
                    tilReps.setError(null);
                }
                else{
                    btnOk.setEnabled(false);
                    tilReps.setError("Niepoprawna wartość");
                }
            }
        });
        etWeight.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void onEditTextChanged(Editable s) {
                if(viewModel.validateEtWeight(s.toString())){
                    btnOk.setEnabled(true);
                    tilWeight.setError(null);
                }
                else{
                    btnOk.setEnabled(false);
                    tilWeight.setError("Niepoprawna wartość");
                }
            }
        });
        etRest.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void onEditTextChanged(Editable s) {
                if(viewModel.validateEtReps(s.toString())){
                    btnOk.setEnabled(true);
                    tilRest.setError(null);
                }
                else{
                    btnOk.setEnabled(false);
                    tilRest.setError("Niepoprawna wartość");
                }
            }
        });
        //endregion

        btnCancel.setOnClickListener(v -> {
            btnCancel.setEnabled(false);
            dialog.dismiss();
        });
        btnOk.setOnClickListener(v -> {
            btnOk.setEnabled(false);
            Series new_series = new Series(series);
            new_series.setReps(Integer.parseInt(Objects.requireNonNull(etReps.getText()).toString().trim()));
            new_series.setWeight(Double.parseDouble(Objects.requireNonNull(etWeight.getText()).toString().trim()));
            new_series.setRestTime(Integer.parseInt(Objects.requireNonNull(etRest.getText()).toString().trim()));
            viewModel.updateSeries(seriesList,position,new_series);
            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void onParamsChanged(List<Series> seriesList,int position) {
        buildParamDialog(seriesList.get(position), position,seriesList);
    }
}