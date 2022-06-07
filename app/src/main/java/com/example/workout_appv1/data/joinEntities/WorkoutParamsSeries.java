package com.example.workout_appv1.data.joinEntities;

import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.data.entities.WorkoutParams;

import java.util.List;

public class WorkoutParamsSeries {
    private WorkoutParams workoutParams;
    private List<Series> seriesList;

    public WorkoutParamsSeries(WorkoutParams workoutParams, List<Series> seriesList) {
        this.workoutParams = workoutParams;
        this.seriesList = seriesList;
    }

    public WorkoutParams getWorkoutParams() {
        return workoutParams;
    }

    public void setWorkoutParams(WorkoutParams workoutParams) {
        this.workoutParams = workoutParams;
    }

    public List<Series> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<Series> seriesList) {
        this.seriesList = seriesList;
    }
}
