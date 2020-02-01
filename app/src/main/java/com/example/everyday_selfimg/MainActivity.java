package com.example.everyday_selfimg;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    AppCompatActivity appCompatActivity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openButton();

    }

    private void openButton() {
        CameraButton();
    }

    private void CameraButton() {
        Button OpenCameraButton = findViewById(R.id.button3);
        OpenCameraButton.setOnClickListener(new Button.OnClickListener(){

            int countTime = 0;

            @Override
            public void onClick(View v) {
                countTime++;
                if(countTime >= 2){
                    PhoneCommand phoneCommand = new OpenCamera(appCompatActivity);
                    phoneCommand.execute();

                    countTime = 0;
                }





            }
        });
    }



}


