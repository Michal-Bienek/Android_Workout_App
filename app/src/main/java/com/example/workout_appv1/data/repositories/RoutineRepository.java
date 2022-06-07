package com.example.workout_appv1.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.daos.RoutineDao;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.data.relations.RoutineWithExercisesInRoutine;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoutineRepository {
    private final RoutineDao routineDao;
    public RoutineRepository(Application application){
        WorkoutPlannerDb database = WorkoutPlannerDb.getInstance(application);
        this.routineDao = database.routineDao();
    }

    public void insertRoutine(Routine routine){
        WorkoutPlannerDb.databaseWriteExecutor.execute(() -> routineDao.insertRoutine(routine));
    }
    public void updateRoutine(Routine routine){
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->routineDao.updateRoutine(routine));
    }
    public void deleteRoutine(Routine routine){
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->routineDao.deleteRoutine(routine));
    }
    public LiveData<List<Routine>>getRoutinesFromActivePlans(){
        return routineDao.getRoutinesFromActivePlans();
    }
    public LiveData<List<Routine>>getAllPlanRoutines(int planId){
        return routineDao.getAllPlanRoutines(planId);
    }

    public LiveData<Routine>getRoutineById(int routineId){
        return routineDao.getRoutineById(routineId);
    }

    public void updateRoutineById(int routineId, Date date){
        WorkoutPlannerDb.databaseWriteExecutor.execute(() -> routineDao.updateRoutineById(routineId,date));
    }
}
