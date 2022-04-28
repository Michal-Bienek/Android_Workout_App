package com.example.workout_appv1.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.daos.PlanDao;
import com.example.workout_appv1.data.entities.Plan;
import com.example.workout_appv1.data.relations.PlanWithRoutines;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlanRepository {
    private PlanDao planDao;
    public PlanRepository(Application application){
        WorkoutPlannerDb database = WorkoutPlannerDb.getInstance(application);
        this.planDao = database.planDao();
    }

    public void insertPlan(Plan plan){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> planDao.insertPlan(plan));
    }
    public void insertPlans(List<Plan>plans){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> planDao.insertPlans(plans));
    }
    public void deletePlan(Plan plan){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> planDao.deletePlan(plan));
    }
    public void updatePlan(Plan plan){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> planDao.updatePlan(plan));
    }
    public LiveData<List<Plan>>getSortedPlans(){
        return this.planDao.getSortedPlans();
    }
    public  LiveData<List<PlanWithRoutines>>getPlanWithRoutines(int planId){
        return this.planDao.getPlanWithRoutines(planId);
    }
    public LiveData<Plan>getPlanById(int planId){
        return this.planDao.getPlanById(planId);
    }


}
