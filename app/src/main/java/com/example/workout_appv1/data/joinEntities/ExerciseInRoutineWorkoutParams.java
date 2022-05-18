package com.example.workout_appv1.data.joinEntities;

import androidx.annotation.NonNull;

import java.util.Objects;

public class ExerciseInRoutineWorkoutParams {
    public int exerciseInRoutineId;
    public int fk_routineId;
    public int fk_exerciseId;
    public int latest_workout_params;

    @NonNull
    @Override
    public String toString() {
        return "ExerciseInRoutineWorkoutParams{" +
                "exerciseInRoutineId=" + exerciseInRoutineId +
                ", fk_routineId=" + fk_routineId +
                ", fk_exerciseId=" + fk_exerciseId +
                ", latest_workout_params=" + latest_workout_params +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseInRoutineWorkoutParams that = (ExerciseInRoutineWorkoutParams) o;
        return exerciseInRoutineId == that.exerciseInRoutineId && fk_routineId == that.fk_routineId && fk_exerciseId == that.fk_exerciseId && latest_workout_params == that.latest_workout_params;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseInRoutineId, fk_routineId, fk_exerciseId, latest_workout_params);
    }

}
