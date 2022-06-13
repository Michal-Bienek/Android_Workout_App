package com.example.workout_appv1.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.data.repositories.RoutineRepository;

import java.util.List;

public class FragmentHomeViewModel extends AndroidViewModel {
    private RoutineRepository routineRepository;
    private final String[] dayShortcuts;

    public FragmentHomeViewModel(@NonNull Application application) {
        super(application);
        this.routineRepository = new RoutineRepository(application);
        dayShortcuts = application.getResources().getStringArray(R.array.day_shortcuts_array);

    }

    public LiveData<List<Routine>> getRoutinesFromActivePlans() {
        return routineRepository.getRoutinesFromActivePlans();
    }

    public String getDayShortcut(int day_of_week) {
        String shortcut = "NI";
        if (day_of_week <= 6 && day_of_week >= 0)
            shortcut = dayShortcuts[day_of_week];
        return shortcut;

    }
}
