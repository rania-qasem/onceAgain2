package com.example.onceagain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_ad extends AppCompatActivity {

    MaterialCardView Cars;
    MaterialCardView Books;
    MaterialCardView Electronics;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://once-again-33-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ad);

        Cars = findViewById(R.id.cars);
        Cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(getApplicationContext(), carInformation.class);
                startActivity(intent);
            }
        });

        Books = findViewById(R.id.books);
        Books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), book_Information.class);
                startActivity(intent);
            }
        });

        Electronics = findViewById(R.id.electronics);
        Electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), electronic_Information.class);
                startActivity(intent);
            }
        });
    }
}