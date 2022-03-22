package com.example.workout_appv1.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "plans")
public class Plan {
    @PrimaryKey(autoGenerate = true)
    private int planId;
    private String planName;
    private String goal;
    private boolean isActive;

    @Ignore
    public Plan(int planId, String planName, String goal, boolean isActive) {
        this.planId = planId;
        this.planName = planName;
        this.goal = goal;
        this.isActive = isActive;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
