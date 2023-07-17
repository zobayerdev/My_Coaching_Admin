package com.trodev.mycoaching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ExamSyllabusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_syllabus);

        getSupportActionBar().setTitle("Exam Syllabus");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}