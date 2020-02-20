package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DescriptionActivity extends AppCompatActivity {
    Button imageButton;
    ImageView imageView;
    Uri imageUri;

    private static final int REQUEST_CODE = 1;



    public static  final int CAMERA_REQUEST = 1000;
    public static final int MY_CAMERA_PERMISSION_CODE = 1001;
    DataBaseHelper dataBaseHelper;

    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;

    Location noteLocation;


    @Override
    protected void onStart() {
        super.onStart();

        if(!checkPermission())
            requestPermission();
        else
            getLastLocation();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        final EditText editTextTitle = findViewById(R.id.title_edit_text);
        final EditText editTextDesc= findViewById(R.id.description_edit_text);
        //image capture

        imageButton = findViewById(R.id.chooseimagebtn);
        imageView = findViewById(R.id.image_view);


        Button buttonSave = findViewById(R.id.btn_save_note);


        dataBaseHelper = new DataBaseHelper(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        buildLocationRequest();
        buildLocationCallBack();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                    if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission,CAMERA_REQUEST);

                    }
                    else{
                        openCamera();
                    }
                }
                else {
                    openCamera();
                }
            }
        });




        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String joiningDate = sdf.format(calendar.getTime());

                String cname = MainActivity.categoryName.get(MainActivity.catPosition);
                String ntitle = editTextTitle.getText().toString().trim();
                String ndesc = editTextDesc.getText().toString().trim();

                if(ntitle.isEmpty() && ndesc.isEmpty()){
                    Toast.makeText(DescriptionActivity.this, "Fill the required feilds", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(dataBaseHelper.addNote(cname,ntitle,ndesc,joiningDate,noteLocation.getLatitude(),noteLocation.getLongitude())){
                    Toast.makeText(DescriptionActivity.this, "saved", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DescriptionActivity.this, "not saved", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(DescriptionActivity.this,NotesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);



            }
        });



    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New picture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From camera");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);


        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraintent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(cameraintent,MY_CAMERA_PERMISSION_CODE);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(REQUEST_CODE == requestCode){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                openCamera();
                getLastLocation();
                fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
            }
            else{
                Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show();
            }

            if(CAMERA_REQUEST == requestCode){

                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera();

                    fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
                }
                else{
                    Toast.makeText(this, " camersa denied", Toast.LENGTH_SHORT).show();
                }

            }

//            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                openCamera();
//            }
//            else{
//                Toast.makeText(this, "denied camera", Toast.LENGTH_SHORT).show();
//            }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK) {
            imageView.setImageURI(imageUri);
        }
    }

    private void buildLocationRequest(){
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10);

    }

    private void buildLocationCallBack() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for (Location location: locationResult.getLocations()) {

                   noteLocation = location;


                }
            }
        };
    }

    private boolean checkPermission() {
        int permissionState = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    private void getLastLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(this, new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    noteLocation = task.getResult();
                    System.out.println(noteLocation.getLongitude());
                    System.out.println(noteLocation.getLatitude());

                }
            }
        });
    }




}
