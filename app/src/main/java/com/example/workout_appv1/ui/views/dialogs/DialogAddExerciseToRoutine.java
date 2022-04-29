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

public class DialogAddExerciseToRoutine extends DialogFragment {
    public static final String ARG_ROUTINE_ID = "ROUTINE_ID";
    public static final String ARG_EXERCISE_ID = "EXERCISE_ID";
    Context context;
    TextView tvExNameDialogAddExercise;
    EditText etNumberOfSeries;
    ImageView btnMinusSeries,btnPlusSeries;
    RecyclerView  rvFragmentDialogExercise;

    public static DialogAddExerciseToRoutine newAddInstance(int routineId, int exerciseId){
        DialogAddExerciseToRoutine dialog = new DialogAddExerciseToRoutine();
        Bundle args = new Bundle();
        args.putInt(ARG_ROUTINE_ID,routineId);
        args.putInt(ARG_EXERCISE_ID,exerciseId);
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.dialog_fragment_add_exercise_to_routine,container,false);
       initViews(view);
       //initRecyclerView();
        Bundle args = getArguments();
        if(args==null){
            dismiss();
        }
        else {
            if (args.containsKey(ARG_ROUTINE_ID)&&args.containsKey(ARG_EXERCISE_ID)){
                int routineId = args.getInt(ARG_ROUTINE_ID);
                int exerciseId = args.getInt(ARG_EXERCISE_ID);
            }
            else{
                dismiss();
            }
        }



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