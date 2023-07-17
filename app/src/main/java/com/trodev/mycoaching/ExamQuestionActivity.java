package com.trodev.mycoaching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ExamQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_question);

        getSupportActionBar().setTitle("Exam Question");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}