package com.example.workout_appv1.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.entities.Exercise;
import com.example.workout_appv1.data.repositories.ExerciseRepository;

import java.util.List;

public class FragmentExerciseViewModel extends AndroidViewModel {
    private ExerciseRepository exerciseRepository;

    public FragmentExerciseViewModel(@NonNull Application application) {
        super(application);
        this.exerciseRepository = new ExerciseRepository(application);
    }

    public LiveData<List<Exercise>> getAllExercises() {
        return exerciseRepository.getAllExercises();
    }
}
