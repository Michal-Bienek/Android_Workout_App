package com.example.workout_appv1.ui.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.example.workout_appv1.data.WorkoutPlannerDb;
import com.example.workout_appv1.ui.adapters.HomeAdapter;
import com.example.workout_appv1.data.entities.Routine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {


    public FragmentHome() {
        // Required empty public constructor
    }

    public static FragmentHome newInstance() {
        return new FragmentHome();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        initRecyclerView(view);
        return view;
    }


    private void initRecyclerView(View view){
//        WorkoutPlannerDb database=WorkoutPlannerDb.getInstance(getActivity());
//        //List<Routine> routineList = database.routineDao().getRoutinesFromActivePlans();
//        HomeAdapter adapter=new HomeAdapter(getActivity(),routineList);
//        Toast.makeText(getActivity(), ""+routineList.size(), Toast.LENGTH_SHORT).show();
//
//        RecyclerView recyclerView=view.findViewById(R.id.rvHome);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);

    }
}