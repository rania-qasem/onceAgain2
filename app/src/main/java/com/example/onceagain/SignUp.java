package com.example.onceagain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.google.gson.Gson;

public class SignUp extends AppCompatActivity {

    TextInputLayout Email, Password, Username, Phone, Address;
    String email, password, username, phone, address;
    MaterialButton SignUp, JoinNow;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://once-again-33-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Shared preferences
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("shared", getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        User currUser = new User();

        SignUp = findViewById(R.id.signUp);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Username = findViewById(R.id.username);
        Phone = findViewById(R.id.phone);
        Address = findViewById(R.id.location);
        JoinNow = findViewById(R.id.join_now);

        //get data from EditTexts into String variables
        email = Email.getEditText().getText().toString();
        password = Password.getEditText().getText().toString();
        username = Username.getEditText().getText().toString();
        phone = Phone.getEditText().getText().toString();
        address = Address.getEditText().getText().toString();

        //Loading dialog
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                //check the constrains on the fields before sending data to firebase
                if (!UsernameValidation() || !PasswordValidation() || !PhoneValidation() || !EmailValidation() || !AddressValidation()){
                    progressDialog.dismiss();
                    return;
                }
                else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            progressDialog.dismiss();

                            //check phone is not registered before
                            if (snapshot.hasChild(phone)){
                                Toast.makeText(SignUp.this, "User is already registered!", Toast.LENGTH_SHORT).show();
                            }else {
                                //sending data to firebase Realtime Database
                                //using phone number as unique identity of every user
                                databaseReference.child("users").child(phone).child("email").setValue(email);
                                databaseReference.child("users").child(phone).child("password").setValue(password);
                                databaseReference.child("users").child(phone).child("username").setValue(username);
                                databaseReference.child("users").child(phone).child("address").setValue(address);

                                //show success message then finish activity
                                Toast.makeText(SignUp.this, "User registered successfully.", Toast.LENGTH_SHORT).show();

                                //////////////////////////////////////////////////////////////
                                currUser.setEmailAddress(email);
                                currUser.setFullName(username);
                                currUser.setUserAddress(address);
                                currUser.setPhoneNum(phone);
                                String userJson = gson.toJson(currUser);
                                editor.putString("user", userJson);
                                editor.commit();

                                //open Home activity
                                Intent intent = new Intent(getApplicationContext(), Home.class);
                                startActivity(intent);
                                finish();

                                Toast.makeText(SignUp.this, "we reached here - this code will not execute", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progressDialog.dismiss();
                        }
                    });

                }
            }
        });

        //open SignIn activity
        JoinNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
            }
        });
    }//End of onCreate









//    public void sharedData(String email,String password,String username,String phone,String address){
//        editor.putString("full_name", username);
//        editor.putString("phone_number", phone);
//        editor.putString("location", address);
//        editor.putString("password", password);
//        editor.putString("email", email);
////        editor.commit();
////        editor.apply();
//    }

    boolean EmailValidation () {
        email = Email.getEditText().getText().toString().trim();
        if (email.isEmpty()){
            Email.setError("This field should not be Empty!");
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Your email is not correct!");
            return false;
        }else {
            Email.setErrorEnabled(false);
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

    boolean UsernameValidation () {
        username = Username.getEditText().getText().toString().trim();
        if (username.isEmpty()){
            Username.setError("This field should not be Empty");
            return false;
        }else if (username.length() > 20){
            Username.setError("Username should not be grater than 8 character!");
            return false;
        }else {
            Username.setErrorEnabled(false);
            return true;
        }
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

    boolean AddressValidation(){
        address = Address.getEditText().getText().toString().trim();
        if (address.isEmpty()){
            Address.setError("This field should not be Empty!");
            return false;
        }
        else{
            Address.setErrorEnabled(false);
            return true;
        }
    }
}//End of class