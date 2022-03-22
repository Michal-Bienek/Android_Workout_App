package com.example.workout_appv1.entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity(tableName = "workout_params",foreignKeys = {
        @ForeignKey(entity = ExercisesInRoutine.class,
                parentColumns = "exerciseInRoutineId",
                childColumns = "fk_exerciseInRoutineId",
                onDelete = ForeignKey.CASCADE)
})
public class WorkoutParams {
    @PrimaryKey(autoGenerate = true)
    private int workoutParamsId;
    private Date workout_date;
    private int fk_exerciseInRoutineId;

    @Ignore
    public WorkoutParams(int workoutParamsId, Date workout_date, int fk_exerciseInRoutineId) {
        this.workoutParamsId = workoutParamsId;
        this.workout_date = workout_date;
        this.fk_exerciseInRoutineId = fk_exerciseInRoutineId;
    }

    public int getWorkoutParamsId() {
        return workoutParamsId;
    }

    public void setWorkoutParamsId(int workoutParamsId) {
        this.workoutParamsId = workoutParamsId;
    }

    public Date getWorkout_date() {
        return workout_date;
    }

    public void setWorkout_date(Date workout_date) {
        this.workout_date = workout_date;
    }

    public int getFk_exerciseInRoutineId() {
        return fk_exerciseInRoutineId;
    }

    public void setFk_exerciseInRoutineId(int fk_exerciseInRoutineId) {
        this.fk_exerciseInRoutineId = fk_exerciseInRoutineId;
    }
}
