package com.example.workout_appv1.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.entities.Plan;
import com.example.workout_appv1.data.repositories.PlanRepository;

public class DialogAddEditPlanViewModel extends AndroidViewModel {

    private final PlanRepository planRepository;

    public DialogAddEditPlanViewModel(@NonNull Application application) {
        super(application);
        planRepository = new PlanRepository(application);
    }

    public void insertPlan(Plan plan) {
        planRepository.insertPlan(plan);
    }

    public LiveData<Plan> getPlanById(int planId) {
        return planRepository.getPlanById(planId);
    }

    public void updatePlan(Plan plan) {
        planRepository.updatePlan(plan);
    }

    public boolean validatePlanName(String planName) {
        return !planName.isEmpty();
    }

}
