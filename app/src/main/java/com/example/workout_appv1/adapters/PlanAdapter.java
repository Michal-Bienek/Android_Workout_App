package com.example.workout_appv1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.WorkoutPlannerDb;
import com.example.workout_appv1.entities.Routine;

import java.util.ArrayList;
import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {
    List<Routine>routines=new ArrayList<>();
    Context context;
    WorkoutPlannerDb database;
    String[]dayShortCuts;

    public PlanAdapter(List<Routine> routines, Context context, String[] dayShortCuts) {
        this.routines = routines;
        this.context = context;
        this.database = WorkoutPlannerDb.getInstance(context);
        this.dayShortCuts = dayShortCuts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Routine routine=routines.get(position);
        holder.tvNamePlanItem.setText(routine.getRoutineName());
        holder.tvDayPlanItem.setText(getDayShortcut(routine.getDayOfWeek()));
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    private String getDayShortcut(int day_of_week){
        String shortcut="";
        if(day_of_week<=7&&day_of_week>=1)
            shortcut=dayShortCuts[day_of_week-1];
        return shortcut;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayPlanItem, tvNamePlanItem;
        ImageView btnMorePlanItem;
        ConstraintLayout clPlanItem;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvDayPlanItem=itemView.findViewById(R.id.tvDayPlanItem);
            this.tvNamePlanItem=itemView.findViewById(R.id.tvNamePlanItem);
            this.btnMorePlanItem=itemView.findViewById(R.id.btnMorePlanItem);
            this.clPlanItem=itemView.findViewById(R.id.clPlanItem);
        }
    }
}
