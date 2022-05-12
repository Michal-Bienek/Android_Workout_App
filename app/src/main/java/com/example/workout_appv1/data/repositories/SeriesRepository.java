package com.example.workout_appv1.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.daos.SeriesDao;
import com.example.workout_appv1.data.entities.Series;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SeriesRepository {
    private final SeriesDao seriesDao;
    public SeriesRepository(Application application){
        WorkoutPlannerDb database = WorkoutPlannerDb.getInstance(application);
        seriesDao = database.seriesDao();
    }

    public void insertSeries(Series series){
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->seriesDao.insertSeries(series));
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(()->seriesDao.insertSeries(series));
    }
    public void insertSeriesList(List<Series> seriesList){
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->seriesDao.insertSeriesList(seriesList));
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(()->seriesDao.insertSeriesList(seriesList));
    }
    public void deleteSeries(Series series){
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->seriesDao.deleteSeries(series));
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(()->seriesDao.deleteSeries(series));
    }
    public void updateSeries(Series series){
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->seriesDao.updateSeries(series));
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(()->seriesDao.updateSeries(series));
    }
    public LiveData<List<Series>> getAllSeriesInWorkoutParams(int workoutParamsId){
        return seriesDao.getAllSeriesInWorkoutParams(workoutParamsId);
    }
}
