package com.example.workout_appv1.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.workout_appv1.data.entities.Plan;
import com.example.workout_appv1.data.relations.PlanWithRoutines;

import java.util.List;

@Dao
public interface PlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlan(Plan plan);

    @Delete
    void deletePlan(Plan plan);

    @Update
    void updatePlan(Plan plan);

    @Query("SELECT * FROM plans ORDER BY isActive DESC")
    LiveData<List<Plan>> getSortedPlans();

    @Query("SELECT * FROM plans where planId=:planId LIMIT 1")
    LiveData<Plan> getPlanById(int planId);

}
