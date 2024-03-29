package com.example.workout_appv1.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.workout_appv1.data.daos.ExerciseDao;
import com.example.workout_appv1.data.daos.ExercisesInRoutineDao;
import com.example.workout_appv1.data.daos.PlanDao;
import com.example.workout_appv1.data.daos.RoutineDao;
import com.example.workout_appv1.data.daos.SeriesDao;
import com.example.workout_appv1.data.daos.WorkoutParamsDao;
import com.example.workout_appv1.data.entities.Exercise;
import com.example.workout_appv1.data.entities.ExercisesInRoutine;
import com.example.workout_appv1.data.entities.Plan;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.data.entities.RoutineStats;
import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.data.entities.WorkoutParams;
import com.example.workout_appv1.data.type_converters.DateConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Plan.class, Routine.class, Exercise.class, ExercisesInRoutine.class,
        WorkoutParams.class, Series.class, RoutineStats.class
}, version = 4, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class WorkoutPlannerDb extends RoomDatabase {
    private static WorkoutPlannerDb workoutPlannerDb;
    private static final String DATABASE_NAME = "workout_plannerDB";
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public synchronized static WorkoutPlannerDb getInstance(Context context) {
        if (workoutPlannerDb == null) {
            workoutPlannerDb = Room.databaseBuilder(context.getApplicationContext(),
                    WorkoutPlannerDb.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        prepopulateIfEmpty();
        return workoutPlannerDb;
    }

    public abstract PlanDao planDao();

    public abstract RoutineDao routineDao();

    public abstract ExerciseDao exerciseDao();

    public abstract ExercisesInRoutineDao exercisesInRoutineDao();

    public abstract WorkoutParamsDao workoutParamsDao();

    public abstract SeriesDao seriesDao();

    private static void prepopulateIfEmpty(){
        WorkoutPlannerDb.databaseWriteExecutor.execute(() -> {
            ExerciseDao exerciseDao = workoutPlannerDb.exerciseDao();
            if(exerciseDao.getExerciseCount()==0){
                exerciseDao.insertExercise(new Exercise("Wyciskanie leżąc", "", "Klata"));
                exerciseDao.insertExercise(new Exercise("Przysiady", "", "Nogi"));
                exerciseDao.insertExercise(new Exercise("Dipy na poręczach", "", "Triceps"));
                exerciseDao.insertExercise(new Exercise("Podciąganie", "", "Plecy"));
                exerciseDao.insertExercise(new Exercise("Pompki", "", "Klata"));
                exerciseDao.insertExercise(new Exercise("Allahy", "", "Brzuch"));
                exerciseDao.insertExercise(new Exercise("Unoszenie na biceps", "", "Biceps"));
                exerciseDao.insertExercise(new Exercise("Wyciskanie na suwnicy Smitha", "", "Nogi "));
                exerciseDao.insertExercise(new Exercise("Wznosy tułowia", "", "Prostowniki grzbietu"));
                exerciseDao.insertExercise(new Exercise("Wznosy ramion", "", "Barki"));
                exerciseDao.insertExercise(new Exercise("Prostowanie ramion na maszynie", "", "Triceps"));
            }
        });
    }
}

//    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            new PopulateDbExerciseAsyncTask(workoutPlannerDb).execute();
//        }
//    };
//
//    private static class PopulateDbExerciseAsyncTask extends AsyncTask<Void, Void, Void> {
//        private final ExerciseDao exerciseDao;
//
//        private PopulateDbExerciseAsyncTask(WorkoutPlannerDb db) {
//            exerciseDao = db.exerciseDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            exerciseDao.insertExercise(new Exercise("Wyciskanie leząc", "", "Klata"));
//            exerciseDao.insertExercise(new Exercise("Przysiady", "", "Nogi"));
//            exerciseDao.insertExercise(new Exercise("Dipy na poręczach", "", "Triceps"));
//            exerciseDao.insertExercise(new Exercise("Podciąganie", "", "Plecy"));
//            exerciseDao.insertExercise(new Exercise("Pompki", "", "Klata"));
//            exerciseDao.insertExercise(new Exercise("Allahy", "", "Brzuch"));
//            exerciseDao.insertExercise(new Exercise("Unoszenie na biceps", "", "Biceps"));
//            exerciseDao.insertExercise(new Exercise("Wyciskanie na suwnicy Smitha", "", "Nogi "));
//            exerciseDao.insertExercise(new Exercise("Wznosy tułowia", "", "Prostowniki grzbietu"));
//            return null;
//        }
//    }
