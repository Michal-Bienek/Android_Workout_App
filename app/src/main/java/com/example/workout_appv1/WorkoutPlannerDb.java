package com.example.workout_appv1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.workout_appv1.entities.Exercise;
import com.example.workout_appv1.entities.ExercisesInRoutine;
import com.example.workout_appv1.entities.Plan;
import com.example.workout_appv1.entities.Routine;
import com.example.workout_appv1.entities.Series;
import com.example.workout_appv1.entities.WorkoutParams;
import com.example.workout_appv1.type_converters.DateConverter;

@Database(entities = {
        Plan.class,
        Routine.class,
        Exercise.class,
        ExercisesInRoutine.class,
        WorkoutParams.class,
        Series.class
},version = 1)
@TypeConverters({DateConverter.class})
public abstract class WorkoutPlannerDb extends RoomDatabase {
    private static WorkoutPlannerDb workoutPlannerDb;
    private static final String DATABASE_NAME="workout_plannerDB";

    public synchronized static WorkoutPlannerDb getInstance(Context context){
        if(workoutPlannerDb==null){
            workoutPlannerDb=Room.databaseBuilder(context.getApplicationContext(),WorkoutPlannerDb.class,DATABASE_NAME)
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return workoutPlannerDb;
    }

    //Daos
}
