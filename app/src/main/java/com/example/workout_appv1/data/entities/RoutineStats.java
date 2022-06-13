package com.example.workout_appv1.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "routine_stats", foreignKeys = {@ForeignKey(entity =Routine.class,
        parentColumns = "routineId",
        childColumns = "fk_routineId",
        onDelete = ForeignKey.CASCADE
)} ,indices = {@Index(value = {"fk_routineId"})})
public class RoutineStats {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date workout_date;
    private double total_volume;
    private int fk_routineId;

    public RoutineStats(){}

    @Ignore
    public RoutineStats(Date workout_date, double total_volume, int fk_routineId) {
        this.workout_date = workout_date;
        this.total_volume = total_volume;
        this.fk_routineId = fk_routineId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getWorkout_date() {
        return workout_date;
    }

    public void setWorkout_date(Date workout_date) {
        this.workout_date = workout_date;
    }

    public double getTotal_volume() {
        return total_volume;
    }

    public void setTotal_volume(double total_volume) {
        this.total_volume = total_volume;
    }

    public int getFk_routineId() {
        return fk_routineId;
    }

    public void setFk_routineId(int fk_routineId) {
        this.fk_routineId = fk_routineId;
    }
}
