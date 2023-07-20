package com.trodev.mycoaching.questionactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trodev.mycoaching.R;

import java.util.ArrayList;
import java.util.List;

public class ExamQuestionActivity extends AppCompatActivity {

    /*import widget*/
    FloatingActionButton fab;
    private RecyclerView questionRv, qustionsevenRv ;
    private DatabaseReference reference, referenceSeven;
    private List<QuestionData> list;
    private QuestionAdapter adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_question);

        getSupportActionBar().setTitle("Exam Question");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*find this widget with id*/
        fab = findViewById(R.id.fab);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        /*set on click listener*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamQuestionActivity.this, UploadQuestionActivity.class);
                startActivity(intent);
            }
        });

        /*pdf data*/
        questionRv = findViewById(R.id.questionRv);
        qustionsevenRv = findViewById(R.id.qustionsevenRv);
        reference = FirebaseDatabase.getInstance().getReference().child("question_class_six");
        referenceSeven = FirebaseDatabase.getInstance().getReference().child("question_class_seven");

        /*offline mode create*/
        reference.keepSynced(true);
        referenceSeven.keepSynced(true);

        getData();
        getDataSeven();
    }

    private void getData() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    QuestionData data = snapshot.getValue(QuestionData.class);
                    list.add(data);
                }

                adapter = new QuestionAdapter(ExamQuestionActivity.this, list);
                progressBar.setVisibility(View.GONE);
                questionRv.setLayoutManager(new LinearLayoutManager(ExamQuestionActivity.this));
                questionRv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void getDataSeven() {

        referenceSeven.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                progressBar.setVisibility(View.VISIBLE);
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    QuestionData data = snapshot.getValue(QuestionData.class);
                    list.add(data);
                }

                progressBar.setVisibility(View.INVISIBLE);
                adapter = new QuestionAdapter(ExamQuestionActivity.this, list);
                // progressBar.setVisibility(View.GONE);
                qustionsevenRv.setLayoutManager(new LinearLayoutManager(ExamQuestionActivity.this));
                qustionsevenRv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

}