package com.example.workout_appv1.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.daos.WorkoutParamsDao;
import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.data.entities.WorkoutParams;
import com.example.workout_appv1.data.joinEntities.WorkoutParamsSeries;
import com.example.workout_appv1.data.relations.WorkoutParamsWithSeries;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkoutParamsRepository {
    private final WorkoutParamsDao workoutParamsDao;
    private final WorkoutPlannerDb database;

    public WorkoutParamsRepository(Application application){
        database = WorkoutPlannerDb.getInstance(application);
        this.workoutParamsDao = database.workoutParamsDao();
    }

    public void insertUserWorkout(List<WorkoutParamsSeries>userWorkoutList){
        WorkoutPlannerDb.databaseWriteExecutor.execute(() -> {
            for(WorkoutParamsSeries wps : userWorkoutList){
                long wpId = workoutParamsDao.insertWorkoutParams(wps.getWorkoutParams());
                if((int)wpId>0){
                    for(Series s: wps.getSeriesList()){
                        s.setFk_workoutParamsId((int)wpId);
                        database.seriesDao().insertSeries(s);
                    }
                }
            }
        });

    }
    public WorkoutParamsWithSeries getWorkoutParamsWithSeries(int exerciseInRoutineId){
        return workoutParamsDao.getWorkoutParamsWithSeries(exerciseInRoutineId);
    }
}
