package com.example.workout_appv1.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.WorkoutPlannerDb;
import com.example.workout_appv1.entities.Plan;

import java.util.List;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {
    Context context;
    List<Plan> planList;
    WorkoutPlannerDb database;
    private final OnPlanListener mOnPlanListener;

    public ProgramAdapter(Context context, List<Plan> planList, OnPlanListener mOnPlanListener) {
        this.context = context;
        this.planList = planList;
        this.mOnPlanListener = mOnPlanListener;
        database=WorkoutPlannerDb.getInstance(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_program,parent,false);
        return new ViewHolder(view,mOnPlanListener);
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
                        update(holder);
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

    private void update(ViewHolder holder){
        int position=holder.getAdapterPosition();
        Plan plan=planList.get(position);

        //Initialize dialog
        Dialog dialog = new Dialog(context);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.program_dialog);
        //Initialize Window Manager
        WindowManager.LayoutParams lp=new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width=WindowManager.LayoutParams.MATCH_PARENT;
        //Initialize dialog Variables
        EditText etNameProgramDialog=dialog.findViewById(R.id.etNameProgramDialog);
        EditText etGoalProgramDialog= dialog.findViewById(R.id.etGoalProgramDialog);
        CheckBox cbProgramDialog=dialog.findViewById(R.id.cbProgramDialog);
        Button btnCancelDialogProgram=dialog.findViewById(R.id.btnCancelDialogProgram);
        Button btnAddDialogProgram= dialog.findViewById(R.id.btnAddDialogProgram);
        btnAddDialogProgram.setText("Aktualizuj");
        etNameProgramDialog.setText(plan.getPlanName());
        etGoalProgramDialog.setText(plan.getGoal());
        cbProgramDialog.setChecked(plan.isActive());
        btnAddDialogProgram.setOnClickListener(view -> {
            String name=etNameProgramDialog.getText().toString();
            String goal=etGoalProgramDialog.getText().toString();
            if(!name.equals("")&&!goal.equals("")){
                plan.setPlanName(name);
                plan.setGoal(goal);
                plan.setActive(cbProgramDialog.isChecked());
                planList.set(position,plan);
                database.planDao().updatePlan(plan);
                notifyItemChanged(position);
            }
            dialog.dismiss();
        });
        btnCancelDialogProgram.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
        dialog.getWindow().setAttributes(lp);

    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView btnProgramItem;
        TextView tvProgramItem;
        ImageView btnMoreProgramItem;
        ConstraintLayout clProgramItem;
        OnPlanListener onPlanListener;
        public ViewHolder(@NonNull View itemView, OnPlanListener onPlanListener) {
            super(itemView);
            btnProgramItem=itemView.findViewById(R.id.btnProgramItem);
            tvProgramItem=itemView.findViewById(R.id.tvProgramItem);
            btnMoreProgramItem=itemView.findViewById(R.id.btnMoreProgramItem);
            clProgramItem=itemView.findViewById(R.id.clProgramItem);
            this.onPlanListener=onPlanListener;

            clProgramItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onPlanListener.onPlanClick(getAdapterPosition());

        }
    }

    public interface OnPlanListener{
        void onPlanClick(int position);
    }
}
