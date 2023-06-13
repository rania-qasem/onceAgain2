package com.example.onceagain;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Cars_Fragment extends Fragment {
    private ListView listView;
    private List<Car> carList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cars_fragment, container, false);

        listView = view.findViewById(R.id.list_view);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("carInfo",getActivity().MODE_PRIVATE);
        Gson gson = new Gson();
        int carCount = sharedPref.getAll().size();

        carList = new ArrayList<Car>();
        for (int i = 0; i < carCount; i++) {
            String carKey = "car" + i;
            String carJson = sharedPref.getString(carKey, "");
            Car car = gson.fromJson(carJson, Car.class);
            carList.add(car);
        }



        Car_Adapter carAdapter = new Car_Adapter(getActivity(), carList);
        listView.setAdapter(carAdapter);

        return view;
    }
}
