package com.example.workout_appv1.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.joinEntities.ExerciseInRoutineExercise;
import com.example.workout_appv1.data.repositories.ExerciseInRoutineRepository;

import java.util.List;

public class FragmentRoutineViewModel extends AndroidViewModel {
    private ExerciseInRoutineRepository exerciseInRoutineRepository;
    public FragmentRoutineViewModel(@NonNull Application application) {
        super(application);
        exerciseInRoutineRepository = new ExerciseInRoutineRepository(application);
    }

    public LiveData<List<ExerciseInRoutineExercise>>getExerciseInRoutineAndExerciseByRoutineId(int routineId){
        return exerciseInRoutineRepository.getExerciseInRoutineAndExerciseByRoutineId(routineId);
    }
}
