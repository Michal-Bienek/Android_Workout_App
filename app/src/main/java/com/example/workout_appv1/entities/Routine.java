package com.example.workout_appv1.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "routines", foreignKeys = {@ForeignKey(entity =Plan.class,
        parentColumns = "planId",
        childColumns = "fk_planId",
        onDelete = ForeignKey.CASCADE
)} )
public class Routine {
    @PrimaryKey(autoGenerate = true)
    private int routineId;
    private String routineName;
    private int dayOfWeek;
    //Last workout data field
    private int fk_planId;
}
