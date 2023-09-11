package com.trodev.mycoaching.tube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.trodev.mycoaching.R;

public class TubeActivity extends AppCompatActivity {

    private EditText tube_title, tube_link, upload_date  ;
    private Spinner data_category;
    private Button uploadBtn;
    private final int REQ = 1;
    private Bitmap bitmap = null;
    private String category;
    private ProgressDialog progressDialog;

    private String title, link, date;

    private String downloadUrl = "";

    private StorageReference storageReference;
    private DatabaseReference reference, dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tube);

        /*init views*/
        tube_title = findViewById(R.id.tube_title);
        tube_link = findViewById(R.id.tube_link);
        upload_date = findViewById(R.id.upload_date);

        /*init button*/
        uploadBtn = findViewById(R.id.uploadBtn);
        data_category = findViewById(R.id.data_category);

        /*progressbar */
        progressDialog = new ProgressDialog(this);

        /*database path*/
        // firebase
        reference = FirebaseDatabase.getInstance().getReference().child("lectures");

        /*database category*/
        String[] items = new String[]{"Select class", "class 06", "class 07", "class 08", "class 09", "class 10", "class hsc" };
        data_category.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items));

        data_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                category = data_category.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        uploadBtn.setOnClickListener(new View.OnClickListener() { // eikhane change ache AddStudentBtn
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });
    }


    private void checkValidation() {

        title = tube_title.getText().toString().trim();
        link = tube_link.getText().toString().trim();
        date = upload_date.getText().toString().trim();


        if (title.isEmpty() || link.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "পূরণ করুন", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.setMessage("Uploading Details");
            progressDialog.show();
            insertData();
        }

    }

    private void insertData() {

        dbRef = reference.child(category);
        final String uniquekey = dbRef.push().getKey();


        TubeModel tubeModel = new TubeModel(title, link, date,  uniquekey);
        dbRef.child(uniquekey).setValue(tubeModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                Toast.makeText(TubeActivity.this, "সফল ভাবে আপলোড হয়েছে", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(TubeActivity.this, "আপলোড সফল হয়নি", Toast.LENGTH_SHORT).show();
            }
        });

    }

}