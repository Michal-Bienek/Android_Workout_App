package com.example.workout_appv1.data.entities.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "series",foreignKeys = {
        @ForeignKey(entity = WorkoutParams.class,
        parentColumns ="workoutParamsId",
        childColumns = "fk_workoutParamsId",
        onDelete = ForeignKey.CASCADE)
},indices = {@Index(value = "fk_workoutParamsId")})
public class Series {
    @PrimaryKey(autoGenerate = true)
    private int seriesId;
    private int reps;
    private double weight;
    private String rate;
    private int restTime;
    private String note;
    private int fk_workoutParamsId;

    public Series() {
    }

    @Ignore
    public Series(int seriesId, int reps, double weight, String rate, int restTime, String note, int fk_exerciseInRoutineId, int fk_workoutParamsId) {
        this.seriesId = seriesId;
        this.reps = reps;
        this.weight = weight;
        this.rate = rate;
        this.restTime = restTime;
        this.note = note;
        this.fk_workoutParamsId = fk_workoutParamsId;
    }

    @Ignore
    @Override
    public String toString() {
        return "Series{" +
                "seriesId=" + seriesId +
                ", reps=" + reps +
                ", weight=" + weight +
                ", rate='" + rate + '\'' +
                ", restTime=" + restTime +
                ", note='" + note + '\'' +
                ", fk_workoutParamsId=" + fk_workoutParamsId +
                '}';
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getFk_workoutParamsId() {
        return fk_workoutParamsId;
    }

    public void setFk_workoutParamsId(int fk_workoutParamsId) {
        this.fk_workoutParamsId = fk_workoutParamsId;
    }
}
