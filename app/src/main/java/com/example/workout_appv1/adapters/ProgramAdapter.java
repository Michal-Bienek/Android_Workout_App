package com.example.workout_appv1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.WorkoutPlannerDb;
import com.example.workout_appv1.entities.Plan;

import java.util.List;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {
    Context context;
    List<Plan> planList;
    WorkoutPlannerDb database;

    public ProgramAdapter(Context context, List<Plan> planList) {
        this.context = context;
        this.planList = planList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_program,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Plan p=planList.get(position);
        database=WorkoutPlannerDb.getInstance(context);
        holder.tvProgramItem.setText(p.getPlanName());
        holder.switchProgram.setChecked(p.isActive());
        holder.switchProgram.setOnCheckedChangeListener((compoundButton, b) -> {
            database.planDao().updatePlanStatus(p.getPlanId(),b);
        });

    }

    @Override
    public int getItemCount() {
        return planList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView btnProgramItem;
        TextView tvProgramItem;
        SwitchCompat switchProgram;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnProgramItem=itemView.findViewById(R.id.btnProgramItem);
            tvProgramItem=itemView.findViewById(R.id.tvProgramItem);
            switchProgram=itemView.findViewById(R.id.switchProgram);
        }
    }
}
