package com.example.workout_appv1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
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
        database=WorkoutPlannerDb.getInstance(context);
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
        holder.tvProgramItem.setText(p.getPlanName());
        holder.btnMoreProgramItem.setOnClickListener(view -> {
            PopupMenu popup=new PopupMenu(context,holder.btnMoreProgramItem);
            popup.inflate(R.menu.item_popup_menu);
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.miEdit:
                        Toast.makeText(context, "Edytuj", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.miDelete:
                        delete(holder);
                        Toast.makeText(context, "Usuń", Toast.LENGTH_SHORT).show();
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
        return planList.size();
    }

    private void delete(ViewHolder holder){
        Plan plan=planList.get(holder.getAdapterPosition());
        database.planDao().deletePlan(plan);
        int position=holder.getAdapterPosition();
        planList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,planList.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView btnProgramItem;
        TextView tvProgramItem;
        ImageView btnMoreProgramItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnProgramItem=itemView.findViewById(R.id.btnProgramItem);
            tvProgramItem=itemView.findViewById(R.id.tvProgramItem);
            btnMoreProgramItem=itemView.findViewById(R.id.btnMoreProgramItem);
        }
    }
}
