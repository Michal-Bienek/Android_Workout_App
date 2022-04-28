package com.example.workout_appv1.ui.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Plan;
import com.example.workout_appv1.viewmodels.DialogAddEditPlanViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class DialogAddEditPlan extends DialogFragment {
    public static final String PLAN_ID = "PLAN_ID";
    DialogAddEditPlanViewModel viewModel;
    //Declare views
    private EditText etName;
    private EditText etGoal;
    private CheckBox cbActive;
    private Button btnConfirm, btnCancel;
    private Context context;
    private boolean isEdit = false;


    public DialogAddEditPlan() {
        // Required empty public constructor
    }

    public static DialogAddEditPlan newAddInstance() {
        return new DialogAddEditPlan();
    }

    public static DialogAddEditPlan newEditInstance(int planId) {
        DialogAddEditPlan dialogAddEditPlan = new DialogAddEditPlan();
        Bundle args = new Bundle();
        args.putInt(PLAN_ID, planId);
        dialogAddEditPlan.setArguments(args);
        return dialogAddEditPlan;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_add_edit_plan, container, false);
        this.etName = view.findViewById(R.id.etNamePlanDialog);
        this.etGoal = view.findViewById(R.id.etGoalPlanDialog);
        this.cbActive = view.findViewById(R.id.cbPlanDialog);
        this.btnConfirm = view.findViewById(R.id.btnAddPlanDialog);
        this.btnCancel = view.findViewById(R.id.btnCancelPlanDialog);

        //Init ViewModel
        viewModel = new ViewModelProvider(this).get(DialogAddEditPlanViewModel.class);

        if (getArguments() != null) {
            editPlan();
        } else {
            addPlan();
        }

        btnCancel.setOnClickListener(view1 -> dismiss());
        return view;
    }

    private void editPlan() {

    }

    private void addPlan() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String goal = etGoal.getText().toString().trim();
                boolean isActive = cbActive.isChecked();
                if (!name.equals("")) {
                    Plan plan = new Plan(name, goal, isActive);
                    boolean success = viewModel.insertPlan(plan);
                    dismiss();
                }
            }
        });
    }

}