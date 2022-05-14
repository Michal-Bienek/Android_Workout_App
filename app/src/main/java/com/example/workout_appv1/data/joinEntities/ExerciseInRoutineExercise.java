package com.example.workout_appv1.data.joinEntities;

public class ExerciseInRoutineExercise {
    public int exInRoutineId;
    public int exerciseId;
    public String exerciseName;

    @Override
    public String toString() {
        return "ExerciseInRoutineExercise{" +
                "exInRoutineId=" + exInRoutineId +
                ", exerciseId=" + exerciseId +
                ", exerciseName='" + exerciseName + '\'' +
                '}';
    }
}
