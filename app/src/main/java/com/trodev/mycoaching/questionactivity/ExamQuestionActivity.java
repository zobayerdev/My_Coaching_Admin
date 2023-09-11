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
    private RecyclerView questionRv, qustionsevenRv, qustioneightRv ;
    private DatabaseReference reference, referenceSeven, referenceEight, referenceNine, referenceTen, referenceHsc;
    private List<QuestionData> list;
    private QuestionAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_question);

        getSupportActionBar().setTitle("Exam Question");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*find this widget with id*/
        fab = findViewById(R.id.fab);


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
        qustioneightRv = findViewById(R.id.qustioneightRv);


        /*database path*/
        reference = FirebaseDatabase.getInstance().getReference().child("questions").child("question_class_six");
        referenceSeven = FirebaseDatabase.getInstance().getReference().child("questions").child("question_class_seven");
        referenceEight = FirebaseDatabase.getInstance().getReference().child("questions").child("question_class_eight");
        referenceNine = FirebaseDatabase.getInstance().getReference().child("questions").child("question_class_nine");
        referenceTen = FirebaseDatabase.getInstance().getReference().child("questions").child("question_class_ten");
        referenceHsc = FirebaseDatabase.getInstance().getReference().child("questions").child("question_class_hsc");

        /*offline mode create*/
        reference.keepSynced(true);
        referenceSeven.keepSynced(true);
        referenceEight.keepSynced(true);

        getData();
        getDataSeven();
        getDataEight();
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
                questionRv.setLayoutManager(new LinearLayoutManager(ExamQuestionActivity.this));
                questionRv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getDataSeven() {

        referenceSeven.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    QuestionData data = snapshot.getValue(QuestionData.class);
                    list.add(data);
                }


                adapter = new QuestionAdapter(ExamQuestionActivity.this, list);
                // progressBar.setVisibility(View.GONE);
                qustionsevenRv.setLayoutManager(new LinearLayoutManager(ExamQuestionActivity.this));
                qustionsevenRv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getDataEight() {

        referenceEight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    QuestionData data = snapshot.getValue(QuestionData.class);
                    list.add(data);
                }


                adapter = new QuestionAdapter(ExamQuestionActivity.this, list);
                qustioneightRv.setLayoutManager(new LinearLayoutManager(ExamQuestionActivity.this));
                qustioneightRv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

}