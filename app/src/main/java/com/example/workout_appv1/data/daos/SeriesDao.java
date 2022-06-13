package com.example.workout_appv1.data.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.workout_appv1.data.entities.Series;

import java.util.List;

@Dao
public interface SeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSeries(Series series);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSeriesList(List<Series> seriesList);

}
