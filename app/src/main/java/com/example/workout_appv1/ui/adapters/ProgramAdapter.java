package com.example.workout_appv1.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import com.example.workout_appv1.data.entities.Plan;
import com.example.workout_appv1.ui.views.dialogs.DialogAddEditPlan;
import com.example.workout_appv1.viewmodels.FragmentProgramViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {
    private final Context context;
    private List<Plan> planList = new ArrayList<>();
    private final IOnPlanListener mIOnPlanListener;


    public ProgramAdapter(Context context, IOnPlanListener mIOnPlanListener) {
        this.context = context;
        this.mIOnPlanListener = mIOnPlanListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fragment_program, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(planList.get(position), mIOnPlanListener);
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return planList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView btnProgramItem;
        TextView tvProgramItem;
        TextView tvGoal;
        ImageView btnMoreProgramItem, imgActive;
        ConstraintLayout clProgramItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnProgramItem = itemView.findViewById(R.id.imgProgramIcon);
            tvProgramItem = itemView.findViewById(R.id.tvProgramItem);
            btnMoreProgramItem = itemView.findViewById(R.id.btnMoreProgramItem);
            clProgramItem = itemView.findViewById(R.id.clProgramItem);
            tvGoal = itemView.findViewById(R.id.tvProgramGoal);
            imgActive = itemView.findViewById(R.id.imgActiveBulb);
        }

        @SuppressLint("NonConstantResourceId")
        public void bind(Plan plan, IOnPlanListener listener) {
            tvProgramItem.setText(plan.getPlanName());
            tvGoal.setText(plan.getGoal());
            btnMoreProgramItem.setOnClickListener(view -> {
                PopupMenu popupMenu = new PopupMenu(context, btnMoreProgramItem);
                popupMenu.inflate(R.menu.item_popup_menu);
                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.miEdit:
                            listener.onEditPlan(plan);
                            return true;
                        case R.id.miDelete:
                            listener.onPlanDelete(plan);
                            return true;
                        default:
                            return false;
                    }
                });
                popupMenu.show();
            });
            if(plan.isActive()){
                imgActive.setVisibility(View.VISIBLE);
            }
            else{
                imgActive.setVisibility(View.GONE);
            }
            clProgramItem.setOnClickListener(view -> listener.onPlanClick(plan));
        }
    }

    public interface IOnPlanListener {
        void onPlanClick(Plan plan);

        void onPlanDelete(Plan plan);

        void onEditPlan(Plan plan);
    }
}
