package com.example.workout_appv1.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.data.repositories.RoutineRepository;

public class DialogAddEditRoutineViewModel extends AndroidViewModel {
    private RoutineRepository routineRepository;

    public DialogAddEditRoutineViewModel(@NonNull Application application) {
        super(application);
        routineRepository = new RoutineRepository(application);
    }

    public LiveData<Routine> getRoutineById(int routineId) {
        return routineRepository.getRoutineById(routineId);
    }

    public void updateRoutine(Routine routine) {
        routineRepository.updateRoutine(routine);
    }

    public void insertRoutine(Routine routine) {
        routineRepository.insertRoutine(routine);
    }

    public boolean validateRoutineName(String routineName) {
        return !routineName.isEmpty();
    }
}
