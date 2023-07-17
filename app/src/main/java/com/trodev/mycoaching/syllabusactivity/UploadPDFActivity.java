package com.trodev.mycoaching.syllabusactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.trodev.mycoaching.R;

import java.io.File;
import java.util.HashMap;

public class UploadPDFActivity extends AppCompatActivity {

    private CardView addPdf;
    private TextView pdfTextView;
    private EditText pdfTitle;
    private Button uploadPdfBtn , classsevenBtn, classeightBtn, classnineBtn, classtenBtn, classhscBtn ;

    private final int REQ = 1;
    private Uri pdfData;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    String downloadUrl=""; // must amne dite hobe nahole not responding dekhabe
    private ProgressDialog progressDialog;

    private String pdfName, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdfactivity);

        getSupportActionBar().setTitle("Upload PDF");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // firebase ini
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();


        //progressbar dialog
        progressDialog = new ProgressDialog(this);

        // id define
        addPdf = findViewById(R.id.addPdf);
        pdfTitle = findViewById(R.id.pdfTitle);
        uploadPdfBtn = findViewById(R.id.uploadPdfBtn);
        pdfTextView = findViewById(R.id.pdfTextView);

        /*all button are is here*/
        classsevenBtn = findViewById(R.id.classsevenBtn);
        classeightBtn = findViewById(R.id.classeightBtn);
        classnineBtn = findViewById(R.id.classnineBtn);
        classtenBtn = findViewById(R.id.classtenBtn);
        classhscBtn = findViewById(R.id.classhscBtn);


        addPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        uploadPdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = pdfTitle.getText().toString();
                if(title.isEmpty()){
                    pdfTitle.setError("Empty...!");
                    pdfTitle.requestFocus();

                }
                else if(pdfData == null)
                {
                    Toast.makeText(UploadPDFActivity.this, "Please select pdf...!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    uploadPdf();
                }
            }
        });


        classsevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = pdfTitle.getText().toString();
                if(title.isEmpty()){
                    pdfTitle.setError("Empty...!");
                    pdfTitle.requestFocus();

                }
                else if(pdfData == null)
                {
                    Toast.makeText(UploadPDFActivity.this, "Please select pdf...!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    uploadPdfSeven();
                }
            }
        });

    }

    private void uploadPdfSeven() {

        progressDialog.setTitle("Please wait...");
        progressDialog.setTitle("Uploading pdf");
        progressDialog.show();
        StorageReference reference = storageReference.child("syllabus_class_seven/"+pdfName+"-"+System.currentTimeMillis()+".pdf");
        reference.putFile(pdfData)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri uri = uriTask.getResult();

                        /*data send on this path*/
                        uploadDataSeven(String.valueOf(uri));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(UploadPDFActivity.this, "Something went wrong...!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void uploadDataSeven(String valueOf) {

        String uniqueKey = databaseReference.child("syllabus_class_seven").push().getKey();

        HashMap data = new HashMap();
        data.put("pdfTitle",title);
        data.put("pdfUrl",valueOf);

        databaseReference.child("syllabus_class_seven").child(uniqueKey).setValue(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(UploadPDFActivity.this, "PDF uploaded successful", Toast.LENGTH_SHORT).show();
                        pdfTitle.setText("");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(UploadPDFActivity.this, "Failed to upload PDF", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void uploadPdf() {
        progressDialog.setTitle("Please wait...");
        progressDialog.setTitle("Uploading pdf");
        progressDialog.show();
        StorageReference reference = storageReference.child("syllabus_class_six/"+pdfName+"-"+System.currentTimeMillis()+".pdf");
        reference.putFile(pdfData)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri uri = uriTask.getResult();
                        uploadData(String.valueOf(uri));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(UploadPDFActivity.this, "Something went wrong...!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void uploadData(String valueOf) {

        String uniqueKey = databaseReference.child("syllabus_class_six").push().getKey();

        HashMap data = new HashMap();
        data.put("pdfTitle",title);
        data.put("pdfUrl",valueOf);

        databaseReference.child("syllabus_class_six").child(uniqueKey).setValue(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(UploadPDFActivity.this, "PDF uploaded successful", Toast.LENGTH_SHORT).show();
                        pdfTitle.setText("");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(UploadPDFActivity.this, "Failed to upload PDF", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("pdf/docs/ppt");// eikhane jodi amr phn e support na kore tahole (*) mark diye nite parbo;
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Pdf File"),REQ);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == REQ && resultCode ==RESULT_OK){
            pdfData = data.getData();

            if(pdfData.toString().startsWith("content://"))
            {
                Cursor cursor = null;
                try {
                    cursor = UploadPDFActivity.this.getContentResolver().query(pdfData,null,null,null,null);
                    if(cursor != null && cursor.moveToFirst())
                    {

                        pdfName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else if(pdfData.toString().startsWith("file://"))
            {
                pdfName = new File(pdfData.toString()).getName();
            }

            Toast.makeText(this, "PDF added successfull...!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "PDF added unsuccessfull...!", Toast.LENGTH_LONG).show();
        }

        pdfTextView.setText("PDF Name: "+pdfName);

    }
}