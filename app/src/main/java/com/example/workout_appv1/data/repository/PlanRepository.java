package com.example.workout_appv1.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.daos.PlanDao;
import com.example.workout_appv1.data.entities.Plan;

import java.util.List;

public class PlanRepository {
    private PlanDao planDao;
    private LiveData<List<Plan>> sortedPlans;
    public PlanRepository(Application application){
        WorkoutPlannerDb database = WorkoutPlannerDb.getInstance(application);
        planDao = database.planDao();
        sortedPlans = planDao.getSortedPlans();
    }
    public void insertPlan(Plan p){
        planDao.insertPlan(p);
    }
    public void insertPlans(List<Plan>planList){
        planDao.insertPlans(planList);
    }
    public void deletePlan(Plan plan){
        planDao.deletePlan(plan);
    }
    public void updatePlan(Plan plan){
        planDao.updatePlan(plan);
    }
    public void updatePlanStatus(int id, boolean status){
        planDao.updatePlanStatus(id,status);
    }
    public LiveData<List<Plan>>getSortedPlans(){
        return sortedPlans;
    }




}
