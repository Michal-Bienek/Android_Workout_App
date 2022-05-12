package com.example.workout_appv1.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder> {
    @NonNull
    @Override
    public RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fragment_routine,parent,false);
        return new RoutineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RoutineViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout clRoutineItem;
        TextView tvExerciseNameItemRoutine;
        ImageView btnMoreItemRoutine;
        public RoutineViewHolder(@NonNull View itemView) {
            super(itemView);
            this.clRoutineItem = itemView.findViewById(R.id.clRoutineItem);
            this.tvExerciseNameItemRoutine = itemView.findViewById(R.id.tvExerciseNameItemRoutine);
            this.btnMoreItemRoutine = itemView.findViewById(R.id.btnMoreItemRoutine);
        }
    }
}
