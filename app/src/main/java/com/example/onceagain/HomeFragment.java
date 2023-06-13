package com.example.onceagain;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;

public class HomeFragment extends Fragment {

    CardView tabletCard, Books, Cars, Electronics;
    Fragment selectedCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        Books = view.findViewById(R.id.books);
        Cars = view.findViewById(R.id.cars);
        Electronics = view.findViewById(R.id.electronics);

        Books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCategoryFragment("books");
            }
        });

        Cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCategoryFragment("cars");
            }
        });

        Electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCategoryFragment("electronics");
            }
        });

        return view;
    }




    private void loadCategoryFragment(String category) {
        Fragment categoryFragment;

        Bundle bundle = new Bundle();
        bundle.putString("category", category);

        switch (category) {
            case "cars":
                categoryFragment = new Cars_Fragment();
                break;
            case "books":
                categoryFragment = new Books_Fragment();
                break;
            case "electronics":
                categoryFragment = new Electronics_Fragment();
                break;
            default:
                return;
        }

        categoryFragment.setArguments(bundle);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container2, categoryFragment).addToBackStack(null).commit();
    }
}
