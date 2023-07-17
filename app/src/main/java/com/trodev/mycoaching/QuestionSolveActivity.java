package com.trodev.mycoaching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class QuestionSolveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_solve);

        getSupportActionBar().setTitle("Previous Question Solve");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}