package com.example.workout_appv1.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.workout_appv1.entities.ExercisesInRoutine;

import java.util.List;

@Dao
public interface ExercisesInRoutineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExerciseInRoutine(ExercisesInRoutine exerciseInRoutine);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExerciseInRoutineList(List<ExercisesInRoutine> exercisesInRoutineList);

    @Delete
    void deleteExerciseInRoutine(ExercisesInRoutine exercisesInRoutine);

    @Update
    void updateExerciseInRoutine(ExercisesInRoutine exercisesInRoutine);

    @Query("SELECT * FROM exercises_in_routine")
    List<ExercisesInRoutine>getAllExercisesInRoutine();

    @Query("SELECT * FROM exercises_in_routine WHERE fk_routineId=:routineId")
    List<ExercisesInRoutine>getExercisesInRoutineByRoutineId(int routineId);

    @Query("SELECT * FROM exercises_in_routine WHERE fk_exerciseId=:exerciseId")
    List<ExercisesInRoutine>getExercisesInRoutineByExerciseId(int exerciseId);



}
