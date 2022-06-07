package com.example.workout_appv1.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.MapInfo;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.workout_appv1.data.entities.ExercisesInRoutine;
import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.data.joinEntities.ExerciseInRoutineExercise;
import com.example.workout_appv1.data.joinEntities.ExerciseInRoutineWorkoutParams;
import com.example.workout_appv1.data.relations.ExercisesInRoutineWithWorkoutParams;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Dao
public interface ExercisesInRoutineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertExerciseInRoutine(ExercisesInRoutine exerciseInRoutine);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExerciseInRoutineList(List<ExercisesInRoutine> exercisesInRoutineList);

    @Delete
    void deleteExerciseInRoutine(ExercisesInRoutine exercisesInRoutine);

    @Query("DELETE FROM exercises_in_routine WHERE exerciseInRoutineId= :exerciseInRoutineId")
    void deleteExerciseInRoutineById(int exerciseInRoutineId);

    @Update
    void updateExerciseInRoutine(ExercisesInRoutine exercisesInRoutine);

    @Query("SELECT * FROM exercises_in_routine")
    LiveData<List<ExercisesInRoutine>> getAllExercisesInRoutine();

    @Query("SELECT * FROM exercises_in_routine WHERE fk_routineId=:routineId")
    LiveData<List<ExercisesInRoutine>> getExercisesInRoutineByRoutineId(int routineId);

    @Query("SELECT * FROM exercises_in_routine WHERE fk_exerciseId=:exerciseId")
    LiveData<List<ExercisesInRoutine>> getExercisesInRoutineByExerciseId(int exerciseId);

    @Transaction
    @Query("SELECT * FROM exercises_in_routine WHERE exerciseInRoutineId=:exerciseInRoutineId")
    LiveData<List<ExercisesInRoutineWithWorkoutParams>> getExercisesWithParams(int exerciseInRoutineId);


    @Query("SELECT exercises_in_routine.exerciseInRoutineId as exInRoutineId, exercises.exerciseId as exerciseId, exercises.name as exerciseName  FROM exercises_in_routine INNER JOIN exercises ON exercises_in_routine.fk_exerciseId = exercises.exerciseId WHERE exercises_in_routine.fk_routineId = :routineId ")
    LiveData<List<ExerciseInRoutineExercise>> getExerciseInRoutineAndExerciseByRoutineId(int routineId);

    @Query("SELECT ex.exerciseInRoutineId,ex.fk_routineId, ex.fk_exerciseId ," +
            "lwp.latest_workout_params, s.* FROM exercises_in_routine AS ex " +
            "INNER JOIN (SELECT fk_exerciseInRoutineId, Max(workoutParamsId) " +
            "as latest_workout_params " +
            "from (SELECT * from workout_params WHERE workout_date is null) as wp " +
            "group by wp.fk_exerciseInRoutineId Having Max(workoutParamsId) ) " +
            "as lwp on ex.exerciseInRoutineId = lwp.fk_exerciseInRoutineId " +
            "INNER JOIN series s on lwp.latest_workout_params = s.fk_workoutParamsId " +
            "Where ex.fk_routineId = :routineId")
    Map<ExerciseInRoutineWorkoutParams,List<Series>>getExercisesWithSeries(int routineId);




}


