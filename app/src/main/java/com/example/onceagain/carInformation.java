package com.example.onceagain;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class carInformation extends AppCompatActivity {

    TextView carType;
    ImageButton productImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_information);

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
                        carType.setText(carTypes[which]);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        productImg = findViewById(R.id.productImg);
        productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraHardware(carInformation.this);
            }
        });
    }


    public boolean checkCameraHardware (Context context){
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            try {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
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
}