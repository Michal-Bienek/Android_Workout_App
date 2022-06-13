package com.example.workout_appv1.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.daos.RoutineDao;
import com.example.workout_appv1.data.daos.WorkoutParamsDao;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.data.entities.RoutineStats;
import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.data.joinEntities.WorkoutParamsSeries;
import com.example.workout_appv1.data.relations.RoutineWithExercisesInRoutine;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoutineRepository {
    private final RoutineDao routineDao;
    private final WorkoutPlannerDb database;

    public RoutineRepository(Application application){
        database = WorkoutPlannerDb.getInstance(application);
        this.routineDao = database.routineDao();
    }

    public void insertRoutine(Routine routine){
        WorkoutPlannerDb.databaseWriteExecutor.execute(() -> routineDao.insertRoutine(routine));
    }
    public void updateRoutine(Routine routine){
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->routineDao.updateRoutine(routine));
    }
    public void deleteRoutine(Routine routine){
        WorkoutPlannerDb.databaseWriteExecutor.execute(()->routineDao.deleteRoutine(routine));
    }
    public LiveData<List<Routine>>getRoutinesFromActivePlans(){
        return routineDao.getRoutinesFromActivePlans();
    }
    public LiveData<List<Routine>>getAllPlanRoutines(int planId){
        return routineDao.getAllPlanRoutines(planId);
    }

    public LiveData<Routine>getRoutineById(int routineId){
        return routineDao.getRoutineById(routineId);
    }

    public void updateRoutineById(int routineId, Date date){
        WorkoutPlannerDb.databaseWriteExecutor.execute(() -> routineDao.updateRoutineById(routineId,date));
    }

    public void saveUserWorkout(List<WorkoutParamsSeries>userWorkoutList,int routineId, Date date){
        WorkoutPlannerDb.databaseWriteExecutor.execute(() -> {
            updateRoutineById(routineId,date);
            double training_volume = saveWpWithSeries(userWorkoutList);
            RoutineStats routineStats = new RoutineStats(date,training_volume,routineId);
            routineDao.insertRoutineStat(routineStats);
        });


    }
    private double saveWpWithSeries(List<WorkoutParamsSeries>userWorkoutList){
        WorkoutParamsDao workoutParamsDao = database.workoutParamsDao();
        double training_volume =0;
        for(WorkoutParamsSeries wps : userWorkoutList){
            long wpId = workoutParamsDao.insertWorkoutParams(wps.getWorkoutParams());
            if((int)wpId>0){
                for(Series s: wps.getSeriesList()){
                    s.setFk_workoutParamsId((int)wpId);
                    training_volume+=(s.getReps()*s.getWeight());
                    database.seriesDao().insertSeries(s);
                }
            }
        }
        return training_volume;
    }

    public LiveData<List<RoutineStats>>getAllRoutineStatsById(int routineId){
        return routineDao.getAllRoutineStatsById(routineId);
    }
}
