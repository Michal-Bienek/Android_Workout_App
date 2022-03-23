package com.example.workout_appv1.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.workout_appv1.entities.Plan;

import java.util.List;

@Dao
public interface PlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlan(Plan plan);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlans(List<Plan> plans);

    @Delete
    void deletePlan(Plan plan);

    @Update
    void updatePlan(Plan plan);

    @Query("UPDATE plans SET isActive=:status WHERE planId==:id")
    void updatePlanStatus(int id, boolean status);

    @Query("SELECT * FROM plans ORDER BY isActive DESC")
    List<Plan>getSortedPlans();



}
