package com.example.workout_appv1.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.workout_appv1.entities.ExercisesInRoutine;
import com.example.workout_appv1.entities.WorkoutParams;

import java.util.List;

public class ExercisesInRoutineWithWorkoutParams {
    @Embedded public ExercisesInRoutine exercisesInRoutine;
    @Relation(
            parentColumn = "exerciseInRoutineId",
            entityColumn = "fk_exerciseInRoutineId"
    )

    public List<WorkoutParams> workoutParamsList;
}
