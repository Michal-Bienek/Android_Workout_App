package com.example.workout_appv1.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.workout_appv1.data.entities.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertExercise(Exercise exercise);

    @Query("SELECT * FROM exercises")
    LiveData<List<Exercise>>getAllExercises();

    @Query("SELECT * FROM exercises WHERE exerciseId = :exerciseId")
    Exercise getExerciseById(int exerciseId);

    @Query("Select COUNT(*) from exercises")
    long getExerciseCount();

}
