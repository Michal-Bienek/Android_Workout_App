package com.example.workout_appv1.ui.views.dialogs;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workout_appv1.R;

public class DialogAddEditRoutine extends DialogFragment {

    public static final String ARG_ROUTINE_ID = "ROUTINE_ID";

    public DialogAddEditRoutine() {
        // Required empty public constructor
    }

    public static DialogAddEditRoutine newInstance(int routineId) {
        DialogAddEditRoutine fragment = new DialogAddEditRoutine();
        Bundle args = new Bundle();
        args.putInt(ARG_ROUTINE_ID, routineId);
        fragment.setArguments(args);
        return fragment;
    }

    public static DialogAddEditRoutine newAddInstance(){
        return new DialogAddEditRoutine();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dialog_add_edit_routine, container, false);

        //Initialize views


        //Handle edit/add
        if(getArguments()!=null){
            int routineId = getArguments().getInt(ARG_ROUTINE_ID);
            editRoutine(routineId);
        }
        else{
            addNewRoutine();
        }


        return view;
    }

    private void addNewRoutine(){}
    private void editRoutine(int routineId){}
}