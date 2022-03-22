package com.example.workout_appv1.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plans")
public class Plan {
    @PrimaryKey(autoGenerate = true)
    private int planId;
    private String planName;
    private String goal;
    private boolean isActive;
}
