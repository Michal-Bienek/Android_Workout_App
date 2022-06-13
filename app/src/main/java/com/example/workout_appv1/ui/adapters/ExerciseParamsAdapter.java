package com.example.workout_appv1.ui.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.helpers.CustomTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class ExerciseParamsAdapter extends RecyclerView.Adapter<ExerciseParamsAdapter.MyViewHolder> {
    Context context;
    List<Series> seriesList = new ArrayList<>();
    OnEtParamsChanged onEtParamsChanged;

    public ExerciseParamsAdapter(Context context, OnEtParamsChanged onEtParamsChanged) {
        this.context = context;
        this.onEtParamsChanged = onEtParamsChanged;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_dialog_add_exercise_to_routine, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(seriesList.get(position), position, onEtParamsChanged);
    }

    public void setSeriesList(List<Series> seriesList) {
        int difference = this.seriesList.size() - seriesList.size();
        this.seriesList = seriesList;
        if (difference == -1) {
            notifyItemInserted(seriesList.size() - 1);
        } else if (difference == 1) {
            notifyItemRemoved(seriesList.size());
        } else {
            notifyDataSetChanged();
        }

    }

    public List<Series> getSeriesList() {
        return seriesList;
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout;
        TextView tvReps, tvWeight, tvRestTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvReps = itemView.findViewById(R.id.tvRepsDialogAddExercise);
            this.tvWeight = itemView.findViewById(R.id.tvWeightDialogAddExercise);
            this.tvRestTime = itemView.findViewById(R.id.tvRestTimeDialogAddExercise);
            this.constraintLayout = itemView.findViewById(R.id.clParamLayout);
        }

        public void bind(Series series, int position, OnEtParamsChanged onEtParamsChanged) {
            tvReps.setText(String.valueOf(series.getReps()));
            tvRestTime.setText(String.valueOf(series.getRestTime()));
            tvWeight.setText(String.valueOf(series.getWeight()));
            constraintLayout.setOnClickListener(v -> {
                onEtParamsChanged.onParamsChanged(seriesList, position);
                Toast.makeText(context, "klinięto", Toast.LENGTH_SHORT).show();
            });
        }
    }

    public interface OnEtParamsChanged {
        void onParamsChanged(List<Series> seriesList, int position);
    }
}
