package com.example.workout_appv1.data.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "exercises")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    private int exerciseId;
    private String name;
    private String description;
    private String muscleGroup;

    public Exercise(){}

    @Ignore
    public Exercise(String name, String description, String muscleGroup) {
        this.name = name;
        this.description = description;
        this.muscleGroup = muscleGroup;
    }

    @Ignore
    @Override
    public String toString() {
        return "Exercise{" +
                "exerciseId=" + exerciseId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", muscleGroup='" + muscleGroup + '\'' +
                '}';
    }


    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
