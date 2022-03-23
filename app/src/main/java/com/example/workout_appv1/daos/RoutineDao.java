package com.example.workout_appv1.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.workout_appv1.entities.Routine;

import java.util.List;

@Dao
public interface RoutineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoutine(Routine routine);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoutines(List<Routine> routineList);

    @Delete
    void deleteRoutine(Routine routine);

    @Update
    void updateRoutine(Routine routine);

    @Query("UPDATE routines SET lastWorkoutDate=:date WHERE routineId=:routineId")
    void updateRoutineDate(int routineId, long date);

    @Query("SELECT * FROM routines")
    List<Routine>GetAllRoutines();

    @Query("SELECT * FROM routines where fk_planId=:planId")
    List<Routine>GetAllPlanRoutines(int planId);



}
