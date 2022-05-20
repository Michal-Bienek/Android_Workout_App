package com.example.workout_appv1.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.workout_appv1.data.entities.Routine;
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
    LiveData<List<Routine>> getAllRoutines();

    @Query("SELECT * FROM routines where fk_planId=:planId ORDER BY dayOfWeek")
    LiveData<List<Routine>> getAllPlanRoutines(int planId);

    @Query("SELECT routines.* from routines " +
            "INNER JOIN plans ON plans.planId=routines.fk_planId " +
            "WHERE plans.isActive=1 " +
            "ORDER BY dayOfWeek")
    LiveData<List<Routine>>getRoutinesFromActivePlans();

    @Query("SELECT * FROM routines WHERE routineId = :routineId LIMIT 1")
    LiveData<Routine>getRoutineById(int routineId);

    @Transaction
    @Query("SELECT * FROM routines WHERE routineId=:routineId")
    LiveData<List<RoutineWithExercisesInRoutine>>getRoutineWithExercisesInRoutineList(int routineId);



}
