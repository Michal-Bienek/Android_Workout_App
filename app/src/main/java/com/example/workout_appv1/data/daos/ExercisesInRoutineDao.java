package com.example.workout_appv1.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.workout_appv1.data.entities.ExercisesInRoutine;
import com.example.workout_appv1.data.relations.ExercisesInRoutineWithWorkoutParams;

import java.util.List;

@Dao
public interface ExercisesInRoutineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertExerciseInRoutine(ExercisesInRoutine exerciseInRoutine);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExerciseInRoutineList(List<ExercisesInRoutine> exercisesInRoutineList);

    @Delete
    void deleteExerciseInRoutine(ExercisesInRoutine exercisesInRoutine);

    @Update
    void updateExerciseInRoutine(ExercisesInRoutine exercisesInRoutine);

    @Query("SELECT * FROM exercises_in_routine")
    LiveData<List<ExercisesInRoutine>>getAllExercisesInRoutine();

    @Query("SELECT * FROM exercises_in_routine WHERE fk_routineId=:routineId")
    LiveData<List<ExercisesInRoutine>>getExercisesInRoutineByRoutineId(int routineId);

    @Query("SELECT * FROM exercises_in_routine WHERE fk_exerciseId=:exerciseId")
    LiveData<List<ExercisesInRoutine>>getExercisesInRoutineByExerciseId(int exerciseId);

    @Transaction
    @Query("SELECT * FROM exercises_in_routine WHERE exerciseInRoutineId=:exerciseInRoutineId")
    LiveData<List<ExercisesInRoutineWithWorkoutParams>>exercisesWithParams(int exerciseInRoutineId);



}
