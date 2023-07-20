package com.trodev.mycoaching.solvequestionactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trodev.mycoaching.R;

public class QuestionSolveActivity extends AppCompatActivity {

    /*import widget*/
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_solve);

        getSupportActionBar().setTitle("Previous Question Solve");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*find this widget with id*/
        fab = findViewById(R.id.fab);


        /*set on click listener*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionSolveActivity.this, UploadSolveActivity.class);
                startActivity(intent);
            }
        });


    }
}