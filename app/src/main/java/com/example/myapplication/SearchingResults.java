package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class SearchingResults extends AppCompatActivity {

    ImageView imageNoSolution;

    ArrayList<SearchResult> states = new ArrayList<SearchResult>();

    ListView listSearchResult;

    TextView NumberSolutionsFound;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    Cursor cursor1;
    Cursor cursor2;
    Cursor cursor3;
    Cursor cursor4;
    Cursor cursor5;

    Button btnAdvancedSearch;
    Button btnSuperSearch;
    Button btnCreateWork;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_results);

        imageNoSolution = (ImageView) findViewById(R.id.imageNoSolution);

        btnAdvancedSearch = (Button) findViewById(R.id.btnAdvancedSearch);
        btnSuperSearch = (Button) findViewById(R.id.btnSuperSearch);
        btnCreateWork = (Button) findViewById(R.id.btnCreateWork);

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

        listSearchResult = (ListView) findViewById(R.id.listSearchResult);

        NumberSolutionsFound = (TextView) findViewById(R.id.NumberSolutionsFound);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        databaseHelper.create_db();
        db = databaseHelper.open();

        btnCreateWork.setVisibility(View.GONE);
        imageNoSolution.setVisibility(View.GONE);

        Bundle arguments = getIntent().getExtras();
        int id_user = arguments.getInt("id_user");
        String requestText = arguments.getString("requestText");
        int cases = arguments.getInt("cases");

        ArrayList<String> descriptions_id1 = new ArrayList<String>();
        int n1 = 0;

        cursor1 = db.query(DatabaseHelper.TABLE_DISCIPLINES, null, null, null, null, null, null);

        if (cursor1.moveToFirst()) {
            int _id_discipline = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_DISCIPLINE);
            int name_discipline = cursor1.getColumnIndex(DatabaseHelper.COLUMN_NAME_DISCIPLINE);

            do {
                if (cursor1.getString(name_discipline).toLowerCase().contains(requestText.toLowerCase())) {
                    descriptions_id1.add(Integer.toString(cursor1.getInt(_id_discipline)));
                    n1++;
                }
            } while (cursor1.moveToNext());
        }
        cursor1.close();

        String[] descriptions_id1_array = (String[]) descriptions_id1.toArray(new String[descriptions_id1.size()]);

        ArrayList<String> descriptions_id2 = new ArrayList<String>();
        int n2 = 0;
        cursor1 = db.query(DatabaseHelper.TABLE_AUTHORS, null, null, null, null, null, null);

        if (cursor1.moveToFirst()) {
            int _id_author = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_AUTHOR);
            int name_author = cursor1.getColumnIndex(DatabaseHelper.COLUMN_NAME_AUTHOR);

            do {
                if (cursor1.getString(name_author).toLowerCase().contains(requestText.toLowerCase())) {
                    descriptions_id2.add(Integer.toString(cursor1.getInt(_id_author)));
                    n2++;
                }
            } while (cursor1.moveToNext());
        }
        cursor1.close();

        String[] descriptions_id2_array = (String[]) descriptions_id2.toArray(new String[descriptions_id2.size()]);

        ArrayList<String> descriptions_id3 = new ArrayList<String>();
        int n3 = 0;
        cursor1 = db.query(DatabaseHelper.TABLE_THEMES, null, null, null, null, null, null);

        if (cursor1.moveToFirst()) {
            int _id_theme = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_THEME);
            int theme = cursor1.getColumnIndex(DatabaseHelper.COLUMN_THEME);

            do {
                if (cursor1.getString(theme).toLowerCase().contains(requestText.toLowerCase())) {
                    descriptions_id3.add(Integer.toString(cursor1.getInt(_id_theme)));
                    n3++;
                }
            } while (cursor1.moveToNext());
        }
        cursor1.close();

        String[] descriptions_id3_array = (String[]) descriptions_id2.toArray(new String[descriptions_id3.size()]);

        LinkedHashSet<String> descriptions_id = new LinkedHashSet<String>();

        cursor1 = db.query(DatabaseHelper.TABLE_WORK_DESCRIPTION, null, null, null, null, null, null);

        if (cursor1.moveToFirst()) {
            int id_work_description = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_WORK_DESCRIPTION);
            int id_type_of_work = cursor1.getColumnIndex(DatabaseHelper.COLUMN_TYPE_OF_WORK);
            int task_number = cursor1.getColumnIndex(DatabaseHelper.COLUMN_TASK_NUMBER);
            int option_number = cursor1.getColumnIndex(DatabaseHelper.COLUMN_OPTION_NUMBER);
            int section_number = cursor1.getColumnIndex(DatabaseHelper.COLUMN_SECTION_NUMBER);
            int chapter_number = cursor1.getColumnIndex(DatabaseHelper.COLUMN_CHAPTER_NUMBER);
            int paragraph_number = cursor1.getColumnIndex(DatabaseHelper.COLUMN_PARAGRAPH_NUMBER);
            int publication_number = cursor1.getColumnIndex(DatabaseHelper.COLUMN_PUBLICATION_NUMBER);

            do {
                boolean next = true;
                if (cursor1.getString(id_type_of_work).toLowerCase().contains(requestText.toLowerCase())) {
                    descriptions_id.add(Integer.toString(cursor1.getInt(id_work_description)));
                    next = false;
                }

                if (next && Integer.toString(cursor1.getInt(task_number)).toLowerCase().contains(requestText.toLowerCase())) {
                    descriptions_id.add(Integer.toString(cursor1.getInt(id_work_description)));
                    next = false;
                }

                if (next && Integer.toString(cursor1.getInt(option_number)).toLowerCase().contains(requestText.toLowerCase())) {
                    descriptions_id.add(Integer.toString(cursor1.getInt(id_work_description)));
                    next = false;
                }

                if (next && Integer.toString(cursor1.getInt(section_number)).toLowerCase().contains(requestText.toLowerCase())) {
                    descriptions_id.add(Integer.toString(cursor1.getInt(id_work_description)));
                    next = false;
                }

                if (next && Integer.toString(cursor1.getInt(chapter_number)).toLowerCase().contains(requestText.toLowerCase())) {
                    descriptions_id.add(Integer.toString(cursor1.getInt(id_work_description)));
                    next = false;
                }

                if (next && Integer.toString(cursor1.getInt(paragraph_number)).toLowerCase().contains(requestText.toLowerCase())) {
                    descriptions_id.add(Integer.toString(cursor1.getInt(id_work_description)));
                    next = false;
                }

                if (next && Integer.toString(cursor1.getInt(publication_number)).toLowerCase().contains(requestText.toLowerCase())) {
                    descriptions_id.add(Integer.toString(cursor1.getInt(id_work_description)));
                }

            } while (cursor1.moveToNext());
        }
        cursor1.close();

        for (int i = 0; i < n1; i++) {
            cursor1 = db.query(DatabaseHelper.TABLE_WORK_DESCRIPTION, null, null, null, null, null, null);

            if (cursor1.moveToFirst()) {
                int id_work_description = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_WORK_DESCRIPTION);
                int _id_discipline = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_DISCIPLINE);

                do {
                    if (Integer.toString(cursor1.getInt(_id_discipline)).equals(descriptions_id1_array[i])) {
                        descriptions_id.add(Integer.toString(cursor1.getInt(id_work_description)));
                    }

                } while (cursor1.moveToNext());
            }
            cursor1.close();
        }

        for (int i = 0; i < n2; i++) {
            cursor1 = db.query(DatabaseHelper.TABLE_WORK_DESCRIPTION, null, null, null, null, null, null);

            if (cursor1.moveToFirst()) {
                int id_work_description = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_WORK_DESCRIPTION);
                int _id_author = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_AUTHOR);

                do {
                    if (Integer.toString(cursor1.getInt(_id_author)).equals(descriptions_id2_array[i])) {
                        descriptions_id.add(Integer.toString(cursor1.getInt(id_work_description)));
                    }

                } while (cursor1.moveToNext());
            }
            cursor1.close();
        }

        for (int i = 0; i < n3; i++) {
            cursor1 = db.query(DatabaseHelper.TABLE_THEME_OF_WORK, null, null, null, null, null, null);

            if (cursor1.moveToFirst()) {
                int id_work_description = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_WORK_DESCRIPTION);
                int _id_theme = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_THEME);

                do {
                    if (Integer.toString(cursor1.getInt(_id_theme)).equals(descriptions_id3_array[i])) {
                        descriptions_id.add(Integer.toString(cursor1.getInt(id_work_description)));
                    }

                } while (cursor1.moveToNext());
            }
            cursor1.close();
        }

        String[] descrip_id = (String[]) descriptions_id.toArray(new String[descriptions_id.size()]);
        int n = descriptions_id.size();

        String[][] foundWorks = new String[n][18];
        for (int i = 0; i < n; i++) {
            foundWorks[i][0] = descrip_id[i];

            cursor1 = db.query(DatabaseHelper.TABLE_WORK_DESCRIPTION, null, null, null, null, null, null);

            if (cursor1.moveToFirst()) {
                int id_work_description = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_WORK_DESCRIPTION);
                int id_type_of_work = cursor1.getColumnIndex(DatabaseHelper.COLUMN_TYPE_OF_WORK);
                int id_discipline = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_DISCIPLINE);
                int id_author = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_AUTHOR);
                int task_number = cursor1.getColumnIndex(DatabaseHelper.COLUMN_TASK_NUMBER);
                int option_number = cursor1.getColumnIndex(DatabaseHelper.COLUMN_OPTION_NUMBER);
                int section_number = cursor1.getColumnIndex(DatabaseHelper.COLUMN_SECTION_NUMBER);
                int chapter_number = cursor1.getColumnIndex(DatabaseHelper.COLUMN_CHAPTER_NUMBER);
                int paragraph_number = cursor1.getColumnIndex(DatabaseHelper.COLUMN_PARAGRAPH_NUMBER);
                int publication_number = cursor1.getColumnIndex(DatabaseHelper.COLUMN_PUBLICATION_NUMBER);

                do {
                    if (Integer.toString(cursor1.getInt(id_work_description)).equals(foundWorks[i][0])) {
                        foundWorks[i][1] = cursor1.getString(id_type_of_work);
                        foundWorks[i][2] = Integer.toString(cursor1.getInt(id_discipline));
                        foundWorks[i][3] = Integer.toString(cursor1.getInt(id_author));
                        foundWorks[i][9] = Integer.toString(cursor1.getInt(task_number));
                        foundWorks[i][10] = Integer.toString(cursor1.getInt(option_number));
                        foundWorks[i][11] = Integer.toString(cursor1.getInt(section_number));
                        foundWorks[i][12] = Integer.toString(cursor1.getInt(chapter_number));
                        foundWorks[i][13] = Integer.toString(cursor1.getInt(paragraph_number));
                        foundWorks[i][14] = Integer.toString(cursor1.getInt(publication_number));
                        break;
                    }
                } while (cursor1.moveToNext());
            }
            cursor1.close();

            cursor2 = db.query(DatabaseHelper.TABLE_DISCIPLINES, null, null, null, null, null, null);

            if (cursor2.moveToFirst()) {
                int _id_discipline = cursor2.getColumnIndex(DatabaseHelper.COLUMN_ID_DISCIPLINE);
                int name_discipline = cursor2.getColumnIndex(DatabaseHelper.COLUMN_NAME_DISCIPLINE);

                do {
                    if (Integer.toString(cursor2.getInt(_id_discipline)).equals(foundWorks[i][2])) {
                        foundWorks[i][2] = cursor2.getString(name_discipline);
                        break;
                    }

                } while (cursor2.moveToNext());
            }
            cursor2.close();

            cursor2 = db.query(DatabaseHelper.TABLE_AUTHORS, null, null, null, null, null, null);

            if (cursor2.moveToFirst()) {
                int _id_author = cursor2.getColumnIndex(DatabaseHelper.COLUMN_ID_AUTHOR);
                int name_author = cursor2.getColumnIndex(DatabaseHelper.COLUMN_NAME_AUTHOR);

                do {
                    if (Integer.toString(cursor2.getInt(_id_author)).equals(foundWorks[i][3])) {
                        foundWorks[i][3] = cursor2.getString(name_author);
                        break;
                    }

                } while (cursor2.moveToNext());
            }
            cursor2.close();

            int iTheme = 0;

            foundWorks[i][4] = "lack";
            foundWorks[i][5] = "lack";
            foundWorks[i][6] = "lack";
            foundWorks[i][7] = "lack";
            foundWorks[i][8] = "lack";

            cursor2 = db.query(DatabaseHelper.TABLE_THEME_OF_WORK, null, null, null, null, null, null);

            if (cursor2.moveToFirst()) {
                int id_work_description = cursor2.getColumnIndex(DatabaseHelper.COLUMN_ID_WORK_DESCRIPTION);
                int _id_theme = cursor2.getColumnIndex(DatabaseHelper.COLUMN_ID_THEME);

                do {
                    if (Integer.toString(cursor2.getInt(id_work_description)).equals(foundWorks[i][0])) {
                        foundWorks[i][4 + iTheme] = Integer.toString(cursor2.getInt(_id_theme));
                        iTheme++;
                    }

                    if (iTheme == 5) {
                        break;
                    }

                } while (cursor2.moveToNext());
            }
            cursor2.close();


            cursor3 = db.query(DatabaseHelper.TABLE_THEMES, null, null, null, null, null, null);

            if (cursor3.moveToFirst()) {
                int _id_theme = cursor3.getColumnIndex(DatabaseHelper.COLUMN_ID_THEME);
                int _id_name_theme = cursor3.getColumnIndex(DatabaseHelper.COLUMN_THEME);

                do {
                    if (Integer.toString(cursor3.getInt(_id_theme)).equals(foundWorks[i][4])) {
                        foundWorks[i][4] = cursor3.getString(_id_name_theme);
                        iTheme++;
                    }

                    if ((iTheme >= 1) && (Integer.toString(cursor3.getInt(_id_theme)).equals(foundWorks[i][5]))) {
                        foundWorks[i][5] = cursor3.getString(_id_name_theme);
                        iTheme++;
                    }

                    if ((iTheme >= 2) && (Integer.toString(cursor3.getInt(_id_theme)).equals(foundWorks[i][6]))) {
                        foundWorks[i][6] = cursor3.getString(_id_name_theme);
                        iTheme++;
                    }

                    if ((iTheme >= 3) && (Integer.toString(cursor3.getInt(_id_theme)).equals(foundWorks[i][7]))) {
                        foundWorks[i][7] = cursor3.getString(_id_name_theme);
                        iTheme++;
                    }

                    if ((iTheme >= 4) && (Integer.toString(cursor3.getInt(_id_theme)).equals(foundWorks[i][8]))) {
                        foundWorks[i][8] = cursor3.getString(_id_name_theme);
                        iTheme++;
                    }

                } while (cursor3.moveToNext());
            }
            cursor3.close();

            cursor4 = db.query(DatabaseHelper.TABLE_USER_WORK, null, null, null, null, null, null);

            if (cursor4.moveToLast()) {
                int id_user_work_in_DB = cursor4.getColumnIndex(DatabaseHelper.COLUMN_ID_USER_WORK);
                int id_work_description_in_DB = cursor4.getColumnIndex(DatabaseHelper.COLUMN_ID_WORK_DESCRIPTION);
                int id_creator = cursor4.getColumnIndex(DatabaseHelper.COLUMN_ID_USER);
                int id_time_of_creation = cursor4.getColumnIndex(DatabaseHelper.COLUMN_TIME_OF_CREATION);

                do {
                    if (Integer.toString(cursor4.getInt(id_work_description_in_DB)).equals(foundWorks[i][0])) {
                        foundWorks[i][15] = Integer.toString(cursor4.getInt(id_user_work_in_DB));
                        foundWorks[i][16] = Integer.toString(cursor4.getInt(id_creator));
                        foundWorks[i][17] = cursor4.getString(id_time_of_creation);

                    }
                } while (cursor4.moveToPrevious());
            }
            cursor4.close();

            cursor5 = db.query(DatabaseHelper.TABLE_USERS, null, null, null, null, null, null);

            if (cursor5.moveToFirst()) {
                int _id_user = cursor5.getColumnIndex(DatabaseHelper.COLUMN_ID_USER);
                int name = cursor5.getColumnIndex(DatabaseHelper.COLUMN_NAME);

                do {
                    if (Integer.toString(cursor5.getInt(_id_user)).equals(foundWorks[i][16])) {
                        foundWorks[i][16] = cursor5.getString(name);
                        break;
                    }


                } while (cursor5.moveToNext());
            }
            cursor5.close();

        }

        String[][] finalFoundWorks = new String[n][18];
        if (cases == 0) {
            finalFoundWorks = foundWorks;
        } else {
            int previousN = n;
            n = 0;

            String autoCompleteDiscipline = arguments.getString("autoCompleteDiscipline");
            String autoCompleteAuthor = arguments.getString("autoCompleteAuthor");
            String autoCompleteTheme = arguments.getString("autoCompleteTheme");
            String editTextTextSection = arguments.getString("editTextTextSection");
            String editTextChapter = arguments.getString("editTextChapter");
            String editTextParagraph = arguments.getString("editTextParagraph");
            String editTextNumberOfPublication = arguments.getString("editTextNumberOfPublication");
            String editTextNumberOfVariant = arguments.getString("editTextNumberOfVariant");
            String editTextNumberOfTask = arguments.getString("editTextNumberOfTask");

            for (int i = 0; i < previousN; i++) {
                boolean passed = true;

                finalFoundWorks[n][0] = foundWorks[i][0];
                finalFoundWorks[n][1] = foundWorks[i][1];
                finalFoundWorks[n][2] = foundWorks[i][2];
                finalFoundWorks[n][3] = foundWorks[i][3];
                finalFoundWorks[n][4] = foundWorks[i][4];
                finalFoundWorks[n][5] = foundWorks[i][5];
                finalFoundWorks[n][6] = foundWorks[i][6];
                finalFoundWorks[n][7] = foundWorks[i][7];
                finalFoundWorks[n][8] = foundWorks[i][8];
                finalFoundWorks[n][9] = foundWorks[i][9];
                finalFoundWorks[n][10] = foundWorks[i][10];
                finalFoundWorks[n][11] = foundWorks[i][11];
                finalFoundWorks[n][12] = foundWorks[i][12];
                finalFoundWorks[n][13] = foundWorks[i][13];
                finalFoundWorks[n][14] = foundWorks[i][14];
                finalFoundWorks[n][15] = foundWorks[i][15];
                finalFoundWorks[n][16] = foundWorks[i][16];
                finalFoundWorks[n][17] = foundWorks[i][17];

                if (!((autoCompleteDiscipline.equals("") || autoCompleteDiscipline.equals(foundWorks[i][2])))) {
                    passed = false;
                }

                if (!((autoCompleteAuthor.equals("") || autoCompleteAuthor.equals(foundWorks[i][3])))) {
                    passed = false;
                }

                if (!autoCompleteTheme.equals("")) {
                    boolean passedTheme = false;
                    if (autoCompleteTheme.equals(foundWorks[i][4])) {
                        passedTheme = true;
                    }
                    if (autoCompleteTheme.equals(foundWorks[i][5])) {
                        passedTheme = true;
                    }
                    if (autoCompleteTheme.equals(foundWorks[i][6])) {
                        passedTheme = true;
                    }
                    if (autoCompleteTheme.equals(foundWorks[i][7])) {
                        passedTheme = true;
                    }
                    if (autoCompleteTheme.equals(foundWorks[i][8])) {
                        passedTheme = true;
                    }

                    if (!passedTheme) {
                        passed = false;
                    }
                }

                if (!((editTextNumberOfTask.equals("") || editTextNumberOfTask.equals(foundWorks[i][9])))) {
                    passed = false;
                }

                if (!((editTextNumberOfVariant.equals("") || editTextNumberOfVariant.equals(foundWorks[i][10])))) {
                    passed = false;
                }

                if (!((editTextTextSection.equals("") || editTextTextSection.equals(foundWorks[i][11])))) {
                    passed = false;
                }

                if (!((editTextChapter.equals("") || editTextChapter.equals(foundWorks[i][12])))) {
                    passed = false;
                }

                if (!((editTextParagraph.equals("") || editTextParagraph.equals(foundWorks[i][13])))) {
                    passed = false;
                }

                if (!((editTextNumberOfPublication.equals("") || editTextNumberOfPublication.equals(foundWorks[i][14])))) {
                    passed = false;
                }

                if (passed) {
                    n++;
                }

            }
        }

        NumberSolutionsFound.setText(Integer.toString(n));

        if (n == 0) {
            btnCreateWork.setVisibility(View.VISIBLE);
            imageNoSolution.setVisibility(View.VISIBLE);
        }

        // создаем адаптер
        SearchResultAdapter stateAdapter = new SearchResultAdapter(this, R.layout.listsearchresult, states);
        // устанавливаем адаптер
        listSearchResult.setAdapter(stateAdapter);
        // слушатель выбора в списке
        String[][] finalFoundWorksLast = finalFoundWorks;
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), InformationAboutTheSolution.class);
                intent.putExtra("id_user", id_user);
                intent.putExtra("id_user_work", Integer.valueOf(finalFoundWorksLast[position][15]));
                startActivity(intent);
            }
        };
        listSearchResult.setOnItemClickListener(itemListener);

        setInitialData(finalFoundWorksLast, n);
    }

    private void setInitialData(String[][] finalFoundWorksLast, int n) {
        for (int i = 0; i < n; i++) {
            String mainStr = "Создатель: " + finalFoundWorksLast[i][16] + "\n";
            mainStr += "Время создания: " + finalFoundWorksLast[i][17] + "\n";
            mainStr += "Тип работы: " + finalFoundWorksLast[i][1] + "\n";
            mainStr += "Дисциплина: " + finalFoundWorksLast[i][2] + "\n";
            mainStr += "Автор: " + finalFoundWorksLast[i][3] + "\n";

            if (!finalFoundWorksLast[i][4].equals("lack")) {
                mainStr += "Тема: " + finalFoundWorksLast[i][4] + "\n";
            }
            if (!finalFoundWorksLast[i][5].equals("lack")) {
                mainStr += "Тема 2: " + finalFoundWorksLast[i][5] + "\n";
            }
            if (!finalFoundWorksLast[i][6].equals("lack")) {
                mainStr += "Тема 3: " + finalFoundWorksLast[i][6] + "\n";
            }
            if (!finalFoundWorksLast[i][7].equals("lack")) {
                mainStr += "Тема 4: " + finalFoundWorksLast[i][7] + "\n";
            }
            if (!finalFoundWorksLast[i][8].equals("lack")) {
                mainStr += "Тема 5: " + finalFoundWorksLast[i][8] + "\n";
            }

            String additionalStr = "";

            if (!finalFoundWorksLast[i][9].equals("-1")) {
                additionalStr += "Номер задания: " + finalFoundWorksLast[i][9] + "\n";
            }
            if (!finalFoundWorksLast[i][10].equals("-1")) {
                additionalStr += "Номер варианта: " + finalFoundWorksLast[i][10] + "\n";
            }
            if (!finalFoundWorksLast[i][11].equals("-1")) {
                additionalStr += "Номер раздела: " + finalFoundWorksLast[i][11] + "\n";
            }
            if (!finalFoundWorksLast[i][12].equals("-1")) {
                additionalStr += "Номер главы: " + finalFoundWorksLast[i][12] + "\n";
            }
            if (!finalFoundWorksLast[i][13].equals("-1")) {
                additionalStr += "Номер параграфа: " + finalFoundWorksLast[i][13] + "\n";
            }
            if (!finalFoundWorksLast[i][14].equals("-1")) {
                additionalStr += "Номер публикации: " + finalFoundWorksLast[i][14] + "\n";
            }

            states.add(new SearchResult(mainStr, additionalStr));

            listSearchResult.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);

                    }
                    return false;
                }
            });

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

        }

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


       Cursor cursor = db.query(DatabaseHelper.TABLE_DISCIPLINES, null, null, null, null, null, null);
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

        public void moveToCreate(View view) {
            Bundle arguments = getIntent().getExtras();
            int id_user = arguments.getInt("id_user");

            Intent intent = new Intent(this, Download.class);
            intent.putExtra("id_user", id_user);
            startActivity(intent);
        }

}
