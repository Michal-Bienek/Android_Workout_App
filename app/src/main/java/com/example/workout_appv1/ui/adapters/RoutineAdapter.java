package com.example.workout_appv1.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.joinEntities.ExerciseInRoutineExercise;

import java.util.ArrayList;
import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder> {
    private List<ExerciseInRoutineExercise> exerciseInRoutineExerciseList = new ArrayList<>();
    private Context context;
    private final IOnExerciseInRoutineAction onExerciseInRoutine;

    public RoutineAdapter(Context context, IOnExerciseInRoutineAction onExerciseInRoutine) {
        this.context = context;
        this.onExerciseInRoutine = onExerciseInRoutine;
    }

    @NonNull
    @Override
    public RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fragment_routine,parent,false);
        return new RoutineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder holder, int position) {
        holder.bind(exerciseInRoutineExerciseList.get(position),this.onExerciseInRoutine);
    }

    @Override
    public int getItemCount() {
        return exerciseInRoutineExerciseList.size();
    }

    public void setExerciseInRoutineExerciseList(List<ExerciseInRoutineExercise> exerciseInRoutineExerciseList) {
        this.exerciseInRoutineExerciseList = exerciseInRoutineExerciseList;
        notifyDataSetChanged();
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
        public void bind(ExerciseInRoutineExercise exercise, IOnExerciseInRoutineAction onExerciseInRoutineAction){
            this.tvExerciseNameItemRoutine.setText(exercise.exerciseName);
            this.btnMoreItemRoutine.setOnClickListener(view -> {
                PopupMenu popupMenu = new PopupMenu(context,btnMoreItemRoutine);
                popupMenu.inflate(R.menu.item_popup_menu);
                popupMenu.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()){
                        case R.id.miEdit:
                            onExerciseInRoutineAction.onEdit(exercise);
                            return true;
                        case R.id.miDelete:
                            onExerciseInRoutineAction.onDelete(exercise);
                            return true;
                        default:
                            return false;
                    }
                });
                popupMenu.show();
            });
            clRoutineItem.setOnClickListener(view -> onExerciseInRoutineAction.onEdit(exercise));
        }
    }

    public interface IOnExerciseInRoutineAction {
        void onDelete(ExerciseInRoutineExercise exercise);
        void onEdit(ExerciseInRoutineExercise exercise);
    }
}