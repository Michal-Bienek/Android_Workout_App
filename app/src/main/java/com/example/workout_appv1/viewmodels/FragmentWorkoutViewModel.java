package com.example.workout_appv1.viewmodels;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.workout_appv1.data.entities.Exercise;
import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.data.joinEntities.ExerciseWithOneSeries;
import com.example.workout_appv1.data.joinEntities.ExerciseWithSeries;
import com.example.workout_appv1.data.joinEntities.WorkoutParamsSeries;
import com.example.workout_appv1.data.repositories.ExerciseInRoutineRepository;
import com.example.workout_appv1.helpers.ValueParser;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentWorkoutViewModel extends AndroidViewModel {
    private final ExerciseInRoutineRepository repository;
    private Date currentDate;
    List<ExerciseWithSeries> exerciseWithSeriesList;
    List<WorkoutParamsSeries>workoutParamsSeriesList = new ArrayList<>();
    List<Series>userSeries = new ArrayList<>();
    private int exercise_position;
    private int series_position;
    private int series_count;

    public FragmentWorkoutViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ExerciseInRoutineRepository(application);
        currentDate= new Date();
    }

    public ExerciseWithOneSeries getNextSeries(ExerciseWithOneSeries exercise){
        if(series_position<series_count-1){
            series_position++;
            return new ExerciseWithOneSeries(getExerciseByPosition(exercise_position),getSeriesByPosition(series_position),series_count,series_position );
        }
        else{
            if(exercise_position<exerciseWithSeriesList.size()-1){
                exercise_position++;
                series_position= 0;
                setSeries_count(exercise_position);
                return new ExerciseWithOneSeries(getExerciseByPosition(exercise_position),getSeriesByPosition(series_position),series_count,series_position);
            }
            else{
                return null;
            }
        }
    }

    public ExerciseWithOneSeries initializeVariables(int routineId){
        getExercisesInRoutineWithSeries(routineId);
        return new ExerciseWithOneSeries(getExerciseByPosition(exercise_position),getSeriesByPosition(series_position),series_count,series_position);
    }

    private void getExercisesInRoutineWithSeries(int routineId) {
        this.exerciseWithSeriesList = repository.getExercisesInRoutineWithSeries(routineId);
        this.exercise_position = 0;
        this.series_position = 0;
        setSeries_count(exercise_position);

    }

    private void setSeries_count(int exercise_position){
        ExerciseWithSeries exerciseWithSeries = exerciseWithSeriesList.get(exercise_position);
        this.series_count = exerciseWithSeries.getSeriesList().size();
    }

    private Series getSeriesByPosition(int position){
        return exerciseWithSeriesList.get(exercise_position).getSeriesList().get(position);
    }
    private Exercise getExerciseByPosition(int position){
        return exerciseWithSeriesList.get(position).getExercise();
    }
    private  void addToUserSeries(ExerciseWithOneSeries exercise,Series s){
        Series series= exercise.getSeries();
    }

    public String validateReps(String etReps){
        if(etReps==null||etReps.isEmpty()){
            return "Pole nie może być puste";
        }
        else if(!ValueParser.isPositiveInteger(etReps)){
            return "Niepoprawna wartość";
        }else{
            return "";
        }
    }
    public String validateWeight(String etWeight){
        if(etWeight==null||etWeight.trim().isEmpty()){
            return "Pole nie może być puste";
        }
        else if(!ValueParser.isDouble(etWeight)){
            return "Niepoprawna wartość";
        }else{
            return "";
        }
    }
}
