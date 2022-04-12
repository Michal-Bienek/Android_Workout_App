package com.example.workout_appv1.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Exercise;

import org.w3c.dom.Text;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    List<Exercise> exerciseList;
    Context context;

    public ExerciseAdapter( Context context, List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise,parent,false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise ex= exerciseList.get(position);
        holder.tvNameItemExercise.setText(ex.getName());
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        ImageView imgExercise;
        TextView tvNameItemExercise;
        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            imgExercise=itemView.findViewById(R.id.imgExercise);
            tvNameItemExercise=itemView.findViewById(R.id.tvNameItemExercise);
        }
    }
}
