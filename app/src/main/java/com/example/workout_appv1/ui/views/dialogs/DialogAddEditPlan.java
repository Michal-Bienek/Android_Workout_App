package com.example.workout_appv1.ui.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Plan;
import com.example.workout_appv1.helpers.CustomTextWatcher;
import com.example.workout_appv1.viewmodels.DialogAddEditPlanViewModel;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DialogAddEditPlan extends DialogFragment {
    public static final String PLAN_ID = "PLAN_ID";
    DialogAddEditPlanViewModel viewModel;
    //Declare views
    private TextInputLayout tilName, tilGoal;
    private TextInputEditText etName,etGoal;
    private MaterialCheckBox cbActive;
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
        View view = inflater.inflate(R.layout.dialog_fragment_add_edit_plan, container, false);
        initViews(view);


        //Init ViewModel
        viewModel = new ViewModelProvider(this).get(DialogAddEditPlanViewModel.class);

        if (getArguments() != null) {
            int planId = getArguments().getInt(PLAN_ID);
            viewModel.getPlanById(planId).observe(getViewLifecycleOwner(), this::editPlan);
        } else {

            addPlan();
        }

        etName.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void onEditTextChanged(Editable s) {
                if(s.toString().trim().length()>0){
                    tilName.setError(null);
                }
            }
        });

        btnCancel.setOnClickListener(view1 -> dismiss());
        return view;
    }

    private void editPlan(Plan plan) {
        etName.setText(plan.getPlanName());
        etGoal.setText(plan.getGoal());
        cbActive.setChecked(plan.isActive());
        btnConfirm.setText("Zapisz");
        btnConfirm.setOnClickListener(view -> {
            String name = etName.getText().toString().trim();
            String goal = etGoal.getText().toString().trim();
            boolean isActive = cbActive.isChecked();
            boolean nameValidation = viewModel.validatePlanName(name);
            if(nameValidation){
                plan.setPlanName(name);
                plan.setGoal(goal);
                plan.setActive(isActive);
                viewModel.updatePlan(plan);
                dismiss();
            }
            else{
                tilName.setError("Pole nazwy nie może być puste");
            }
        });

    }

    private void addPlan() {
        btnConfirm.setOnClickListener(view -> {
            String name = etName.getText().toString().trim();
            String goal = etGoal.getText().toString().trim();
            boolean isActive = cbActive.isChecked();
            boolean nameValidation = viewModel.validatePlanName(name);
            if(nameValidation){
                Plan plan = new Plan(name, goal, isActive);
                viewModel.insertPlan(plan);
                dismiss();
            }
            else {
                tilName.setError("Pole nazwy nie może być puste");
            }
        });
    }

    private void initViews(View view){
        this.tilName = view.findViewById(R.id.tilNamePlanDialog);
        this.tilGoal = view.findViewById(R.id.tilGoalPlanDialog);
        this.etName = view.findViewById(R.id.etNamePlanDialog);
        this.etGoal = view.findViewById(R.id.etGoalPlanDialog);
        this.cbActive = view.findViewById(R.id.cbPlanDialog);
        this.btnConfirm = view.findViewById(R.id.btnAddPlanDialog);
        this.btnCancel = view.findViewById(R.id.btnCancelPlanDialog);
    }


}