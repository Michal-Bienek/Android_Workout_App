package com.example.workout_appv1.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.data.entities.Series;

import java.util.ArrayList;
import java.util.List;

public class DialogAddExerciseToRoutineViewModel extends AndroidViewModel {
    private MutableLiveData<List<Series>>exerciseSeries;
    public DialogAddExerciseToRoutineViewModel(@NonNull Application application) {
        super(application);
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


    private void initializeLivedata(){
        Series series = new Series(5,40,"1/2/3",120,"",0,0 );
        List<Series>initList = new ArrayList<>();
        initList.add(series);
        this.exerciseSeries = new MutableLiveData<>(initList);

    }

}
