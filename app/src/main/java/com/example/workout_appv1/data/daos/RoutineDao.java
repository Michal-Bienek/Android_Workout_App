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
import com.example.workout_appv1.data.entities.RoutineStats;
import com.example.workout_appv1.data.relations.RoutineWithExercisesInRoutine;

import java.util.Date;
import java.util.List;

@Dao
public interface RoutineDao {
    @Insert
    void insertRoutine(Routine routine);

    @Delete
    void deleteRoutine(Routine routine);

    @Update
    void updateRoutine(Routine routine);

    @Query("SELECT * FROM routines where fk_planId=:planId ORDER BY dayOfWeek")
    LiveData<List<Routine>> getAllPlanRoutines(int planId);

    @Query("SELECT routines.* from routines " +
            "INNER JOIN plans ON plans.planId=routines.fk_planId " +
            "WHERE plans.isActive=1 " +
            "ORDER BY dayOfWeek")
    LiveData<List<Routine>>getRoutinesFromActivePlans();

    @Query("SELECT * FROM routines WHERE routineId = :routineId LIMIT 1")
    LiveData<Routine>getRoutineById(int routineId);

    @Query("UPDATE routines SET lastWorkoutDate =:last_workoutDate WHERE routineId=:routineId")
    void updateRoutineById(int routineId, Date last_workoutDate);

    @Insert
    void insertRoutineStat(RoutineStats routineStats);

    @Query("SELECT * FROM routine_stats WHERE fk_routineId=:routineId ORDER BY id DESC")
    List<RoutineStats>getAllRoutineStatsById(int routineId);




}
