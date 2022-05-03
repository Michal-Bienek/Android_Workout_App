package com.example.workout_appv1.ui.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.ui.adapters.ExerciseParamsAdapter;
import com.example.workout_appv1.viewmodels.DialogAddExerciseToRoutineViewModel;

import java.util.List;

public class DialogAddExerciseToRoutine extends DialogFragment implements ExerciseParamsAdapter.OnEtParamsChanged{
    public static final String ARG_ROUTINE_ID = "ROUTINE_ID";
    public static final String ARG_EXERCISE_Name = "EXERCISE_ID";
    Context context;
    TextView tvExNameDialogAddExercise;
    TextView tvNumberOfSeries;
    ImageView btnMinusSeries,btnPlusSeries;
    RecyclerView  rvFragmentDialogExercise;
    private DialogAddExerciseToRoutineViewModel viewModel;
    private ExerciseParamsAdapter adapter;

    public static DialogAddExerciseToRoutine newAddInstance(int routineId, String exerciseName){
        DialogAddExerciseToRoutine dialog = new DialogAddExerciseToRoutine();
        Bundle args = new Bundle();
        args.putInt(ARG_ROUTINE_ID,routineId);
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
        btnMinusSeries=view.findViewById(R.id.btnMinusSeries);
        btnPlusSeries=view.findViewById(R.id.btnPlusSeries);
        rvFragmentDialogExercise= view.findViewById(R.id.rvFragmentDialogExercise);
        Button btnCancel = view.findViewById(R.id.btnCancelDialogAddExerciseWithParams);

        //Initialize RecyclerView
        LinearLayoutManager layoutManager= new LinearLayoutManager(context);
        adapter = new ExerciseParamsAdapter(context, this);
        this.rvFragmentDialogExercise.setLayoutManager(layoutManager);
        this.rvFragmentDialogExercise.setAdapter(adapter);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        Bundle args = getArguments();
        if(args==null){
            dismiss();
        }
        else {
            if (args.containsKey(ARG_ROUTINE_ID)&&args.containsKey(ARG_EXERCISE_Name)){
                int routineId = args.getInt(ARG_ROUTINE_ID);
                String exerciseName = args.getString(ARG_EXERCISE_Name);
                tvExNameDialogAddExercise.setText(exerciseName);
                viewModel.getExerciseSeriesList().observe(this, new Observer<List<Series>>() {
                    @Override
                    public void onChanged(List<Series> seriesList) {
                        adapter.setSeriesList(seriesList);
                        tvNumberOfSeries.setText(String.valueOf(seriesList.size()));
                    }
                });
                btnPlusSeries.setOnClickListener(view1 -> viewModel.addSeries(adapter.getSeriesList()));
                btnMinusSeries.setOnClickListener(view12 -> viewModel.removeSeries(adapter.getSeriesList()));
            }
            else{
                dismiss();
            }
        }
       return view;
    }

    @Override
    public void onParamsChanged(List<Series> seriesList) {
        viewModel.updateSeries(seriesList);
    }
}