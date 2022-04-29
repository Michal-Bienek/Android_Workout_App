package com.example.workout_appv1.ui.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.ui.views.dialogs.DialogAddEditRoutine;
import com.example.workout_appv1.viewmodels.FragmentPlanViewModel;

import java.util.ArrayList;
import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {
    List<Routine>routines=new ArrayList<>();
    Context context;
    String[]dayShortCuts;
    FragmentPlanViewModel viewModel;
    private final OnRoutineClickListener onRoutineClickListener;

    public PlanAdapter( Context context, String[] dayShortCuts,OnRoutineClickListener onRoutineClickListener) {
        this.context = context;
        this.dayShortCuts = dayShortCuts;
        this.onRoutineClickListener=onRoutineClickListener;
        this.viewModel = new ViewModelProvider((FragmentActivity)context).get(FragmentPlanViewModel.class);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item,parent,false);
        return new MyViewHolder(view,onRoutineClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Routine routine=routines.get(position);
        holder.tvNamePlanItem.setText(routine.getRoutineName());
        holder.tvDayPlanItem.setText(getDayShortcut(routine.getDayOfWeek()));
        holder.btnMorePlanItem.setOnClickListener(view -> {
            showPopupMenu(holder);
        });
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    private String getDayShortcut(int day_of_week){
        String shortcut="";
        if(day_of_week<=6&&day_of_week>=0)
            shortcut=dayShortCuts[day_of_week];
        return shortcut;

    }

    private void showPopupMenu(MyViewHolder holder){
        PopupMenu menu=new PopupMenu(context,holder.btnMorePlanItem);
        menu.inflate(R.menu.item_popup_menu);
        menu.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.miEdit:
                    editRoutine(routines.get(holder.getAdapterPosition()));
                    return true;
                case R.id.miDelete:
                    delete(holder);
                    return true;
                default:
                    return false;
            }
        });
        menu.show();
    }

    public void setRoutines(List<Routine>routines){
        this.routines = routines;
        notifyDataSetChanged();
    }

    private void delete(MyViewHolder holder){
        int position=holder.getAdapterPosition();
        Routine r=routines.get(position);
        viewModel.deleteRoutine(r);
    }
    private void editRoutine(Routine routine){
        DialogAddEditRoutine dialog = DialogAddEditRoutine.newEditInstance(routine.getFk_planId(),routine.getRoutineId());
        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
        dialog.show(fragmentManager,"EditRoutine");
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvDayPlanItem, tvNamePlanItem;
        ImageView btnMorePlanItem;
        ConstraintLayout clPlanItem;
        OnRoutineClickListener onRoutineClickListener;

        public MyViewHolder(@NonNull View itemView,OnRoutineClickListener onRoutineClickListener) {
            super(itemView);
            this.tvDayPlanItem=itemView.findViewById(R.id.tvDayPlanItem);
            this.tvNamePlanItem=itemView.findViewById(R.id.tvNamePlanItem);
            this.btnMorePlanItem=itemView.findViewById(R.id.btnMorePlanItem);
            this.clPlanItem=itemView.findViewById(R.id.clPlanItem);
            this.onRoutineClickListener=onRoutineClickListener;
            this.clPlanItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRoutineClickListener.OnRoutineClick(routines.get(getAdapterPosition()));
        }
    }
    public interface OnRoutineClickListener{
        void OnRoutineClick(Routine routine);
    }
}
