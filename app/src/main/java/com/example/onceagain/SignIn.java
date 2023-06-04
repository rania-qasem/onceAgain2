package com.example.onceagain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    TextInputLayout Phone, Password;
    MaterialButton SignIn;
    String phone, password;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://once-again-33-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Phone = findViewById(R.id.phone);
        Password = findViewById(R.id.password1);
        SignIn = findViewById(R.id.signIn);

        //get data from EditTexts into String variables
        password = Password.getEditText().getText().toString();
        phone = Phone.getEditText().getText().toString();

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!PhoneValidation() || !PasswordValidation()){
                    return;
                }else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check if phone is exist in firebase database
                            if (snapshot.hasChild(phone)){
                                //mobile is exist in firebase database
                                //now get password of user from firebase data and match it with user entered password
                                final String getPassword = snapshot.child(phone).child("password").getValue(String.class);

                                if (getPassword.equals(password)){
                                    Toast.makeText(SignIn.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();

                                    //open Home activity on success
                                    startActivity(new Intent(getApplicationContext(), Home.class));
                                    finish();

                                }else {
                                    Toast.makeText(SignIn.this, "Wrong Password", Toast.LENGTH_SHORT).show();

                                }
                            }else {
                                Toast.makeText(SignIn.this, "Wrong Phone", Toast.LENGTH_SHORT).show();

                            }


                            //pass the userID(phone) to profile fragment
                            Bundle bundle = new Bundle();
                            bundle.putString("phone", phone);

                            ProfileFragment fragment = new ProfileFragment();
                            fragment.setArguments(bundle);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }


    boolean PhoneValidation () {
        phone = Phone.getEditText().getText().toString().trim();
        String validID = phone.substring(0, 2);
        if (phone.isEmpty()){
            Phone.setError("This field should not be Empty");
            return false;
        }else if (phone.length() != 9){
            Phone.setError("The number is not Exist!");
            return false;
        }else if (!validID.equals("59") && !validID.equals("56")){
            Phone.setError("The number is not Exist!");
            return false;
        }else {
            Phone.setErrorEnabled(false);
            return true;
        }
    }

    boolean PasswordValidation () {
        password = Password.getEditText().getText().toString().trim();
        if (password.isEmpty()){
            Password.setError("This field should not be Empty!");
            return false;
        }else if (password.length() > 8){
            Password.setError("Password should not be grater than 8 character!");
            return false;
        }else {
            Password.setErrorEnabled(false);
            return true;
        }
    }
}