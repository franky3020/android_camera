package com.example.everyday_selfimg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    AppCompatActivity appCompatActivity = this;
    String cameraFilePath = "";
    String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    static final int  ALL_PERMISSION_GRANTED = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        openButton();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == 0) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageBitmap(BitmapFactory.decodeFile(cameraFilePath));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {

            case ALL_PERMISSION_GRANTED: {
                for(int g:grantResults){
                    if( g != PackageManager.PERMISSION_GRANTED ){
                        return;
                    }
                }
                takeAPicture();
                return;

            }

        }
    }


    private void openButton() {
        CameraButton();
    }

    private void CameraButton() {
        Button OpenCameraButton = findViewById(R.id.button3);
        OpenCameraButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                if( getPermission() ) {

                    takeAPicture();
                }

            }
        });
    }

    private void takeAPicture(){
        PhoneCommand phoneCommand = new OpenCamera(appCompatActivity);
        phoneCommand.execute();

        OpenCamera openCamera = (OpenCamera)phoneCommand;
        cameraFilePath =openCamera.getFilePath();
    }



    private boolean isGetAllPermission(){

        for(String permission: permissions){
            boolean isNotGet = !(ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED);

            if(isNotGet){
                return false;
            }
        }
        return true;
    }

    private boolean getPermission(){
        if( ! isGetAllPermission() ) {
            ActivityCompat.requestPermissions(this, permissions, ALL_PERMISSION_GRANTED);
            return false;
        }

        else{
            return true;
        }
    }



}


