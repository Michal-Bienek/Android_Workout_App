package com.example.workout_appv1.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercises_in_routine", foreignKeys = {
        @ForeignKey(entity = Routine.class,
        parentColumns = "routineId",
        childColumns = "fk_routineId",
        onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Exercise.class,
        parentColumns = "exerciseId",
        childColumns = "fk_exerciseId",
        onDelete = ForeignKey.CASCADE)
})
public class ExercisesInRoutine {
    @PrimaryKey(autoGenerate = true)
    private int exerciseInRoutineId;
    private int fk_routineId;
    private int fk_exerciseId;

}
