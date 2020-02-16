package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class DescriptionActivity extends AppCompatActivity {
    Button imageButton;
    public static  final int CAMERA_REQUEST = 1888;
    public static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

imageButton = findViewById(R.id.image_button);
imageButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
//        if(checkSelfPermission((Manifest.permission.CAMERA)) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
//        }
//        else{
//            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(cameraIntent,CAMERA_REQUEST);
//        }
    }
});


    }
}
