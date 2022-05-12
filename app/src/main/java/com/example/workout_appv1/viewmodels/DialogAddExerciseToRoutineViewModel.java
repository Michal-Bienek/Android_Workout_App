package com.example.workout_appv1.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.workout_appv1.data.entities.ExercisesInRoutine;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.data.repositories.ExerciseInRoutineRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DialogAddExerciseToRoutineViewModel extends AndroidViewModel {
    private ExerciseInRoutineRepository exerciseInRoutineRepository;
    private MutableLiveData<List<Series>>exerciseSeries;
    public DialogAddExerciseToRoutineViewModel(@NonNull Application application) {
        super(application);
        exerciseInRoutineRepository = new ExerciseInRoutineRepository(application);
    }

    public LiveData<List<Series>>getExerciseSeriesList(){
        initializeLivedata();
        return exerciseSeries;
    }

    public void addSeries(List<Series>seriesList){
        int list_size = seriesList.size();
        if(list_size>0){
            Series series = seriesList.get(list_size-1);
            seriesList.add(series);
            this.exerciseSeries.postValue(seriesList);
        }
    }

    public void removeSeries(List<Series>seriesList){
        int list_size =seriesList.size();
        if(list_size>1){
            seriesList.remove(list_size-1);
            this.exerciseSeries.setValue(seriesList);
        }
    }
    public void updateSeries(List<Series>seriesList){
        this.exerciseSeries.setValue(seriesList);
    }


    private void initializeLivedata(){
        Series series = new Series(5,40,"1/2/3",120,"",0 );
        List<Series>initList = new ArrayList<>();
        initList.add(series);
        this.exerciseSeries = new MutableLiveData<>(initList);

    }

    public void addExerciseWithParameters(int routineId,int exerciseId,List<Series>seriesList){
        if(seriesList.size()>=1){
            ExercisesInRoutine exercises = new ExercisesInRoutine(0,routineId,exerciseId);
            try {
                exerciseInRoutineRepository.insertExerciseInRoutineWithParameters(exercises,seriesList);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
