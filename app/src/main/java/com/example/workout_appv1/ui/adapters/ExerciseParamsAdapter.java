package com.example.workout_appv1.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.data.entities.Series;
import com.example.workout_appv1.helpers.ExerciseParamsTextWatcher;
import com.example.workout_appv1.helpers.ValueParser;
import com.google.android.material.internal.TextWatcherAdapter;
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
        holder.bind(seriesList.get(position), onEtParamsChanged);
    }

    public void setSeriesList(List<Series> seriesList) {
        this.seriesList = seriesList;
        notifyDataSetChanged();
    }

    public List<Series> getSeriesList() {
        return seriesList;
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText etReps, etWeight, etRestTime;
        TextInputLayout tilReps, tilWeight, tilRestTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tilReps = itemView.findViewById(R.id.tilRepsDialogAddExercise);
            this.tilWeight = itemView.findViewById(R.id.tilWeightDialogAddExercise);
            this.tilRestTime = itemView.findViewById(R.id.tilRestTimeDialogAddExercise);
            this.etReps = itemView.findViewById(R.id.etRepsDialogExercise);
            this.etWeight = itemView.findViewById(R.id.etWeightDialogAddExercise);
            this.etRestTime = itemView.findViewById(R.id.etRestTimeDialogAddExercise);
        }

        public void bind(Series series, OnEtParamsChanged onEtParamsChanged) {
            etReps.setText(String.valueOf(series.getReps()));
            etRestTime.setText(String.valueOf(series.getRestTime()));
            etWeight.setText(String.valueOf(series.getWeight()));
        }
    }

    public interface OnEtParamsChanged {
        void onParamsChanged(List<Series> seriesList);
    }
}
