package com.trodev.mycoaching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.squareup.picasso.Picasso;
import com.trodev.mycoaching.syllabusactivity.SyllabusViewActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class QuestionFullImageActivity extends AppCompatActivity {

    private String url;
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_full_image);

        getSupportActionBar().setTitle("Show Question");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        url = getIntent().getStringExtra("pdfUrl");
        pdfView = findViewById(R.id.pdfView);

        new PdfDownload().execute(url);
    }

    private class PdfDownload extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                if(urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
        }
    }
}