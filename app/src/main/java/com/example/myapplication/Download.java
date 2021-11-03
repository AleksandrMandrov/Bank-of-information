package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Download extends AppCompatActivity {
    TextView textFile;

    private static final int PICKFILE_RESULT_CODE = 1;

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        /*setContentView(R.layout.activity_main);*/

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView2);

        String[] TheScience = getResources().getStringArray(R.array.TheScience);
        List<String> TheScienceList = Arrays.asList(TheScience);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, TheScienceList);
        autoCompleteTextView.setAdapter(adapter);

        AutoCompleteTextView autoCompleteTextView3 = findViewById(R.id.autoCompleteTextView3);

        String[] Author = getResources().getStringArray(R.array.Author);
        List<String> AuthorList = Arrays.asList(Author);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, Author);
        autoCompleteTextView3.setAdapter(adapter3);

        AutoCompleteTextView autoCompleteTextView4 = findViewById(R.id.autoCompleteTextView4);

        String[] Theme = getResources().getStringArray(R.array.Theme);
        List<String> ThemeList = Arrays.asList(Theme);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, Theme);
        autoCompleteTextView4.setAdapter(adapter4);



        /*прикрепление пдф*/

        Button buttonPick = (Button)findViewById(R.id.button2);
        textFile = (TextView)findViewById(R.id.textView12);

        buttonPick.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("file/.pdf");
                startActivityForResult(intent,PICKFILE_RESULT_CODE);

            }});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch(requestCode){
            case PICKFILE_RESULT_CODE:
                if(resultCode==RESULT_OK){
                    String FilePath = data.getData().getPath();
                    textFile.setText(FilePath);
                }
                break;

        }
    }
}