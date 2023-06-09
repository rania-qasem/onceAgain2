package com.example.onceagain;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.Calendar;

public class carInformation extends AppCompatActivity {

    TextView carType;
    ImageButton productImg;
    MaterialButton SaveCar;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://once-again-33-default-rtdb.firebaseio.com/");
    String Car_Type, model, transmission, fuel, color, condition, kilometers, paint, body_condition, payment_method, price, description;
    EditText Model, Transmission, Fuel, Color, Condition, Kilometers, Paint, Body_Condition, Payment_Method, Price, Description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_information);

        Model = findViewById(R.id.model);
        carType = findViewById(R.id.carType);
        Transmission= findViewById(R.id.Transmission);
        Fuel=findViewById(R.id.Fuel);
        Color=findViewById(R.id.Color);
        Condition= findViewById(R.id.Condition);
        Kilometers=findViewById(R.id.Kilometers);
        Paint=findViewById(R.id.Paint);
        Body_Condition=findViewById(R.id.Body_Condition);
        Payment_Method=findViewById(R.id.Payment_Method);
        Price= findViewById(R.id.Price);
        Description=findViewById(R.id.Description);

        //Create an alertDialog for carMake and show it
        carType = findViewById(R.id.carType);
        carType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(carInformation.this);
                builder.setTitle("Car Type");
                String[] carTypes = {"BMW", "Abarath", "Acura", "Alfa Romeo", "Aston Martin", "Audi", "BAIC", "BYD", "Bentley", "Bestune", "Borgward", "Brilliance", "Bugatti", "Buick", "CMC", "Cadillac", "Changan", "Chery", "Chevrolet", "Chrysler", "Citroen", "DFM", "Daewoo", "Daihatsu", "Dayun", "Dodge", "Dongfeng", "Dorcen", "FAW", "Ferrari", "Fiat", "Fisker", "Force", "Ford", "Foton", "GAC", "GMC", "Geely", "Great Wall", "Hafei", "Haval", "Hawtai", "Higer", "Honda", "Hongqi", "Hummer", "Hunaghai", "Hyundai", "Infiniti", "Iran Khodro", "Isuzu", "Iveco", "JMC", "Jac", "Jaguar", "Jeep", "Jetour", "Jinbei", "Kaiyi", "Kia", "Lada", "Lamborghini", "Lancia", "Land Rover", "Lexus", "Lifan", "Lincoln", "Lotus", "MG", "MINI", "Mahindra", "Maruti Suzuki", "Maserati", "Maxus", "Mazda", "McLaren", "Mercedes Benz", "Mercury", "Mitsubishi", "MitsuoKa", "Morgan", "NETA", "Nissan", "Opel", "Pagani", "Peugeot", "Polestar", "Pontiac", "Porsche", "Private", "Proton", "Renault", "Rolls Royce", "Rover", "SAIPA", "SEAT", "Saab", "Saic", "Samsung", "Saturn", "Scion", "Skoda", "Skywell", "Smart", "Soueast", "SsangYong", "Subaru", "Suzuki", "TATA", "Tesla", "Toyota", "UAZ", "Volkswagen", "Volvo", "ZXAUTO", "Zotye"};
                builder.setItems(carTypes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Car_Type = carTypes[which];
                        carType.setText(carTypes[which]);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        SaveCar = findViewById(R.id.save_car);
        SaveCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model = Model.getText().toString();
                transmission= Transmission.getText().toString();
                fuel = Fuel.getText().toString();
                color = Color.getText().toString();
                condition = Condition.getText().toString();
                kilometers = Kilometers.getText().toString();
                paint = Paint.getText().toString();
                body_condition= Body_Condition.getText().toString();
                payment_method= Payment_Method.getText().toString();
                price= Price.getText().toString();
                description= Description.getText().toString();


                databaseReference.child("Cars").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String currentUserId = getCurrentUserId();
                        String CarId = databaseReference.child("Cars").push().getKey();

                        /////////
                        Car car = new Car();
                        car.setAdId(CarId);
                        car.setUserId(currentUserId);
                        car.setModel(model);
                        car.setTransmission(transmission);
                        car.setFuel(fuel);
                        car.setColor(color);
                        car.setCondition(condition);
                        car.setKilometers(kilometers);
                        car.setPaint(paint);
                        car.setBody_Condition(body_condition);
                        car.setPayment_Method(payment_method);
                        car.setPrice(price);
                        car.setDescription(description);

                        databaseReference.child("Cars").child(CarId).setValue(car).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Car saved successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(carInformation.this, Home.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(getApplicationContext(), "Failed to save car", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        productImg = findViewById(R.id.productImg);
        productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraHardware(carInformation.this);
            }
        });
    }//End of onCreate


    public boolean checkCameraHardware (Context context){
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            try {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mStartForResult.launch(cameraIntent);
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Camera is not available", Toast.LENGTH_SHORT).show();
            }
            return true;
        }else {
            Toast.makeText(getApplicationContext(), "No camera on this device", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        // Handle the Intent
                        Bitmap photo = (Bitmap) intent.getExtras().get("data");
                        productImg.setImageBitmap(photo);
                    }
                }
            });

    private String getCurrentUserId() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("shared", getApplicationContext().MODE_PRIVATE);
        String userId = sharedPref.getString("user","null");

        return userId;
    }
}//End of Class