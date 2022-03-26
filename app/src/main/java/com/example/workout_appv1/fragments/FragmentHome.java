package com.example.workout_appv1.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.workout_appv1.R;
import com.example.workout_appv1.WorkoutPlannerDb;
import com.example.workout_appv1.adapters.HomeAdapter;
import com.example.workout_appv1.entities.Routine;

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
        WorkoutPlannerDb database=WorkoutPlannerDb.getInstance(getActivity());
        List<Routine> routineList = getSampleData();
        HomeAdapter adapter=new HomeAdapter(getActivity(),routineList);
        Toast.makeText(getActivity(), ""+routineList.size(), Toast.LENGTH_SHORT).show();

        RecyclerView recyclerView=view.findViewById(R.id.rvHome);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
    private List<Routine>getSampleData(){
        List<Routine>routineList=new ArrayList<>();
        Date d=new Date();
        Routine r1=new Routine(1,"Testowa rutyna",1,d,1);
        Routine r2=new Routine(2,"Testowa rut",1,d,1);
        routineList.add(r1);
        routineList.add(r2);
        return routineList;

    }
}