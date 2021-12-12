package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class LastSolutions extends AppCompatActivity {
    Button btnAdvancedSearch;
    Button btnSuperSearch;

    AutoCompleteTextView autoRequestText;

    TextView textErrorRequest1;
    TextView textErrorRequest2;
    TextView textErrorRequest3;
    TextView textErrorRequest4;
    TextView descriptionDiscipline;
    TextView descriptionAuthor;
    TextView descriptionTheme;
    TextView descriptionSection;
    TextView descriptionChapter;
    TextView descriptionParagraph;
    TextView descriptionNumberOfPublication;
    TextView descriptionNumberOfVariant;
    TextView descriptionNumberOfTask;


    AutoCompleteTextView autoCompleteDiscipline;
    AutoCompleteTextView autoCompleteAuthor;
    AutoCompleteTextView autoCompleteTheme;

    EditText editTextTextSection;
    EditText editTextChapter;
    EditText editTextParagraph;
    EditText editTextNumberOfPublication;
    EditText editTextNumberOfVariant;
    EditText editTextNumberOfTask;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_solutions);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        databaseHelper.create_db();
        db = databaseHelper.open();

        btnAdvancedSearch = (Button) findViewById(R.id.btnAdvancedSearch);
        btnSuperSearch = (Button) findViewById(R.id.btnSuperSearch);

        textErrorRequest1 = (TextView) findViewById(R.id.textErrorRequest1);
        textErrorRequest2 = (TextView) findViewById(R.id.textErrorRequest2);
        textErrorRequest3 = (TextView) findViewById(R.id.textErrorRequest3);
        textErrorRequest4 = (TextView) findViewById(R.id.textErrorRequest4);
        textErrorRequest4 = (TextView) findViewById(R.id.textErrorRequest4);
        descriptionDiscipline = (TextView) findViewById(R.id.descriptionDiscipline);
        descriptionAuthor = (TextView) findViewById(R.id.descriptionAuthor);
        descriptionTheme = (TextView) findViewById(R.id.descriptionTheme);
        descriptionSection = (TextView) findViewById(R.id.descriptionSection);
        descriptionChapter = (TextView) findViewById(R.id.descriptionChapter);
        descriptionParagraph = (TextView) findViewById(R.id.descriptionParagraph);
        descriptionNumberOfPublication = (TextView) findViewById(R.id.descriptionNumberOfPublication);
        descriptionNumberOfVariant = (TextView) findViewById(R.id.descriptionNumberOfVariant);
        descriptionNumberOfTask = (TextView) findViewById(R.id.descriptionNumberOfTask);


        autoCompleteDiscipline = (AutoCompleteTextView) findViewById(R.id.autoCompleteDiscipline);
        autoCompleteAuthor = (AutoCompleteTextView) findViewById(R.id.autoCompleteAuthor);
        autoCompleteTheme = (AutoCompleteTextView) findViewById(R.id.autoCompleteTheme);

        editTextTextSection = (EditText) findViewById(R.id.editTextTextSection);
        editTextChapter = (EditText) findViewById(R.id.editTextChapter);
        editTextParagraph = (EditText) findViewById(R.id.editTextParagraph);
        editTextNumberOfPublication = (EditText) findViewById(R.id.editTextNumberOfPublication);
        editTextNumberOfVariant = (EditText) findViewById(R.id.editTextNumberOfVariant);
        editTextNumberOfTask = (EditText) findViewById(R.id.editTextNumberOfTask);

        textErrorRequest1.setVisibility(View.GONE);
        textErrorRequest2.setVisibility(View.GONE);
        textErrorRequest3.setVisibility(View.GONE);
        textErrorRequest4.setVisibility(View.GONE);

        autoRequestText = (AutoCompleteTextView) findViewById(R.id.autoRequestText);

        //Код с автозаполнением дисциплины----------------------------------------------------------
        AutoCompleteTextView autoCompleteDiscipline = findViewById(R.id.autoCompleteDiscipline);

        Cursor cursor = db.query(DatabaseHelper.TABLE_DISCIPLINES, null, null, null, null, null, null);
        ArrayList<String> discipline = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            int textDiscipline = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_DISCIPLINE);

            do {
                discipline.add(cursor.getString(textDiscipline));
            } while (cursor.moveToNext());
        }

        ArrayAdapter<String> userAdapter2 = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, discipline);

        autoCompleteDiscipline.setAdapter(userAdapter2);
        cursor.close();
        //-----------------------------------------------------------------------------------------


        //Код с автозаполнением авторов-------------------------------------------------------------
        AutoCompleteTextView autoCompleteAuthor = findViewById(R.id.autoCompleteAuthor);

        cursor = db.query(DatabaseHelper.TABLE_AUTHORS, null, null, null, null, null, null);
        ArrayList<String> author = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            int nameAuthor = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_AUTHOR);

            do {
                author.add(cursor.getString(nameAuthor));
            } while (cursor.moveToNext());
        }

        userAdapter2 = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, author);

        autoCompleteAuthor.setAdapter(userAdapter2);
        cursor.close();
        //------------------------------------------------------------------------------------------

        //Код с автозаполнением тем-----------------------------------------------------------------
        AutoCompleteTextView autoCompleteTheme = findViewById(R.id.autoCompleteTheme);

        cursor = db.query(DatabaseHelper.TABLE_THEMES, null, null, null, null, null, null);
        ArrayList<String> theme = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            int nameAuthor = cursor.getColumnIndex(DatabaseHelper.COLUMN_THEME);

            do {
                theme.add(cursor.getString(nameAuthor));
            } while (cursor.moveToNext());
        }

        userAdapter2 = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, theme);

        autoCompleteTheme.setAdapter(userAdapter2);

        cursor.close();

        descriptionDiscipline.setVisibility(View.GONE);
        descriptionAuthor.setVisibility(View.GONE);
        descriptionTheme.setVisibility(View.GONE);
        descriptionSection.setVisibility(View.GONE);
        descriptionChapter.setVisibility(View.GONE);
        descriptionParagraph.setVisibility(View.GONE);
        descriptionNumberOfPublication.setVisibility(View.GONE);
        descriptionNumberOfVariant.setVisibility(View.GONE);
        descriptionNumberOfTask.setVisibility(View.GONE);
        autoCompleteDiscipline.setVisibility(View.GONE);
        autoCompleteAuthor.setVisibility(View.GONE);
        autoCompleteTheme.setVisibility(View.GONE);
        editTextTextSection.setVisibility(View.GONE);
        editTextChapter.setVisibility(View.GONE);
        editTextParagraph.setVisibility(View.GONE);
        editTextNumberOfPublication.setVisibility(View.GONE);
        editTextNumberOfVariant.setVisibility(View.GONE);
        editTextNumberOfTask.setVisibility(View.GONE);
        btnSuperSearch.setVisibility(View.GONE);

        btnAdvancedSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (descriptionDiscipline.getVisibility() == View.VISIBLE) {
                    btnAdvancedSearch.setText("Расширенный поиск");
                    descriptionDiscipline.setVisibility(View.GONE);
                    descriptionAuthor.setVisibility(View.GONE);
                    descriptionTheme.setVisibility(View.GONE);
                    descriptionSection.setVisibility(View.GONE);
                    descriptionChapter.setVisibility(View.GONE);
                    descriptionParagraph.setVisibility(View.GONE);
                    descriptionNumberOfPublication.setVisibility(View.GONE);
                    descriptionNumberOfVariant.setVisibility(View.GONE);
                    descriptionNumberOfTask.setVisibility(View.GONE);
                    autoCompleteDiscipline.setVisibility(View.GONE);
                    autoCompleteAuthor.setVisibility(View.GONE);
                    autoCompleteTheme.setVisibility(View.GONE);
                    editTextTextSection.setVisibility(View.GONE);
                    editTextChapter.setVisibility(View.GONE);
                    editTextParagraph.setVisibility(View.GONE);
                    editTextNumberOfPublication.setVisibility(View.GONE);
                    editTextNumberOfVariant.setVisibility(View.GONE);
                    editTextNumberOfTask.setVisibility(View.GONE);
                    btnSuperSearch.setVisibility(View.GONE);
                } else {
                    btnAdvancedSearch.setText("Отмена расширенного поиска");
                    descriptionDiscipline.setVisibility(View.VISIBLE);
                    descriptionAuthor.setVisibility(View.VISIBLE);
                    descriptionTheme.setVisibility(View.VISIBLE);
                    descriptionSection.setVisibility(View.VISIBLE);
                    descriptionChapter.setVisibility(View.VISIBLE);
                    descriptionParagraph.setVisibility(View.VISIBLE);
                    descriptionNumberOfPublication.setVisibility(View.VISIBLE);
                    descriptionNumberOfVariant.setVisibility(View.VISIBLE);
                    descriptionNumberOfTask.setVisibility(View.VISIBLE);
                    autoCompleteDiscipline.setVisibility(View.VISIBLE);
                    autoCompleteAuthor.setVisibility(View.VISIBLE);
                    autoCompleteTheme.setVisibility(View.VISIBLE);
                    editTextTextSection.setVisibility(View.VISIBLE);
                    editTextChapter.setVisibility(View.VISIBLE);
                    editTextParagraph.setVisibility(View.VISIBLE);
                    editTextNumberOfPublication.setVisibility(View.VISIBLE);
                    editTextNumberOfVariant.setVisibility(View.VISIBLE);
                    editTextNumberOfTask.setVisibility(View.VISIBLE);
                    btnSuperSearch.setVisibility(View.VISIBLE);
                }
            }
        });



        btnSuperSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (autoCompleteDiscipline.getText().toString().equals("")
                 && autoCompleteAuthor.getText().toString().equals("")
                 && autoCompleteTheme.getText().toString().equals("")
                 && editTextTextSection.getText().toString().equals("")
                 && editTextChapter.getText().toString().equals("")
                 && editTextParagraph.getText().toString().equals("")
                 && editTextNumberOfPublication.getText().toString().equals("")
                 && editTextNumberOfVariant.getText().toString().equals("")
                 && editTextNumberOfTask.getText().toString().equals("")) {
                    textErrorRequest3.setVisibility(View.VISIBLE);
                    textErrorRequest4.setVisibility(View.VISIBLE);
                } else {
                    textErrorRequest3.setVisibility(View.GONE);
                    textErrorRequest4.setVisibility(View.GONE);

                    autoRequestText = (AutoCompleteTextView) findViewById(R.id.autoRequestText);

                    String requestText = autoRequestText.getText().toString();

                    if (!requestText.equals("")) {

                        textErrorRequest1.setVisibility(View.GONE);
                        textErrorRequest2.setVisibility(View.GONE);

                        Bundle arguments = getIntent().getExtras();
                        int id_user = arguments.getInt("id_user");


                        Intent intent = new Intent(getApplicationContext(), SearchingResults.class);
                        intent.putExtra("id_user", id_user);
                        intent.putExtra("requestText", requestText);
                        intent.putExtra("autoCompleteDiscipline", autoCompleteDiscipline.getText().toString());
                        intent.putExtra("autoCompleteAuthor", autoCompleteAuthor.getText().toString());
                        intent.putExtra("autoCompleteTheme", autoCompleteTheme.getText().toString());
                        intent.putExtra("editTextTextSection", editTextTextSection.getText().toString());
                        intent.putExtra("editTextChapter", editTextChapter.getText().toString());
                        intent.putExtra("editTextParagraph", editTextParagraph.getText().toString());
                        intent.putExtra("editTextNumberOfPublication", editTextNumberOfPublication.getText().toString());
                        intent.putExtra("editTextNumberOfVariant", editTextNumberOfVariant.getText().toString());
                        intent.putExtra("editTextNumberOfTask", editTextNumberOfTask.getText().toString());
                        intent.putExtra("cases", 1);
                        startActivity(intent);
                    } else {
                        textErrorRequest1.setVisibility(View.VISIBLE);
                        textErrorRequest2.setVisibility(View.VISIBLE);
                    }

                }
            }
        });


        cursor = db.query(DatabaseHelper.TABLE_DISCIPLINES, null, null, null, null, null, null);
        ArrayList<String> offers = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            int textDiscipline = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_DISCIPLINE);

            do {
                offers.add(cursor.getString(textDiscipline));
            } while (cursor.moveToNext());
        }

        cursor.close();

        cursor = db.query(DatabaseHelper.TABLE_AUTHORS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int nameAuthor = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_AUTHOR);

            do {
                offers.add(cursor.getString(nameAuthor));
            } while (cursor.moveToNext());
        }

        cursor.close();


        cursor = db.query(DatabaseHelper.TABLE_THEMES, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int nameAuthor = cursor.getColumnIndex(DatabaseHelper.COLUMN_THEME);

            do {
                offers.add(cursor.getString(nameAuthor));
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<String> userAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, offers);
        autoRequestText.setAdapter(userAdapter);
    }

    public void moveToSearchingIn(View view) {
        autoRequestText = (AutoCompleteTextView) findViewById(R.id.autoRequestText);

        String requestText = autoRequestText.getText().toString();

        if (!requestText.equals("")) {

            textErrorRequest1.setVisibility(View.GONE);
            textErrorRequest2.setVisibility(View.GONE);

            Bundle arguments = getIntent().getExtras();
            int id_user = arguments.getInt("id_user");

            Intent intent = new Intent(this, SearchingResults.class);
            intent.putExtra("id_user", id_user);
            intent.putExtra("requestText", requestText);
            intent.putExtra("cases", 0);
            startActivity(intent);
        } else {
            textErrorRequest1.setVisibility(View.VISIBLE);
            textErrorRequest2.setVisibility(View.VISIBLE);
        }
    }

    public void moveToUsersIn(View view) {
        Bundle arguments = getIntent().getExtras();
        int id_user = arguments.getInt("id_user");

        Intent intent = new Intent(this, UsersProfile.class);
        intent.putExtra("id_user", id_user);
        intent.putExtra("id_user_owner", id_user);
        startActivity(intent);
    }

    public void moveToDownload(View view) {
        Bundle arguments = getIntent().getExtras();
        int id_user = arguments.getInt("id_user");

        Intent intent = new Intent(this, Download.class);
        intent.putExtra("id_user", id_user);
        startActivity(intent);
    }

}