package com.example.workout_appv1.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.workout_appv1.data.daos.daos.ExerciseDao;
import com.example.workout_appv1.data.daos.daos.ExercisesInRoutineDao;
import com.example.workout_appv1.data.daos.daos.PlanDao;
import com.example.workout_appv1.data.daos.daos.RoutineDao;
import com.example.workout_appv1.data.daos.daos.SeriesDao;
import com.example.workout_appv1.data.daos.daos.WorkoutParamsDao;
import com.example.workout_appv1.data.entities.entities.Exercise;
import com.example.workout_appv1.data.entities.entities.ExercisesInRoutine;
import com.example.workout_appv1.data.entities.entities.Plan;
import com.example.workout_appv1.data.entities.entities.Routine;
import com.example.workout_appv1.data.entities.entities.Series;
import com.example.workout_appv1.data.entities.entities.WorkoutParams;
import com.example.workout_appv1.data.type_converters.DateConverter;

@Database(entities = {
        Plan.class,
        Routine.class,
        Exercise.class,
        ExercisesInRoutine.class,
        WorkoutParams.class,
        Series.class
},version = 1,exportSchema = true)
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
    public abstract PlanDao planDao();
    public abstract RoutineDao routineDao();
    public abstract ExerciseDao exerciseDao();
    public abstract ExercisesInRoutineDao exercisesInRoutineDao();
    public abstract WorkoutParamsDao workoutParamsDao();
    public abstract SeriesDao seriesDao();
}
