package com.example.workout_appv1.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.daos.ExercisesInRoutineDao;
import com.example.workout_appv1.data.entities.ExercisesInRoutine;
import com.example.workout_appv1.data.relations.ExercisesInRoutineWithWorkoutParams;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExerciseInRoutineRepository {
    private final ExercisesInRoutineDao exercisesInRoutineDao;
    public ExerciseInRoutineRepository(Application application){
        WorkoutPlannerDb database = WorkoutPlannerDb.getInstance(application);
        this.exercisesInRoutineDao = database.exercisesInRoutineDao();
    }

    public void insertExerciseInRoutine(ExercisesInRoutine exercisesInRoutine){
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->this.exercisesInRoutineDao.insertExerciseInRoutine(exercisesInRoutine));
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(()->this.exercisesInRoutineDao.insertExerciseInRoutine(exercisesInRoutine));
//
////        Future<Long>future = executorService.submit(()->this.exercisesInRoutineDao.insertExerciseInRoutine(exercisesInRoutine));
//        Future<Long> submit = executorService.submit((Callable<Long>) () -> this.exercisesInRoutineDao.insertExerciseInRoutine(exercisesInRoutine));
//        executorService.shutdown();

    }

    public void insertExerciseInRoutineList(List<ExercisesInRoutine> exercisesInRoutineList){
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->this.exercisesInRoutineDao.insertExerciseInRoutineList(exercisesInRoutineList));
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(()->this.exercisesInRoutineDao.insertExerciseInRoutineList(exercisesInRoutineList));
//        executorService.shutdown();
    }

    public void deleteExerciseInRoutine(ExercisesInRoutine exercisesInRoutine){
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->this.exercisesInRoutineDao.deleteExerciseInRoutine(exercisesInRoutine));
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(()->this.exercisesInRoutineDao.deleteExerciseInRoutine(exercisesInRoutine));
//        executorService.shutdown();

    }

    public void updateExerciseInRoutine(ExercisesInRoutine exercisesInRoutine){
        WorkoutPlannerDb.databaseWriteExecutor.execute(() -> exercisesInRoutineDao.updateExerciseInRoutine(exercisesInRoutine));
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(() -> exercisesInRoutineDao.updateExerciseInRoutine(exercisesInRoutine));
//        executorService.shutdown();
    }

    public LiveData<List<ExercisesInRoutine>> getAllExercisesInRoutine(){
        return this.exercisesInRoutineDao.getAllExercisesInRoutine();
    }

    public  LiveData<List<ExercisesInRoutine>>getExercisesInRoutineByRoutineId(int routineId){
        return this.exercisesInRoutineDao.getExercisesInRoutineByRoutineId(routineId);
    }

    public LiveData<List<ExercisesInRoutine>>getExercisesInRoutineByExerciseId(int exerciseId){
        return this.exercisesInRoutineDao.getExercisesInRoutineByExerciseId(exerciseId);
    }

    public LiveData<List<ExercisesInRoutineWithWorkoutParams>>exercisesWithParams(int exerciseInRoutineId){
        return this.exercisesInRoutineDao.getExercisesWithParams(exerciseInRoutineId);
    }

}
