package com.example.workout_appv1.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.workout_appv1.data.entities.Series;

import java.util.List;

@Dao
public interface SeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSeries(Series series);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSeriesList(List<Series> seriesList);

    @Delete
    void deleteSeries(Series series);

    @Update
    void updateSeries(Series series);

    @Query("Select * FROM series where fk_workoutParamsId=:workoutParamsId")
    LiveData<List<Series>> getAllSeriesInWorkoutParams(int workoutParamsId);

}
