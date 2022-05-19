package com.example.workout_appv1.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.entities.Routine;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private final IOnHomeActionListener listener;
    private List<Routine> routineList = new ArrayList<>();

    public HomeAdapter(IOnHomeActionListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fragment_home, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.bind(routineList.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return routineList.size();
    }

    public void setRoutineList(List<Routine> routineList) {
        this.routineList = routineList;
        notifyDataSetChanged();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayHomeItem;
        TextView tvNameHomeItem;
        ConstraintLayout clHomeItem;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvDayHomeItem = itemView.findViewById(R.id.tvDayHomeItem);
            this.tvNameHomeItem = itemView.findViewById(R.id.tvNameHomeItem);
            this.clHomeItem = itemView.findViewById(R.id.clHomeItem);
        }

        public void bind(Routine routine, IOnHomeActionListener onHomeAction) {
            this.tvNameHomeItem.setText(routine.getRoutineName());
            this.tvDayHomeItem.setText(onHomeAction.getDayShortcut(routine));
            this.clHomeItem.setOnClickListener(view -> onHomeAction.onItemClick(routine));

        }
    }

    public interface IOnHomeActionListener {
        String getDayShortcut(Routine routine);

        void onItemClick(Routine routine);

    }
}
