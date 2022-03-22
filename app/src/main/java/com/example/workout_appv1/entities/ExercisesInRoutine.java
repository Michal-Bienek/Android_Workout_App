package com.example.workout_appv1.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
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

    @Ignore
    public ExercisesInRoutine(int exerciseInRoutineId, int fk_routineId, int fk_exerciseId) {
        this.exerciseInRoutineId = exerciseInRoutineId;
        this.fk_routineId = fk_routineId;
        this.fk_exerciseId = fk_exerciseId;
    }

    public int getExerciseInRoutineId() {
        return exerciseInRoutineId;
    }

    public void setExerciseInRoutineId(int exerciseInRoutineId) {
        this.exerciseInRoutineId = exerciseInRoutineId;
    }

    public int getFk_routineId() {
        return fk_routineId;
    }

    public void setFk_routineId(int fk_routineId) {
        this.fk_routineId = fk_routineId;
    }

    public int getFk_exerciseId() {
        return fk_exerciseId;
    }

    public void setFk_exerciseId(int fk_exerciseId) {
        this.fk_exerciseId = fk_exerciseId;
    }
}
