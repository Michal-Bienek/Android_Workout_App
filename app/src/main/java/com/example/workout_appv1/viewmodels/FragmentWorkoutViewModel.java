package com.example.workout_appv1.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.workout_appv1.data.entities.Exercise;
import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.data.entities.WorkoutParams;
import com.example.workout_appv1.data.joinEntities.ExerciseInRoutineWorkoutParams;
import com.example.workout_appv1.data.joinEntities.ExerciseWithOneSeries;
import com.example.workout_appv1.data.joinEntities.ExerciseWithSeries;
import com.example.workout_appv1.data.joinEntities.WorkoutParamsSeries;
import com.example.workout_appv1.data.repositories.ExerciseInRoutineRepository;
import com.example.workout_appv1.data.repositories.WorkoutParamsRepository;
import com.example.workout_appv1.helpers.ValueParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentWorkoutViewModel extends AndroidViewModel {
    private final ExerciseInRoutineRepository repository;
    private final WorkoutParamsRepository wp_repository;
    private Date currentDate;
    List<ExerciseWithSeries> exerciseWithSeriesList;
    List<WorkoutParamsSeries> userWorkout;
    private int exercise_position;
    private int series_position;
    private int series_count;

    public FragmentWorkoutViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ExerciseInRoutineRepository(application);
        this.wp_repository = new WorkoutParamsRepository(application);
        currentDate= new Date();
    }

    public ExerciseWithOneSeries getNextSeries(ExerciseWithOneSeries exercise, String reps, String weight){
        updateUserParams(reps,weight);
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
                saveWorkout();
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
        this.initWorkoutParamsSeriesList();
        this.exercise_position = 0;
        this.series_position = 0;
        setSeries_count(exercise_position);
    }

    private void updateUserParams(String reps, String weight){
        int iReps = Integer.parseInt(reps);
        double dWeight = Double.parseDouble(weight);
        this.userWorkout.get(exercise_position).getSeriesList().get(series_position).setReps(iReps);
        this.userWorkout.get(exercise_position).getSeriesList().get(series_position).setWeight(dWeight);
    }

    private void initWorkoutParamsSeriesList(){
        this.userWorkout = new ArrayList<>();
        for (ExerciseWithSeries ex:this.exerciseWithSeriesList) {
            ExerciseInRoutineWorkoutParams exercise = ex.getExerciseInRoutineWorkoutParams();
            WorkoutParams workoutParams = new WorkoutParams(0,this.currentDate,exercise.exerciseInRoutineId);
            List<Series>seriesList = new ArrayList<>();
            for(Series series : ex.getSeriesList()){
                Series user_series = new Series(0,0,series.getRate(),series.getRestTime(),series.getNote(),series.getFk_workoutParamsId());
                seriesList.add(user_series);
            }
            this.userWorkout.add(new WorkoutParamsSeries(workoutParams,seriesList));
        }
    }
    private void saveWorkout(){
        wp_repository.insertUserWorkout(this.userWorkout);
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

    public void pauseWorkout(){
        this.saveWorkout();
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
