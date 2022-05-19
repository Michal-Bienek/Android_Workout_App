package com.example.workout_appv1.ui.adapters;

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
import com.example.workout_appv1.data.entities.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    List<Exercise> exerciseList = new ArrayList<>();
    Context context;
    OnExerciseListener onExerciseListener;

    public ExerciseAdapter( Context context,OnExerciseListener onExerciseListener) {
        this.context = context;
        this.onExerciseListener=onExerciseListener;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fragment_exercise,parent,false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        holder.bind(exerciseList.get(position),onExerciseListener);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public void setExerciseList(List<Exercise>exerciseList){
        this.exerciseList = exerciseList;
        notifyDataSetChanged();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        ImageView imgExercise;
        TextView tvNameItemExercise;
        ConstraintLayout clExerciseItem;
        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            imgExercise=itemView.findViewById(R.id.imgExercise);
            tvNameItemExercise=itemView.findViewById(R.id.tvNameItemExercise);
            clExerciseItem = itemView.findViewById(R.id.clExerciseItem);
        }
        public void bind(Exercise exercise, OnExerciseListener onExerciseListener){
            this.tvNameItemExercise.setText(exercise.getName());
            clExerciseItem.setOnClickListener(view -> onExerciseListener.onExerciseClick(exercise));
        }
    }

    public interface OnExerciseListener{
       void onExerciseClick(Exercise position);
    }
}
