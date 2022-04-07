package com.example.workout_appv1.data.daos.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.workout_appv1.data.entities.entities.Routine;
import com.example.workout_appv1.data.relations.RoutineWithExercisesInRoutine;

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

    @Query("SELECT routines.* from routines " +
            "INNER JOIN plans ON plans.planId=routines.fk_planId " +
            "WHERE plans.isActive=1")
    List<Routine>getRoutinesFromActivePlans();

    @Transaction
    @Query("SELECT * FROM routines WHERE routineId=:routineId")
    List<RoutineWithExercisesInRoutine>routineWithExercisesInRoutineList(int routineId);



}
