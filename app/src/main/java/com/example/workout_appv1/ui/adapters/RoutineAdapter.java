package com.example.workout_appv1.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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
    private IOnExerciseInRoutine onExerciseInRoutine;

    public RoutineAdapter(Context context, IOnExerciseInRoutine onExerciseInRoutine) {
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
        ExerciseInRoutineExercise exercise = exerciseInRoutineExerciseList.get(position);
        holder.tvExerciseNameItemRoutine.setText(exercise.exerciseName);
        holder.btnMoreItemRoutine.setOnClickListener(view -> {
            PopupMenu popup=new PopupMenu(context,holder.btnMoreItemRoutine);
            popup.inflate(R.menu.item_popup_menu);
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.miEdit:
                        update(holder);
                        return true;
                    case R.id.miDelete:
                        delete(holder);
                        return true;
                    default:
                        return false;
                }
            });
            popup.show();
        });

    }

    @Override
    public int getItemCount() {
        return exerciseInRoutineExerciseList.size();
    }

    public void setExerciseInRoutineExerciseList(List<ExerciseInRoutineExercise> exerciseInRoutineExerciseList) {
        this.exerciseInRoutineExerciseList = exerciseInRoutineExerciseList;
        notifyDataSetChanged();
    }

    private void delete(RoutineAdapter.RoutineViewHolder holder){
        ExerciseInRoutineExercise exercise = this.exerciseInRoutineExerciseList.get(holder.getAdapterPosition());
        onExerciseInRoutine.onDelete(exercise.exInRoutineId);
    }

    private void update(RoutineAdapter.RoutineViewHolder holder){
        ExerciseInRoutineExercise exercise = this.exerciseInRoutineExerciseList.get(holder.getAdapterPosition());
        onExerciseInRoutine.onEdit(exercise);
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

    public interface IOnExerciseInRoutine{
        void onDelete(int exerciseInRoutineId);
        void onEdit(ExerciseInRoutineExercise exercise);
    }
}
