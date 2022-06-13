package com.example.workout_appv1.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.entities.Plan;
import com.example.workout_appv1.data.repositories.PlanRepository;

import java.util.List;

public class FragmentProgramViewModel extends AndroidViewModel {
    PlanRepository planRepository;

    public FragmentProgramViewModel(@NonNull Application application) {
        super(application);
        planRepository = new PlanRepository(application);
    }

    public void insertPlan(Plan plan) {
        planRepository.insertPlan(plan);
    }

    public void deletePlan(Plan plan) {
        planRepository.deletePlan(plan);
    }

    public void updatePlan(Plan plan) {
        planRepository.updatePlan(plan);
    }

    public LiveData<List<Plan>> getSortedPlans() {
        return planRepository.getSortedPlans();
    }
}
