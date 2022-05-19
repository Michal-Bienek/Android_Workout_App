package com.example.workout_appv1.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.entities.Routine;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    Context context;
    List<Routine> routineList= new ArrayList<>();

    public HomeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fragment_home,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Routine routine=routineList.get(position);
        holder.tvNameHomeItem.setText(routine.getRoutineName());

    }

    @Override
    public int getItemCount() {
        return routineList.size();
    }

    private void setRoutineList(List<Routine>routineList){
        this.routineList = routineList;
        notifyDataSetChanged();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayHomeItem;
        TextView tvNameHomeItem;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvDayHomeItem = itemView.findViewById(R.id.tvDayHomeItem);
            this.tvNameHomeItem = itemView.findViewById(R.id.tvNameHomeItem);
        }
    }
}
