package com.example.workout_appv1.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "routines", foreignKeys = {@ForeignKey(entity =Plan.class,
        parentColumns = "planId",
        childColumns = "fk_planId",
        onDelete = ForeignKey.CASCADE
)} ,indices = {@Index(value = {"fk_planId"})})
public class Routine {
    @PrimaryKey(autoGenerate = true)
    private int routineId;
    private String routineName;
    private int dayOfWeek;
    private Date lastWorkoutDate;
    private int fk_planId;

    public Routine(){}

    @Ignore
    public Routine(int routineId, String routineName, int dayOfWeek, Date lastWorkoutDate, int fk_planId) {
        this.routineId = routineId;
        this.routineName = routineName;
        this.dayOfWeek = dayOfWeek;
        this.lastWorkoutDate = lastWorkoutDate;
        this.fk_planId = fk_planId;
    }

    @Ignore
    @Override
    public String toString() {
        return "Routine{" +
                "routineId=" + routineId +
                ", routineName='" + routineName + '\'' +
                ", dayOfWeek=" + dayOfWeek +
                ", lastWorkoutDate=" + lastWorkoutDate +
                ", fk_planId=" + fk_planId +
                '}';
    }

    public int getRoutineId() {
        return routineId;
    }

    public void setRoutineId(int routineId) {
        this.routineId = routineId;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Date getLastWorkoutDate() {
        return lastWorkoutDate;
    }

    public void setLastWorkoutDate(Date lastWorkoutDate) {
        this.lastWorkoutDate = lastWorkoutDate;
    }

    public int getFk_planId() {
        return fk_planId;
    }

    public void setFk_planId(int fk_planId) {
        this.fk_planId = fk_planId;
    }
}
