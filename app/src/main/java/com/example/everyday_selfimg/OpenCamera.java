package com.example.everyday_selfimg;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;

public class OpenCamera extends PhoneCommand {
    AppCompatActivity app;
    File cameraSaveFile = null;

    public OpenCamera(AppCompatActivity app){
        this.app = app;
    }

    @Override
    public void execute() {
        startCamera();
    }


    public void startCamera () {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraSaveFile = new File(Environment.getExternalStorageDirectory(),"imageTmp.jpg");

        Uri outputFileUri = Uri.fromFile(cameraSaveFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        this.app.startActivityForResult(intent, 0);

    }
    public String getFilePath() {
        return cameraSaveFile.getPath();
    }

}

