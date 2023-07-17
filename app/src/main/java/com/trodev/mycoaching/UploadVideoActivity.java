package com.trodev.mycoaching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UploadVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_video);

        getSupportActionBar().setTitle("Lecture Tutorial");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}