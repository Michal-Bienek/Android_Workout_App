package com.example.workout_appv1.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.workout_appv1.data.entities.RoutineStats;
import com.example.workout_appv1.data.joinEntities.ExerciseInRoutineExercise;
import com.example.workout_appv1.data.repositories.ExerciseInRoutineRepository;
import com.example.workout_appv1.data.repositories.RoutineRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentRoutineViewModel extends AndroidViewModel {
    private final ExerciseInRoutineRepository exerciseInRoutineRepository;
    private final RoutineRepository routineRepository;

    public FragmentRoutineViewModel(@NonNull Application application) {
        super(application);
        exerciseInRoutineRepository = new ExerciseInRoutineRepository(application);
        routineRepository = new RoutineRepository(application);
    }

    public LiveData<List<ExerciseInRoutineExercise>> getExerciseInRoutineAndExerciseByRoutineId(int routineId) {
        return exerciseInRoutineRepository.getExerciseInRoutineAndExerciseByRoutineId(routineId);
    }

    public void deleteExerciseInRoutineById(int exerciseInRoutineId) {
        exerciseInRoutineRepository.deleteExerciseInRoutineById(exerciseInRoutineId);
    }

    public LiveData<List<RoutineStats>> getAllRoutineStatsById(int routineId) {
        return routineRepository.getAllRoutineStatsById(routineId);
    }

    public boolean isTrainingVolumeGrowing(List<RoutineStats> routineStatsList) {
        if (routineStatsList.size() > 1) {
            return routineStatsList.get(0).getTotal_volume() > routineStatsList.get(1).getTotal_volume();
        } else {
            return true;
        }
    }

    public String getConvertedDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/y HH:mm");
        return dateFormat.format(date);
    }
}
