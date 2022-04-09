package com.example.workout_appv1.data.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.data.entities.WorkoutParams;

import java.util.List;

public class WorkoutParamsWithSeries {
    @Embedded public WorkoutParams workoutParams;
    @Relation(
            parentColumn = "workoutParamsId",
            entityColumn = "fk_workoutParamsId"
    )
    public List<Series> seriesList;
}
