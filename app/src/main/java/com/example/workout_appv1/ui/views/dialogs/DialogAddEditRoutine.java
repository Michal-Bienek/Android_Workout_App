package com.example.workout_appv1.ui.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.viewmodels.DialogAddEditRoutineViewModel;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.Objects;

public class DialogAddEditRoutine extends DialogFragment {

    public static final String ARG_ROUTINE_ID = "ROUTINE_ID";
    public static final String ARG_PLAN_ID = "PLAN_ID";
    public DialogAddEditRoutineViewModel viewModel;
    private Context context;
    private TextInputEditText etDialogRoutineName;
    private Spinner spinnerDayOfWeek;
    private TextView tvOkDialogRoutine;

    public DialogAddEditRoutine() {
        // Required empty public constructor
    }

    public static DialogAddEditRoutine newEditInstance(int planId, int routineId) {
        DialogAddEditRoutine fragment = new DialogAddEditRoutine();
        Bundle args = new Bundle();
        args.putInt(ARG_PLAN_ID, planId);
        args.putInt(ARG_ROUTINE_ID, routineId);
        fragment.setArguments(args);
        return fragment;
    }

    public static DialogAddEditRoutine newAddInstance(int planId) {
        DialogAddEditRoutine fragment = new DialogAddEditRoutine();
        Bundle args = new Bundle();
        args.putInt(ARG_PLAN_ID, planId);
        fragment.setArguments(args);
        return fragment;
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
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_add_edit_routine, container, false);
        viewModel = new ViewModelProvider(this).get(DialogAddEditRoutineViewModel.class);

        //Initialize views
        etDialogRoutineName = view.findViewById(R.id.etDialogRoutineName);
        spinnerDayOfWeek = view.findViewById(R.id.spinnerDayOfWeek);
        TextView tvCancelDialogRoutine = view.findViewById(R.id.tvCancelDialogRoutine);
        tvOkDialogRoutine = view.findViewById(R.id.tvOkDialogRoutine);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(context, R.array.day_spinner_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDayOfWeek.setAdapter(spinnerAdapter);
        tvCancelDialogRoutine.setOnClickListener(view1 -> dismiss());

        Bundle arguments = getArguments();
        //Handle edit/add
        if (arguments != null) {
            int planId = arguments.getInt(ARG_PLAN_ID);
            if (arguments.containsKey(ARG_ROUTINE_ID)) {
                int routineId = arguments.getInt(ARG_ROUTINE_ID);
                viewModel.getRoutineById(routineId).observe(getViewLifecycleOwner(), this::editRoutine);
            } else {
                addNewRoutine(planId);
            }
        } else {
            dismiss();
        }
        return view;
    }

    private void addNewRoutine(int planId) {
        tvOkDialogRoutine.setText("DODAJ");
        tvOkDialogRoutine.setOnClickListener(view -> {
            String routineName = Objects.requireNonNull(etDialogRoutineName.getText()).toString().trim();
            int day_of_week = spinnerDayOfWeek.getSelectedItemPosition();
            if (!routineName.equals("")) {
                Routine r = new Routine(routineName, day_of_week, null, planId);
                viewModel.insertRoutine(r);
                dismiss();
            }
        });
    }

    private void editRoutine(Routine routine) {
        etDialogRoutineName.setText(routine.getRoutineName());
        spinnerDayOfWeek.setSelection(routine.getDayOfWeek());
        tvOkDialogRoutine.setText("Edytuj");
        tvOkDialogRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String routineName = Objects.requireNonNull(etDialogRoutineName.getText()).toString().trim();
                int day_of_week = spinnerDayOfWeek.getSelectedItemPosition();
                if (!routineName.equals("")) {
                    routine.setRoutineName(routineName);
                    routine.setDayOfWeek(day_of_week);
                    viewModel.updateRoutine(routine);
                    dismiss();
                }
            }
        });

    }
}