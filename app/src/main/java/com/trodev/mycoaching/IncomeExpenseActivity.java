package com.trodev.mycoaching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class IncomeExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_expense);

        getSupportActionBar().setTitle("Income and Expense");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}