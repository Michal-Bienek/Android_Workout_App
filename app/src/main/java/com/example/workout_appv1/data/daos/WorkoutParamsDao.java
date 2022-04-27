package com.example.workout_appv1.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.workout_appv1.data.entities.WorkoutParams;
import com.example.workout_appv1.data.relations.WorkoutParamsWithSeries;

import java.util.Date;
import java.util.List;

@Dao
public interface WorkoutParamsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWorkoutParams(WorkoutParams workoutParams);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWorkoutParamsList(List<WorkoutParams> workoutParamsList);

    @Delete
    void deleteWorkoutParams(WorkoutParams workoutParams);

    @Update
    void updateWorkoutParams(WorkoutParams workoutParams);

    @Query("UPDATE workout_params SET workout_date=:lastWorkoutDate WHERE workoutParamsId=:workoutParamsId")
    void updateWorkoutParamsDate(int workoutParamsId, Date lastWorkoutDate);

    @Query("SELECT * FROM workout_params")
    LiveData<List<WorkoutParams>>getAllWorkoutParams();

    @Query("SELECT * FROM workout_params WHERE fk_exerciseInRoutineId=:exerciseInRoutineId ORDER BY workoutParamsId ASC")
    LiveData<List<WorkoutParams>>getWorkoutParamsInExerciseInRoutine(int exerciseInRoutineId);

    @Query("SELECT * FROM workout_params WHERE fk_exerciseInRoutineId=:ExerciseInRoutineId ORDER BY workout_date DESC LIMIT 1")
    LiveData<List<WorkoutParams>>getLatestWorkoutParamsInExerciseInRoutine(int ExerciseInRoutineId);

    @Transaction
    @Query("SELECT * FROM workout_params WHERE workoutParamsId=:workoutParamsId")
    LiveData<List<WorkoutParamsWithSeries>>getWorkoutParamsWithSeries(int workoutParamsId);



}
