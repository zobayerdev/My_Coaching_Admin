package com.trodev.mycoaching.syllabusactivity;

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

public class ExamSyllabusActivity extends AppCompatActivity {

    /*import widget*/
    FloatingActionButton fab;
    private RecyclerView ebookRecycler;
    private DatabaseReference reference, referenceSeven, referenceEight;
    private List<SyllabusData> list;
    private SyllabusAdapter adapter;
    ProgressBar progreeBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_syllabus);

        getSupportActionBar().setTitle("Exam Syllabus");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*find this widget with id*/
        fab = findViewById(R.id.fab);
        progreeBar = findViewById(R.id.progreeBar);

        /*set on click listener*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamSyllabusActivity.this, UploadPDFActivity.class);
                startActivity(intent);
            }
        });

        /*pdf data*/
        ebookRecycler = findViewById(R.id.ebookRecycler);
        reference = FirebaseDatabase.getInstance().getReference().child("syllabus_class_six");
        referenceSeven = FirebaseDatabase.getInstance().getReference().child("syllabus_class_seven");
        referenceEight = FirebaseDatabase.getInstance().getReference().child("syllabus_class_eight");

        getData();
        getDataSeven();
        //  getDataEight();
    }

    private void getData() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    SyllabusData data = snapshot.getValue(SyllabusData.class);
                    list.add(data);
                }

                adapter = new SyllabusAdapter(ExamSyllabusActivity.this, list);
                progreeBar.setVisibility(View.GONE);
                ebookRecycler.setLayoutManager(new LinearLayoutManager(ExamSyllabusActivity.this));
                ebookRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progreeBar.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getDataSeven() {

        referenceSeven.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                progreeBar.setVisibility(View.VISIBLE);
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    SyllabusData data = snapshot.getValue(SyllabusData.class);
                    list.add(data);
                }

                progreeBar.setVisibility(View.INVISIBLE);
                adapter = new SyllabusAdapter(ExamSyllabusActivity.this, list);
                // progressBar.setVisibility(View.GONE);
                ebookRecycler.setLayoutManager(new LinearLayoutManager(ExamSyllabusActivity.this));
                ebookRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progreeBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }


    /*#########################################*/
    /*
     * Must create a nested recyclerview
     * Must add all ebook recyclerview add on adapter
     * */
/*    private void getDataEight() {
        referenceEight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                progreeBar.setVisibility(View.VISIBLE);
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    SyllabusData data = snapshot.getValue(SyllabusData.class);
                    list.add(data);
                }

                progreeBar.setVisibility(View.INVISIBLE);
                adapter = new SyllabusAdapter(ExamSyllabusActivity.this, list);
                // progressBar.setVisibility(View.GONE);
                ebookRecycler.setLayoutManager(new LinearLayoutManager(ExamSyllabusActivity.this));
                ebookRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progreeBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }*/

}