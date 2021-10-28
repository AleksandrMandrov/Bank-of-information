package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class LastSolutions extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_solutions);

    }

    public void moveToDownload(View view) {
        Intent intent = new Intent(this, Download.class);
        startActivity(intent);
    }

}