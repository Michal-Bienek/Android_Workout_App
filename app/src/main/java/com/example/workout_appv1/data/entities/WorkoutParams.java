package com.example.workout_appv1.data.entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity(tableName = "workout_params",foreignKeys = {
        @ForeignKey(entity = ExercisesInRoutine.class,
                parentColumns = "exerciseInRoutineId",
                childColumns = "fk_exerciseInRoutineId",
                onDelete = ForeignKey.CASCADE)
},indices = {@Index(value = {"fk_exerciseInRoutineId"})})
public class WorkoutParams {
    @PrimaryKey(autoGenerate = true)
    private int workoutParamsId;
    private Date workout_date;
    private int fk_exerciseInRoutineId;

    public WorkoutParams() {
    }

    @Ignore
    public WorkoutParams(int workoutParamsId, Date workout_date, int fk_exerciseInRoutineId) {
        this.workoutParamsId = workoutParamsId;
        this.workout_date = workout_date;
        this.fk_exerciseInRoutineId = fk_exerciseInRoutineId;
    }

    @Ignore
    @Override
    public String toString() {
        return "WorkoutParams{" +
                "workoutParamsId=" + workoutParamsId +
                ", workout_date=" + workout_date +
                ", fk_exerciseInRoutineId=" + fk_exerciseInRoutineId +
                '}';
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
