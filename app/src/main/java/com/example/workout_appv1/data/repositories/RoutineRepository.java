package com.example.workout_appv1.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.daos.RoutineDao;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.data.relations.RoutineWithExercisesInRoutine;

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
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> routineDao.insertRoutine(routine));
    }
    public void insertRoutines(List<Routine>routines){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> routineDao.insertRoutines(routines));
    }
    public void updateRoutine(Routine routine){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->routineDao.updateRoutine(routine));
    }
    public void deleteRoutine(Routine routine){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->routineDao.deleteRoutine(routine));
    }
    public LiveData<List<Routine>>getAllRoutines(){
        return routineDao.getAllRoutines();
    }
    public LiveData<List<Routine>>getRoutinesFromActivePlans(){
        return routineDao.getRoutinesFromActivePlans();
    }
    public LiveData<List<Routine>>getAllPlanRoutines(int planId){
        return routineDao.getAllPlanRoutines(planId);
    }
    public LiveData<List<RoutineWithExercisesInRoutine>>getRoutineWithExercisesInRoutineList(int routineId){
        return routineDao.getRoutineWithExercisesInRoutineList(routineId);
    }

    public LiveData<Routine>getRoutineById(int routineId){
        return routineDao.getRoutineById(routineId);
    }
}
