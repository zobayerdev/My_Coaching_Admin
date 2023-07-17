package com.trodev.mycoaching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StudentPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_payment);

        getSupportActionBar().setTitle("Student Monthly Payment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}