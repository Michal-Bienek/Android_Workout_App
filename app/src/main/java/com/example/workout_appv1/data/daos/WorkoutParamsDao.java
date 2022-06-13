package com.example.workout_appv1.data.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.workout_appv1.data.entities.WorkoutParams;
import com.example.workout_appv1.data.relations.WorkoutParamsWithSeries;

@Dao
public interface WorkoutParamsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertWorkoutParams(WorkoutParams workoutParams);

    @Transaction
    @Query("SELECT * FROM workout_params WHERE fk_exerciseInRoutineId = :exerciseInRoutineId AND workout_date IS NULL ORDER BY workoutParamsId DESC LIMIT 1")
    WorkoutParamsWithSeries getWorkoutParamsWithSeries(int exerciseInRoutineId);


}
