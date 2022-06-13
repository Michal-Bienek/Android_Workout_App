package com.example.workout_appv1.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.workout_appv1.data.entities.ExercisesInRoutine;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.data.relations.WorkoutParamsWithSeries;
import com.example.workout_appv1.data.repositories.ExerciseInRoutineRepository;
import com.example.workout_appv1.data.repositories.WorkoutParamsRepository;
import com.example.workout_appv1.helpers.ValueParser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DialogAddExerciseToRoutineViewModel extends AndroidViewModel {
    private final ExerciseInRoutineRepository exerciseInRoutineRepository;
    private final WorkoutParamsRepository workoutParamsRepository;
    private MutableLiveData<List<Series>> exerciseSeries;

    public DialogAddExerciseToRoutineViewModel(@NonNull Application application) {
        super(application);
        exerciseInRoutineRepository = new ExerciseInRoutineRepository(application);
        workoutParamsRepository = new WorkoutParamsRepository(application);
    }

    public LiveData<List<Series>> getExerciseSeriesList() {
        initializeLivedata();
        return exerciseSeries;
    }

    public LiveData<List<Series>> getExerciseSeriesList(int exerciseInRoutineId) {
        getWorkoutParamsWithSeries(exerciseInRoutineId);
        return exerciseSeries;
    }

    private void initializeLivedata() {
        Series series = new Series(5, 40, "1/2/3", 120, "", 0);
        List<Series> initList = new ArrayList<>();
        initList.add(series);
        this.exerciseSeries = new MutableLiveData<>(initList);

    }

    private void getWorkoutParamsWithSeries(int exerciseInRoutineId) {
        WorkoutParamsWithSeries withSeries = workoutParamsRepository.getWorkoutParamsWithSeries(exerciseInRoutineId);
        if (withSeries != null && withSeries.seriesList.size() > 0) {
            this.exerciseSeries = new MutableLiveData<>(withSeries.seriesList);
        } else {
            initializeLivedata();
        }

    }

    public void addSeries(List<Series> seriesList) {
        int list_size = seriesList.size();
        if (list_size > 0) {
            Series series = seriesList.get(list_size - 1);
            Series new_Series = new Series(series);
            int id = new_Series.getSeriesId() + 1;
            new_Series.setSeriesId(id);
            seriesList.add(new_Series);
            this.exerciseSeries.postValue(seriesList);
        }
    }

    public void removeSeries(List<Series> seriesList) {
        int list_size = seriesList.size();
        if (list_size > 1) {
            seriesList.remove(list_size - 1);
            this.exerciseSeries.setValue(seriesList);
        }
    }

    public void updateSeries(List<Series> seriesList, int position, Series series) {
        this.exerciseSeries.setValue(seriesList);
        if (position < seriesList.size()) {
            seriesList.set(position, series);
            this.exerciseSeries.setValue(seriesList);
        }
    }


    public void addExerciseWithParameters(int routineId, int exerciseId, List<Series> seriesList) {
        if (seriesList.size() >= 1) {
            ExercisesInRoutine exercises = new ExercisesInRoutine(0, routineId, exerciseId);
            try {
                exerciseInRoutineRepository.insertExerciseInRoutineWithParameters(exercises, seriesList);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void editExerciseWithParameters(int exerciseInRoutineId, List<Series> seriesList) {
        if (seriesList.size() >= 1) {
            try {
                exerciseInRoutineRepository.updateExerciseInRoutineWithParameters(exerciseInRoutineId, seriesList);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Validation
    public boolean validateEtReps(String reps) {
        reps = reps.trim();
        if (reps.isEmpty()) {
            return false;
        } else return ValueParser.isPositiveInteger(reps);
    }

    public boolean validateEtWeight(String weight) {
        weight = weight.trim();
        if (weight.isEmpty()) {
            return false;
        } else return ValueParser.isDouble(weight);
    }

}
