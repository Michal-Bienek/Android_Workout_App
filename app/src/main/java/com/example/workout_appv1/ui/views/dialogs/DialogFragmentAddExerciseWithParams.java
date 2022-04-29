package com.example.workout_appv1.ui.views.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.ui.adapters.ExerciseParamsAdapter;

public class DialogFragmentAddExerciseWithParams extends DialogFragment {
    Context context;
    TextView tvExNameDialogAddExercise;
    EditText etNumberOfSeries;
    ImageView btnMinusSeries,btnPlusSeries;
    RecyclerView  rvFragmentDialogExercise;





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.dialog_fragment_add_exercise_with_params,container,false);
       initViews(view);
       initRecyclerView();

       return view;
    }

    private void initViews(View view){
        tvExNameDialogAddExercise=view.findViewById(R.id.tvExNameDialogAddExercise);
        etNumberOfSeries=view.findViewById(R.id.etNumberOfSeries);
        btnMinusSeries=view.findViewById(R.id.btnMinusSeries);
        btnPlusSeries=view.findViewById(R.id.btnPlusSeries);
        rvFragmentDialogExercise= view.findViewById(R.id.rvFragmentDialogExercise);
    }

    private void initRecyclerView(){
        LinearLayoutManager layoutManager= new LinearLayoutManager(context);
        ExerciseParamsAdapter adapter= new ExerciseParamsAdapter(context);
        this.rvFragmentDialogExercise.setLayoutManager(layoutManager);
        this.rvFragmentDialogExercise.setAdapter(adapter);
    }
}