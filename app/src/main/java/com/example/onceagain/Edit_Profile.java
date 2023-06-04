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
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class Edit_Profile extends AppCompatActivity {

    MaterialButton TakeProfilePhoto;
    CircleImageView ProfilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        TakeProfilePhoto = findViewById(R.id.takeProfilePhoto);
        ProfilePhoto = findViewById(R.id.circleImageView);

        TakeProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraHardware(Edit_Profile.this);
            }
        });
    }//End of onCreate


    public boolean checkCameraHardware (Context context){
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            try {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
                mStartForResult.launch(cameraIntent);
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Camera is not available", Toast.LENGTH_LONG).show();
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
                        ProfilePhoto.setImageBitmap(photo);
                    }
                }
            });
}//End of class