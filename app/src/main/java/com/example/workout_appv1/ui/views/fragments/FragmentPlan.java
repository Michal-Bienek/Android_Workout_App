package com.example.workout_appv1.ui.views.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.ui.adapters.PlanAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Objects;

public class FragmentPlan extends Fragment implements PlanAdapter.OnRoutineClickListener {
    //Variables
    Context context;
    private int planId;
    private RecyclerView rvPlan;
    private WorkoutPlannerDb database;
    private List<Routine> routineList;
    private String[]dayShortcuts;
    private FloatingActionButton btnAddRoutine;
    private LinearLayoutManager layoutManager;
    private PlanAdapter planAdapter;
    NavController navController;


    public FragmentPlan() {
    }

    public static FragmentPlan newInstance() {
        return new FragmentPlan();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_plan, container, false);

        //Dodać pobieranie id z poprzedniego fragment
//        FragmentPlanArgs args=FragmentPlanArgs.fromBundle(getArguments());
//        planId=args.getPlanId();
//
//        navController= NavHostFragment.findNavController(this);
//
//        initializeVariables(view);
//        initializeRecyclerView();
//        btnAddRoutine.setOnClickListener(view1 -> {
//            showAddDialog();
//        });
        return view;
    }


    private void initializeVariables(View view){
        rvPlan=view.findViewById(R.id.rvPlan);
        btnAddRoutine=view.findViewById(R.id.btnAddRoutine);
        database=WorkoutPlannerDb.getInstance(context);
        //routineList=database.routineDao().GetAllPlanRoutines(planId);
        dayShortcuts=getResources().getStringArray(R.array.day_shortcuts_array);

    }

    private void initializeRecyclerView(){
        layoutManager=new LinearLayoutManager(context);
        planAdapter=new PlanAdapter(routineList,context,dayShortcuts, this);
        rvPlan.setLayoutManager(layoutManager);
        rvPlan.setAdapter(planAdapter);
    }

//    private void showAddDialog(){
//        Dialog dialog=new Dialog(context);
//        dialog.setCancelable(true);
//        dialog.setContentView(R.layout.plan_add_dialog);
//
//        WindowManager.LayoutParams lp= new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width=WindowManager.LayoutParams.MATCH_PARENT;
//
//        TextInputEditText etName=dialog.findViewById(R.id.etAddRoutineName);
//        Spinner spinner=dialog.findViewById(R.id.spinnerDayOfWeek);
//        ImageView btnConfirm=dialog.findViewById(R.id.btnConfirmPlanAddDialog);
//        ImageView btnCancel=dialog.findViewById(R.id.btnCancelPlanAddDialog);
//
//        ArrayAdapter<CharSequence> spinnerAdapter=ArrayAdapter.createFromResource(context,R.array.day_spinner_array, android.R.layout.simple_spinner_item);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(spinnerAdapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) { }
//        });
//
//        btnConfirm.setOnClickListener(view -> {
//            String routineName= Objects.requireNonNull(etName.getText()).toString();
//            if(!routineName.equals("")){
//                Routine r = new Routine();
//                r.setFk_planId(planId);
//                r.setRoutineName(routineName);
//                r.setDayOfWeek(spinner.getSelectedItemPosition());
//                database.routineDao().insertRoutine(r);
//                routineList.clear();
//                //routineList.addAll(database.routineDao().GetAllPlanRoutines(planId));
//                planAdapter.notifyDataSetChanged();
//            }
//            dialog.dismiss();
//
//        });
//        btnCancel.setOnClickListener(view -> {dialog.dismiss();});
//
//        dialog.show();
//        dialog.getWindow().setAttributes(lp);
//    }

    @Override
    public void OnRoutineClick(int position) {
        int routineId=routineList.get(position).getRoutineId();
        NavDirections action= FragmentPlanDirections.actionFragmentPlanToFragmentRoutine(routineId);
        navController.navigate(action);
    }
}