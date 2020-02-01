package com.example.everyday_selfimg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class OpenCamera extends PhoneCommand {
    AppCompatActivity app;
    public OpenCamera(AppCompatActivity app){
        this.app = app;
    }

    @Override
    public void execute() {
        startCamera();
    }


    public void startCamera (){
        if(!isLocationPermissionGranted()){
            openAppSettingsIntent();
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        this.app.startActivity(intent);
    }

    private boolean isLocationPermissionGranted() {
        return ContextCompat.checkSelfPermission(this.app, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void openAppSettingsIntent() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", this.app.getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.app.startActivity(intent);
    }
}

