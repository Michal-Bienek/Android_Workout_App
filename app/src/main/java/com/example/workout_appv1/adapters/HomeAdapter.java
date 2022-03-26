package com.example.workout_appv1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.WorkoutPlannerDb;
import com.example.workout_appv1.entities.Routine;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    Context context;
    List<Routine> routineList;
    WorkoutPlannerDb database;

    public HomeAdapter(Context context, List<Routine> routineList) {
        this.context = context;
        this.routineList = routineList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Routine routine=routineList.get(position);
        holder.tvRoutineName.setText(routine.getRoutineName());

    }

    @Override
    public int getItemCount() {
        return routineList.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoutineName;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoutineName=itemView.findViewById(R.id.tvRoutineName);
        }
    }
}
