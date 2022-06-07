package com.example.workout_appv1.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.ui.views.dialogs.DialogAddEditRoutine;
import com.example.workout_appv1.viewmodels.FragmentPlanViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {
    List<Routine> routines = new ArrayList<>();
    Context context;
    private final IOnRoutineAction mIOnRoutineAction;

    public PlanAdapter(Context context, IOnRoutineAction mIOnRoutineAction) {
        this.context = context;
        this.mIOnRoutineAction = mIOnRoutineAction;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fragment_plan, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(routines.get(position), mIOnRoutineAction);
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayPlanItem, tvNamePlanItem,tvDate;
        ImageView btnMorePlanItem;
        ConstraintLayout clPlanItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvDayPlanItem = itemView.findViewById(R.id.tvDayPlanItem);
            this.tvNamePlanItem = itemView.findViewById(R.id.tvNamePlanItem);
            this.btnMorePlanItem = itemView.findViewById(R.id.btnMorePlanItem);
            this.clPlanItem = itemView.findViewById(R.id.clPlanItem);
            this.tvDate = itemView.findViewById(R.id.tvDataItemPlan);
        }

        @SuppressLint("NonConstantResourceId")
        public void bind(Routine routine, IOnRoutineAction onRoutineAction) {
            this.tvNamePlanItem.setText(routine.getRoutineName());
            this.tvDayPlanItem.setText(onRoutineAction.getDayShortcut(routine));
            this.btnMorePlanItem.setOnClickListener(view -> {
                PopupMenu popupMenu = new PopupMenu(context, btnMorePlanItem);
                popupMenu.inflate(R.menu.item_popup_menu);
                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.miEdit:
                            onRoutineAction.onRoutineEdit(routine);
                            return true;
                        case R.id.miDelete:
                            onRoutineAction.onRoutineDelete(routine);
                            return true;
                        default:
                            return false;
                    }
                });
                popupMenu.show();
            });
            if(routine.getLastWorkoutDate()!=null){
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/y HH:mm");
                String tmp = dateFormat.format(routine.getLastWorkoutDate());
                this.tvDate.setText(tmp);
            }
            this.clPlanItem.setOnClickListener(view -> onRoutineAction.onRoutineClick(routine));

        }
    }

    public interface IOnRoutineAction {
        void onRoutineClick(Routine routine);

        void onRoutineEdit(Routine routine);

        void onRoutineDelete(Routine routine);

        String getDayShortcut(Routine routine);
    }
}