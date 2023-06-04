package com.example.onceagain;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class book_Information extends AppCompatActivity {

    ImageButton productImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        productImg = findViewById(R.id.productImg);
        productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraHardware(book_Information.this);
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