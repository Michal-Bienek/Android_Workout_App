package com.example.workout_appv1.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.data.repositories.PlanRepository;
import com.example.workout_appv1.data.repositories.RoutineRepository;

import java.util.List;

public class FragmentPlanViewModel extends AndroidViewModel {
    private RoutineRepository routineRepository;

    public FragmentPlanViewModel(@NonNull Application application) {
        super(application);
        this.routineRepository = new RoutineRepository(application);
    }

    public LiveData<List<Routine>> getAllPlanRoutines(int planId){
        return routineRepository.getAllPlanRoutines(planId);
    }
    public void deleteRoutine(Routine routine){
        routineRepository.deleteRoutine(routine);
    }
}
