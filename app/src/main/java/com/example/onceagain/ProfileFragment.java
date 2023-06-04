package com.example.onceagain;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class ProfileFragment extends Fragment {

    private MaterialButton EditProfile;
    private TextView Username, Phone, Address, Password, Email, TitleName;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        Username = view.findViewById(R.id.profile_username);
        Phone = view.findViewById(R.id.profile_phone);
        Address = view.findViewById(R.id.profile_address);
        Password = view.findViewById(R.id.profile_pass);
        Email = view.findViewById(R.id.profile_email);
        TitleName = view.findViewById(R.id.title_name);
        EditProfile = view.findViewById(R.id.editProfile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            String phone = user.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference("users").child(phone);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        String email = snapshot.child("email").getValue(String.class);
                        String password = snapshot.child("password").getValue(String.class);
                        String username = snapshot.child("username").getValue(String.class);
                        String address = snapshot.child("address").getValue(String.class);

                        Username.setText(username);
                        Email.setText(email);
                        Password.setText(password);
                        Address.setText(address);
                        TitleName.setText(username);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }



//        Bundle bundle = getArguments();
//        if (bundle != null){
//            String phone = bundle.getString("phone");
//
//            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(phone);
//            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    String email = snapshot.child("email").getValue(String.class);
//                    String password = snapshot.child("password").getValue(String.class);
//                    String username = snapshot.child("username").getValue(String.class);
//                    String address = snapshot.child("address").getValue(String.class);
//
//                    Username.setText(username);
//                    Email.setText(email);
//                    Password.setText(password);
//                    Address.setText(address);
//                    TitleName.setText(username);
//                    Phone.setText(phone);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }


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
