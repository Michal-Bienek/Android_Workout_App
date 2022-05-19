package com.example.workout_appv1.ui.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workout_appv1.R;
import com.example.workout_appv1.ui.adapters.HomeAdapter;
import com.example.workout_appv1.data.entities.Routine;
import com.example.workout_appv1.viewmodels.FragmentHomeViewModel;

public class FragmentHome extends Fragment {
    private Context context;

    public FragmentHome() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        FragmentHomeViewModel viewModel = new ViewModelProvider(this).get(FragmentHomeViewModel.class);

        //Set recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.rvHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        HomeAdapter adapter = new HomeAdapter(new HomeAdapter.IOnHomeActionListener() {
            @Override
            public String getDayShortcut(Routine routine) {
                return viewModel.getDayShortcut(routine.getDayOfWeek());
            }

            @Override
            public void onItemClick(Routine routine) {
                NavController navController = getNavController();
                NavDirections action = FragmentHomeDirections.actionFragmentHomeToFragmentRoutine(routine.getRoutineId());
                navController.navigate(action);
            }
        });
        recyclerView.setAdapter(adapter);
        viewModel.getRoutinesFromActivePlans().observe(getViewLifecycleOwner(), adapter::setRoutineList);
        return view;
    }

    private NavController getNavController() {
        return NavHostFragment.findNavController(this);
    }

}