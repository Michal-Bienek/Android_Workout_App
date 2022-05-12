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
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->exerciseDao.insertExercise(exercise));
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(()->exerciseDao.insertExercise(exercise));
//        executorService.shutdown();
    }
    public void insertExercises(List<Exercise> exerciseList){
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->exerciseDao.insertExercises(exerciseList));
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(()->exerciseDao.insertExercises(exerciseList));
//        executorService.shutdown();
    }
    public void deleteExercise(Exercise exercise){
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->exerciseDao.deleteExercise(exercise));
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(()->exerciseDao.deleteExercise(exercise));
//        executorService.shutdown();
    }
    public void updateExercise(Exercise exercise){
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->exerciseDao.updateExercise(exercise));
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(()->exerciseDao.updateExercise(exercise));
//        executorService.shutdown();
    }
    public LiveData<List<Exercise>> getAllExercises(){
        return exerciseDao.getAllExercises();
    }

}
