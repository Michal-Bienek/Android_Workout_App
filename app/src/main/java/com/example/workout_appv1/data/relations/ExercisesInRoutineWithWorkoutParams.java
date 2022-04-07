package com.example.workout_appv1.data.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.workout_appv1.data.entities.entities.ExercisesInRoutine;
import com.example.workout_appv1.data.entities.entities.WorkoutParams;

import java.util.List;

public class ExercisesInRoutineWithWorkoutParams {
    @Embedded public ExercisesInRoutine exercisesInRoutine;
    @Relation(
            parentColumn = "exerciseInRoutineId",
            entityColumn = "fk_exerciseInRoutineId"
    )

    public List<WorkoutParams> workoutParamsList;
}
