package com.example.workout_appv1.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.daos.ExerciseDao;
import com.example.workout_appv1.data.daos.ExercisesInRoutineDao;
import com.example.workout_appv1.data.entities.ExercisesInRoutine;
import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.data.entities.WorkoutParams;
import com.example.workout_appv1.data.joinEntities.ExerciseInRoutineExercise;
import com.example.workout_appv1.data.relations.ExercisesInRoutineWithWorkoutParams;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExerciseInRoutineRepository {
    private final ExercisesInRoutineDao exercisesInRoutineDao;
    private final WorkoutPlannerDb database;

    public ExerciseInRoutineRepository(Application application) {
        database = WorkoutPlannerDb.getInstance(application);
        this.exercisesInRoutineDao = database.exercisesInRoutineDao();
    }

    public void insertExerciseInRoutine(ExercisesInRoutine exercisesInRoutine) {
        WorkoutPlannerDb.databaseWriteExecutor.execute(() -> this.exercisesInRoutineDao.insertExerciseInRoutine(exercisesInRoutine));

    }

    public void insertExerciseInRoutineWithParameters(ExercisesInRoutine exercisesInRoutine, List<Series> seriesList) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Long> future;

        future = executorService.submit(() -> exercisesInRoutineDao.insertExerciseInRoutine(exercisesInRoutine));
        long exerciseInRoutineId = future.get();
        WorkoutParams workoutParams = new WorkoutParams(0, null, (int) exerciseInRoutineId);
        future = executorService.submit(() -> database.workoutParamsDao().insertWorkoutParams(workoutParams));
        long workoutParamsId = future.get();
        int wpId = (int) workoutParamsId;
        for (int i = 0; i < seriesList.size(); i++) {
            seriesList.get(i).setFk_workoutParamsId(wpId);
        }
        executorService.execute(() -> database.seriesDao().insertSeriesList(seriesList));

        executorService.shutdown();
    }

    public void updateExerciseInRoutineWithParameters(int exerciseInRoutineId, List<Series>seriesList) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Long> future;

        WorkoutParams workoutParams = new WorkoutParams(0, null, exerciseInRoutineId);
        future = executorService.submit(() -> database.workoutParamsDao().insertWorkoutParams(workoutParams));
        long workoutParamsId = future.get();
        int wpId = (int) workoutParamsId;
        for (int i = 0; i < seriesList.size(); i++) {
            seriesList.get(i).setFk_workoutParamsId(wpId);
        }
        executorService.execute(() -> database.seriesDao().insertSeriesList(seriesList));

        executorService.shutdown();

    }

    public void insertExerciseInRoutineList(List<ExercisesInRoutine> exercisesInRoutineList) {
        WorkoutPlannerDb.databaseWriteExecutor.execute(() -> this.exercisesInRoutineDao.insertExerciseInRoutineList(exercisesInRoutineList));
    }

    public void deleteExerciseInRoutine(ExercisesInRoutine exercisesInRoutine) {
        WorkoutPlannerDb.databaseWriteExecutor.execute(() -> this.exercisesInRoutineDao.deleteExerciseInRoutine(exercisesInRoutine));

    }

    public void deleteExerciseInRoutineById(int exerciseInRoutineId) {
        WorkoutPlannerDb.databaseWriteExecutor.execute(() -> this.exercisesInRoutineDao.deleteExerciseInRoutineById(exerciseInRoutineId));
    }

    public void updateExerciseInRoutine(ExercisesInRoutine exercisesInRoutine) {
        WorkoutPlannerDb.databaseWriteExecutor.execute(() -> exercisesInRoutineDao.updateExerciseInRoutine(exercisesInRoutine));
    }

    public LiveData<List<ExercisesInRoutine>> getAllExercisesInRoutine() {
        return this.exercisesInRoutineDao.getAllExercisesInRoutine();
    }

    public LiveData<List<ExercisesInRoutine>> getExercisesInRoutineByRoutineId(int routineId) {
        return this.exercisesInRoutineDao.getExercisesInRoutineByRoutineId(routineId);
    }

    public LiveData<List<ExercisesInRoutine>> getExercisesInRoutineByExerciseId(int exerciseId) {
        return this.exercisesInRoutineDao.getExercisesInRoutineByExerciseId(exerciseId);
    }

    public LiveData<List<ExercisesInRoutineWithWorkoutParams>> exercisesWithParams(int exerciseInRoutineId) {
        return this.exercisesInRoutineDao.getExercisesWithParams(exerciseInRoutineId);
    }

    public LiveData<List<ExerciseInRoutineExercise>> getExerciseInRoutineAndExerciseByRoutineId(int routineId) {
        return this.exercisesInRoutineDao.getExerciseInRoutineAndExerciseByRoutineId(routineId);
    }

}
