package com.example.onceagain;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class ProfileFragment extends Fragment {

    private MaterialButton EditProfile;
    private TextView Username, Phone, Address, Password, Email, TitleName;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        SharedPreferences sharedPref =getActivity().getSharedPreferences("shared",getActivity().MODE_PRIVATE);
        Gson gson = new Gson();
        String userJson = sharedPref.getString("user","null");
        User currUser = gson.fromJson(userJson,User.class);

        Username = view.findViewById(R.id.profile_username);
        Phone = view.findViewById(R.id.profile_phone);
        Address = view.findViewById(R.id.profile_address);
        Password = view.findViewById(R.id.profile_pass);
        Email = view.findViewById(R.id.profile_email);
        TitleName = view.findViewById(R.id.title_name);
        EditProfile = view.findViewById(R.id.editProfile);

        Username.setText(currUser.getFullName());
        Phone.setText(currUser.getPhoneNum());
        Address.setText(currUser.getUserAddress());
        Email.setText(currUser.getEmailAdress());
        TitleName.setText(currUser.getFullName());

        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Edit_Profile.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
