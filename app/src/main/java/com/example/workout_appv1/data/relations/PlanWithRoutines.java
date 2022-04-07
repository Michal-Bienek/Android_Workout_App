package com.example.workout_appv1.data.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.workout_appv1.data.entities.entities.Plan;
import com.example.workout_appv1.data.entities.entities.Routine;

import java.util.List;

public class PlanWithRoutines {
    @Embedded public Plan plan;
    @Relation(
            parentColumn = "planId",
            entityColumn = "fk_planId"
    )
    public List<Routine> routineList;
}
