package com.example.workout_appv1.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.daos.ExerciseDao;
import com.example.workout_appv1.data.entities.Exercise;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExerciseRepository {
    private final ExerciseDao exerciseDao;
    public ExerciseRepository(Application application){
        WorkoutPlannerDb database = WorkoutPlannerDb.getInstance(application);
        exerciseDao = database.exerciseDao();
    }
    public void insertExercise(Exercise exercise){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->exerciseDao.insertExercise(exercise));
    }
    public void insertExercises(List<Exercise> exerciseList){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->exerciseDao.insertExercises(exerciseList));
    }
    public void deleteExercise(Exercise exercise){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->exerciseDao.deleteExercise(exercise));
    }
    public void updateExercise(Exercise exercise){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->exerciseDao.updateExercise(exercise));
    }
    public LiveData<List<Exercise>> getAllExercises(){
        return exerciseDao.getAllExercises();
    }

}
