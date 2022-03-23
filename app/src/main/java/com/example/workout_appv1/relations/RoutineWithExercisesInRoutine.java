package com.example.workout_appv1.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.workout_appv1.entities.ExercisesInRoutine;
import com.example.workout_appv1.entities.Routine;

import java.util.List;

public class RoutineWithExercisesInRoutine {
    @Embedded public Routine routine;
    @Relation(
            parentColumn = "routineId",
            entityColumn = "fk_routineId"
    )
    public List<ExercisesInRoutine>exercisesInRoutineList;
}
