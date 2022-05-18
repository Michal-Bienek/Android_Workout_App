package com.example.workout_appv1.data.joinEntities;

import com.example.workout_appv1.data.daos.SeriesDao;
import com.example.workout_appv1.data.entities.Exercise;
import com.example.workout_appv1.data.entities.Series;

import java.util.List;

public class ExerciseWithSeries {
    private Exercise exercise;
    private ExerciseInRoutineWorkoutParams exerciseInRoutineWorkoutParams;
    private List<Series> seriesList;

    public ExerciseWithSeries(Exercise exercise, ExerciseInRoutineWorkoutParams exerciseInRoutineWorkoutParams, List<Series> seriesList) {
        this.exercise = exercise;
        this.exerciseInRoutineWorkoutParams = exerciseInRoutineWorkoutParams;
        this.seriesList = seriesList;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public ExerciseInRoutineWorkoutParams getExerciseInRoutineWorkoutParams() {
        return exerciseInRoutineWorkoutParams;
    }

    public void setExerciseInRoutineWorkoutParams(ExerciseInRoutineWorkoutParams exerciseInRoutineWorkoutParams) {
        this.exerciseInRoutineWorkoutParams = exerciseInRoutineWorkoutParams;
    }

    public List<Series> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<Series> seriesList) {
        this.seriesList = seriesList;
    }
}
