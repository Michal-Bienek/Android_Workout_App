package com.example.workout_appv1.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.daos.WorkoutParamsDao;
import com.example.workout_appv1.data.entities.WorkoutParams;
import com.example.workout_appv1.data.relations.WorkoutParamsWithSeries;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkoutParamsRepository {
    private WorkoutParamsDao workoutParamsDao;
    public WorkoutParamsRepository(Application application){
        WorkoutPlannerDb database = WorkoutPlannerDb.getInstance(application);
        this.workoutParamsDao = database.workoutParamsDao();
    }

    public void insertWorkoutParams(WorkoutParams workoutParams){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->workoutParamsDao.insertWorkoutParams(workoutParams));
    }
    public void insertWorkoutParamsList(List<WorkoutParams> workoutParamsList){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->workoutParamsDao.insertWorkoutParamsList(workoutParamsList));
    }
    public void deleteWorkoutParams(WorkoutParams workoutParams){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->workoutParamsDao.deleteWorkoutParams(workoutParams));
    }
    public void updateWorkoutParams(WorkoutParams workoutParams){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->workoutParamsDao.updateWorkoutParams(workoutParams));
    }
    public LiveData<List<WorkoutParams>> getAllWorkoutParams(){
        return workoutParamsDao.getAllWorkoutParams();
    }
    public LiveData<List<WorkoutParams>>getWorkoutParamsInExerciseInRoutine(int exerciseInRoutineId){
        return workoutParamsDao.getWorkoutParamsInExerciseInRoutine(exerciseInRoutineId);
    }
    public LiveData<List<WorkoutParams>>getLatestWorkoutParamsInExerciseInRoutine(int exerciseInRoutineId){
        return workoutParamsDao.getLatestWorkoutParamsInExerciseInRoutine(exerciseInRoutineId);
    }
    public LiveData<List<WorkoutParamsWithSeries>>workoutParamsWithSeries(int workoutParamsId){
        return workoutParamsDao.getWorkoutParamsWithSeries(workoutParamsId);
    }
}
