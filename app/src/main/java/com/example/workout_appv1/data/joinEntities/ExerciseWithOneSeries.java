package com.example.workout_appv1.data.joinEntities;

import androidx.annotation.NonNull;

import com.example.workout_appv1.data.entities.Exercise;
import com.example.workout_appv1.data.entities.Series;

import java.util.List;

public class ExerciseWithOneSeries {
    private Exercise exercise;
    private Series series;
    private int series_Count;
    private int seriesPosition;

    public ExerciseWithOneSeries(Exercise exercise, Series series, int series_Count,int seriesPosition) {
        this.exercise = exercise;
        this.series = series;
        this.series_Count = series_Count;
        this.seriesPosition = seriesPosition;
    }

    @NonNull
    @Override
    public String toString() {
        return "ExerciseWithOneSeries{" +
                "exercise=" + exercise +
                ", series=" + series +
                ", series_Count=" + series_Count +
                '}';
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public int getSeries_Count() {
        return series_Count;
    }

    public void setSeries_Count(int series_Count) {
        this.series_Count = series_Count;
    }

    public int getSeriesPosition() {
        return seriesPosition;
    }

    public void setSeriesPosition(int seriesPosition) {
        this.seriesPosition = seriesPosition;
    }
}
