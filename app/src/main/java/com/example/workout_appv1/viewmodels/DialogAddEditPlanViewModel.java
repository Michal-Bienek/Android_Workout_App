package com.example.workout_appv1.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.workout_appv1.data.entities.Plan;
import com.example.workout_appv1.data.repositories.PlanRepository;

public class DialogAddEditPlanViewModel extends AndroidViewModel {

    private final PlanRepository planRepository;

    public DialogAddEditPlanViewModel(@NonNull Application application) {
        super(application);
        planRepository = new PlanRepository(application);
    }

    public boolean insertPlan(Plan plan) {
        boolean success = false;
        try {
            planRepository.insertPlan(plan);
            success = true;
        } catch (Exception ignored) {

        }
        return success;
    }

}
