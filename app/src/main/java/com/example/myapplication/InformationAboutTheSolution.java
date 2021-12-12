package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InformationAboutTheSolution extends AppCompatActivity {

    ArrayList<SimilarSolutions> states = new ArrayList<SimilarSolutions>();
    ArrayList<userСomments> states2 = new ArrayList<userСomments>();

    ListView listSimilarSolution;
    ListView listComment;

    TextView textWork;
    TextView dateOfCreation;
    TextView сreatorName;
    TextView characteristicsText;
    TextView textComment;
    TextView textScore;
    TextView textLackOfSolutions;
    TextView textErrorRequest;
    TextView textErrorRequest5;

    Button btnTextWork;
    Button btnCharacteristicsText;
    Button btnAddComment;
    Button btnOpenFile;
    Button btnListSimilarSolutions;

    EditText editTextComment;

    ImageView imageHeart;
    ImageView imageWork;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor1;
    Cursor cursor2;
    Cursor cursor3;
    Cursor cursor4;
    Cursor cursor5;
    Cursor cursor6;
    Cursor cursor7;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_information_about_the_solution);

        listSimilarSolution = (ListView) findViewById(R.id.listSimilarSolution);
        listComment = (ListView) findViewById(R.id.listComment);

        textWork = (TextView) findViewById(R.id.textWork);
        dateOfCreation = (TextView) findViewById(R.id.dateOfCreation);
        сreatorName = (TextView) findViewById(R.id.сreatorName);
        characteristicsText = (TextView) findViewById(R.id.characteristicsText);
        textScore = (TextView) findViewById(R.id.textScore);
        textLackOfSolutions = (TextView) findViewById(R.id.textLackOfSolutions);
        textErrorRequest = (TextView) findViewById(R.id.textErrorRequest);
        textErrorRequest5 = (TextView) findViewById(R.id.textErrorRequest5);

        editTextComment = (EditText) findViewById(R.id.editTextComment);

        imageHeart = (ImageView) findViewById(R.id.imageHeart);
        imageWork = (ImageView) findViewById(R.id.imageWork);

        btnTextWork = (Button) findViewById(R.id.btnTextWork);
        btnCharacteristicsText = (Button) findViewById(R.id.btnCharacteristicsText);
        btnAddComment = (Button) findViewById(R.id.btnAddComment);
        btnOpenFile = (Button) findViewById(R.id.btnOpenFile);
        btnListSimilarSolutions = (Button) findViewById(R.id.btnListSimilarSolutions);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        databaseHelper.create_db();
        db = databaseHelper.open();

        textScore.setVisibility(View.GONE);
        imageHeart.setVisibility(View.GONE);



        Bundle arguments = getIntent().getExtras();
        int id_user = arguments.getInt("id_user");
        int id_user_work = arguments.getInt("id_user_work");


        String text_work = null;
        String time_of_creation = null;
        int id_work_description = 0;
        int id_creator = 0;

        String string_Image = null;

        cursor1 = db.query(DatabaseHelper.TABLE_USER_WORK, null, null, null, null, null, null);

        if (cursor1.moveToFirst()) {
            int id_user_work_in_DB = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_USER_WORK);
            id_work_description = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_WORK_DESCRIPTION);
            id_creator = cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_USER);
            int id_text_work = cursor1.getColumnIndex(DatabaseHelper.COLUMN_TEXT_WORK);
            int id_time_of_creation = cursor1.getColumnIndex(DatabaseHelper.COLUMN_TIME_OF_CREATION);
            int id_file_from_work = cursor1.getColumnIndex(DatabaseHelper.COLUMN_FILE_FROM_WORK);


            do {
                if (cursor1.getInt(id_user_work_in_DB) == id_user_work) {
                    text_work = cursor1.getString(id_text_work);
                    id_work_description = cursor1.getInt(id_work_description);
                    id_creator = cursor1.getInt(id_creator);
                    time_of_creation = cursor1.getString(id_time_of_creation);
                    string_Image = cursor1.getString(id_file_from_work);
                }
            } while (cursor1.moveToNext());
        }
        cursor1.close();

        String name = null;

        cursor2 = db.query(DatabaseHelper.TABLE_USERS, null, null, null, null, null, null);

        if (cursor2.moveToFirst()) {
            int id_user_in_DB = cursor2.getColumnIndex(DatabaseHelper.COLUMN_ID_USER);
            int id_name = cursor2.getColumnIndex(DatabaseHelper.COLUMN_NAME);

            do {
                if (cursor2.getInt(id_user_in_DB) == id_creator) {
                    name = cursor2.getString(id_name);
                    break;
                }
            } while (cursor2.moveToNext());
        }

        cursor2.close();

        name = "Создатель: " + name;


        String type_of_work = null;
        int id_discipline = 0;
        int id_author = 0;
        int task_number = 0;
        int option_number = 0;
        int section_number = 0;
        int chapter_number = 0;
        int paragraph_number = 0;
        int publication_number = 0;

        cursor3 = db.query(DatabaseHelper.TABLE_WORK_DESCRIPTION, null, null, null, null, null, null);

        if (cursor3.moveToFirst()) {
            int id_work_description_in_DB = cursor3.getColumnIndex(DatabaseHelper.COLUMN_ID_WORK_DESCRIPTION);
            int id_type_of_work = cursor3.getColumnIndex(DatabaseHelper.COLUMN_TYPE_OF_WORK);
            id_discipline = cursor3.getColumnIndex(DatabaseHelper.COLUMN_ID_DISCIPLINE);
            id_author = cursor3.getColumnIndex(DatabaseHelper.COLUMN_ID_AUTHOR);
            task_number = cursor3.getColumnIndex(DatabaseHelper.COLUMN_TASK_NUMBER);
            option_number = cursor3.getColumnIndex(DatabaseHelper.COLUMN_OPTION_NUMBER);
            section_number = cursor3.getColumnIndex(DatabaseHelper.COLUMN_SECTION_NUMBER);
            chapter_number = cursor3.getColumnIndex(DatabaseHelper.COLUMN_CHAPTER_NUMBER);
            paragraph_number = cursor3.getColumnIndex(DatabaseHelper.COLUMN_PARAGRAPH_NUMBER);
            publication_number = cursor3.getColumnIndex(DatabaseHelper.COLUMN_PUBLICATION_NUMBER);

            do {
                if (cursor3.getInt(id_work_description_in_DB) == id_work_description) {
                    type_of_work = cursor3.getString(id_type_of_work);
                    id_discipline = cursor3.getInt(id_discipline);
                    id_author = cursor3.getInt(id_author);
                    task_number = cursor3.getInt(task_number);
                    option_number = cursor3.getInt(option_number);
                    section_number = cursor3.getInt(section_number);
                    chapter_number = cursor3.getInt(chapter_number);
                    paragraph_number = cursor3.getInt(paragraph_number);
                    publication_number = cursor3.getInt(publication_number);
                    break;
                }
            } while (cursor3.moveToNext());
        }
        cursor3.close();

        String name_discipline = null;

        cursor4 = db.query(DatabaseHelper.TABLE_DISCIPLINES, null, null, null, null, null, null);

        if (cursor4.moveToFirst()) {
            int id_discipline_in_DB = cursor4.getColumnIndex(DatabaseHelper.COLUMN_ID_DISCIPLINE);
            int id_name_discipline = cursor4.getColumnIndex(DatabaseHelper.COLUMN_NAME_DISCIPLINE);

            do {
                if (cursor4.getInt(id_discipline_in_DB) == id_discipline) {
                    name_discipline = cursor4.getString(id_name_discipline);
                    break;
                }
            } while (cursor4.moveToNext());
        }
        cursor4.close();

        String name_author = null;

        cursor4 = db.query(DatabaseHelper.TABLE_AUTHORS, null, null, null, null, null, null);

        if (cursor4.moveToFirst()) {
            int id_author_in_DB = cursor4.getColumnIndex(DatabaseHelper.COLUMN_ID_AUTHOR);
            int id_name_author = cursor4.getColumnIndex(DatabaseHelper.COLUMN_NAME_AUTHOR);

            do {
                if (cursor4.getInt(id_author_in_DB) == id_author) {
                    name_author = cursor4.getString(id_name_author);
                    break;
                }
            } while (cursor4.moveToNext());
        }
        cursor4.close();

        int idThemes[] = {0, 0, 0, 0, 0};
        int numberOfThemes = 0;

        cursor5 = db.query(DatabaseHelper.TABLE_THEME_OF_WORK, null, null, null, null, null, null);

        if (cursor5.moveToFirst()) {
            int id_work_description_in_DB = cursor5.getColumnIndex(DatabaseHelper.COLUMN_ID_WORK_DESCRIPTION);
            int id_theme = cursor5.getColumnIndex(DatabaseHelper.COLUMN_ID_THEME);

            do {
                if (numberOfThemes == 5) {
                    break;
                }


                if (cursor5.getInt(id_work_description_in_DB) == id_work_description) {
                    idThemes[numberOfThemes] = cursor5.getInt(id_theme);
                    numberOfThemes += 1;

                }
            } while (cursor5.moveToNext());
        }
        cursor5.close();

        String[] nameThemes = new String[numberOfThemes];

        cursor6 = db.query(DatabaseHelper.TABLE_THEMES, null, null, null, null, null, null);

        if (cursor6.moveToFirst()) {
            int id_theme_in_DB = cursor6.getColumnIndex(DatabaseHelper.COLUMN_ID_THEME);
            int id_name_theme = cursor6.getColumnIndex(DatabaseHelper.COLUMN_THEME);

            int i = 0;

            do {

                if (i == (numberOfThemes)) {
                    break;
                }

                if (cursor6.getInt(id_theme_in_DB) == idThemes[0]) {
                    nameThemes[i] = cursor6.getString(id_name_theme);
                    i++;
                }

                if (cursor6.getInt(id_theme_in_DB) == idThemes[1]) {
                    nameThemes[i] = cursor6.getString(id_name_theme);
                    i++;
                }

                if (cursor6.getInt(id_theme_in_DB) == idThemes[2]) {
                    nameThemes[i] = cursor6.getString(id_name_theme);
                    i++;
                }

                if (cursor6.getInt(id_theme_in_DB) == idThemes[3]) {
                    nameThemes[i] = cursor6.getString(id_name_theme);
                    i++;
                }

                if (cursor6.getInt(id_theme_in_DB) == idThemes[4]) {
                    nameThemes[i] = cursor6.getString(id_name_theme);
                    i++;
                }

            } while (cursor6.moveToNext());
        }
        cursor6.close();

        String сharacteristicsOfWork = "Тип работы: " + type_of_work + "\n";
        сharacteristicsOfWork += "Дисциплина: " + name_discipline + "\n";
        ;
        сharacteristicsOfWork += "Автор: " + name_author;

        if (numberOfThemes > 1) {

            for (int i = 0; i < numberOfThemes; i++) {
                сharacteristicsOfWork += "\nТема " + Integer.toString(i + 1) + ": " + nameThemes[i];
            }

        } else {
            сharacteristicsOfWork += "\nТема: " + nameThemes[0];
        }

        if (task_number != -1) {
            сharacteristicsOfWork += "\nНомер задания: " + Integer.toString(task_number);
        }

        if (option_number != -1) {
            сharacteristicsOfWork += "\nНомер варианта: " + Integer.toString(option_number);
        }

        if (section_number != -1) {
            сharacteristicsOfWork += "\nНомер раздела: " + Integer.toString(section_number);
        }

        if (chapter_number != -1) {
            сharacteristicsOfWork += "\nНомер главы: " + Integer.toString(chapter_number);
        }

        if (paragraph_number != -1) {
            сharacteristicsOfWork += "\nНомер параграфа: " + Integer.toString(paragraph_number);
        }

        if (publication_number != -1) {
            сharacteristicsOfWork += "\nНомер публикации: " + Integer.toString(publication_number);

        }

        characteristicsText.setText(сharacteristicsOfWork);
        characteristicsText.setVisibility(View.GONE);

        btnCharacteristicsText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (btnCharacteristicsText.getText().equals("Показать")) {
                    btnCharacteristicsText.setText("Скрыть");
                    characteristicsText.setVisibility(View.VISIBLE);
                } else {
                    btnCharacteristicsText.setText("Показать");
                    characteristicsText.setVisibility(View.GONE);
                }
            }
        });

        сreatorName.setText(name);
        dateOfCreation.setText(time_of_creation);

        String finalText_work = text_work;

        if (finalText_work.length() < 150) {
            btnTextWork.setVisibility(View.GONE);
            textWork.setText(finalText_work);
        } else {
            String onTheScreen = finalText_work.substring(0, 151);
            onTheScreen = onTheScreen + "...";
            textWork.setText(onTheScreen);
        }

        btnTextWork.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (btnTextWork.getText().equals("Раскрыть")) {
                    btnTextWork.setText("Скрыть");
                    textWork.setText(finalText_work);
                } else {
                    btnTextWork.setText("Раскрыть");
                    String onTheScreen = finalText_work.substring(0, 151);
                    onTheScreen = onTheScreen + "...";
                    textWork.setText(onTheScreen);
                }
            }
        });

        String finalString_Image = string_Image;
        if (finalString_Image == null) {
            btnOpenFile.setVisibility(View.GONE);
        }


        btnOpenFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                byte[] decodedString = Base64.decode(finalString_Image, Base64.DEFAULT);

                // Декодируем массив байтов в изображение
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                // Помещаем изображение в ImageView
                imageWork.setImageBitmap(decodedByte);


            }
        });

        listSimilarSolution.setVisibility(View.GONE);
        textLackOfSolutions.setVisibility(View.GONE);


        int n = 0;

        cursor7 = db.query(DatabaseHelper.TABLE_USER_WORK, null, null, null, null, null, null);

        if (cursor7.moveToFirst()) {
            int id_work_description_in_DB = cursor7.getColumnIndex(DatabaseHelper.COLUMN_ID_WORK_DESCRIPTION);

            do {
                if (cursor7.getInt(id_work_description_in_DB) == id_work_description) {
                    n++;
                }
            } while (cursor7.moveToNext());
        }
        cursor7.close();


        if (n != 0) {


            String[][] othersWork = new String[n][3];

            cursor7 = db.query(DatabaseHelper.TABLE_USER_WORK, null, null, null, null, null, null);

            if (cursor7.moveToFirst()) {
                int i = 0;

                int id_other_user_work = cursor7.getColumnIndex(DatabaseHelper.COLUMN_ID_USER_WORK);
                int id_work_description_in_DB = cursor7.getColumnIndex(DatabaseHelper.COLUMN_ID_WORK_DESCRIPTION);
                int id_other_user = cursor7.getColumnIndex(DatabaseHelper.COLUMN_ID_USER);
                int id_other_time = cursor7.getColumnIndex(DatabaseHelper.COLUMN_TIME_OF_CREATION);

                do {

                    if (i == n) {
                        break;
                    }

                    if (cursor7.getInt(id_other_user_work) == id_user_work) {
                        n--;
                    }

                    if ((cursor7.getInt(id_work_description_in_DB) == id_work_description) && (cursor7.getInt(id_other_user_work) != id_user_work)) {
                        othersWork[i][0] = Integer.toString(cursor7.getInt(id_other_user_work));
                        othersWork[i][1] = Integer.toString(cursor7.getInt(id_other_user));
                        othersWork[i][2] = cursor7.getString(id_other_time);
                        i++;
                    }
                } while (cursor7.moveToNext());
            }
            cursor7.close();

            for (int i = 0; i < n; i++) {

                cursor7 = db.query(DatabaseHelper.TABLE_USERS, null, null, null, null, null, null);

                if (cursor7.moveToFirst()) {

                    int id_other_user = cursor7.getColumnIndex(DatabaseHelper.COLUMN_ID_USER);
                    int id_name = cursor7.getColumnIndex(DatabaseHelper.COLUMN_NAME);

                    do {

                        if (Integer.toString(cursor7.getInt(id_other_user)).equals(othersWork[i][1])) {
                            othersWork[i][1] = cursor7.getString(id_name);
                            break;
                        }
                    } while (cursor7.moveToNext());
                }
                cursor7.close();
            }



            // создаем адаптер
            SimilarSolutionsAdapter stateAdapter = new SimilarSolutionsAdapter(this, R.layout.listsimilarsolutions_item, states);
            // устанавливаем адаптер
            listSimilarSolution.setAdapter(stateAdapter);
            // слушатель выбора в списке
            AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    Intent intent = new Intent(getApplicationContext(), InformationAboutTheSolution.class);
                    intent.putExtra("id_user", id_user);
                    intent.putExtra("id_user_work", Integer.valueOf(othersWork[position][0]));
                    startActivity(intent);
                }
            };
            listSimilarSolution.setOnItemClickListener(itemListener);

            setInitialData(othersWork, n);
        }

        int finalN = n;
        btnListSimilarSolutions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (finalN != 0) {
                    if (listSimilarSolution.getVisibility() == View.GONE) {
                        listSimilarSolution.setVisibility(View.VISIBLE);
                        btnListSimilarSolutions.setText("Скрыть решения");
                    } else {
                        listSimilarSolution.setVisibility(View.GONE);
                        btnListSimilarSolutions.setText("Похожие решения");
                    }
                } else {
                    if (textLackOfSolutions.getVisibility() == View.GONE) {
                        textLackOfSolutions.setVisibility(View.VISIBLE);
                    } else {
                        textLackOfSolutions.setVisibility(View.GONE);
                    }

                }

            }
        });

        int count = 0;

        Cursor cursor =  db.query(DatabaseHelper.TABLE_COMMENTS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int id_user_work_in_DB = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_USER_WORK);

            do {
                if (cursor.getInt(id_user_work_in_DB) == id_user_work) {
                    count++;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        String[][] commentsUserContent = new String[count][3];

            int j = 0;

            cursor = db.query(DatabaseHelper.TABLE_COMMENTS, null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                int id_user_work_in_DB = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_USER_WORK);
                int _id_user = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_USER);
                int comment_text = cursor.getColumnIndex(DatabaseHelper.COLUMN_COMMENT_TEXT);
                int time_comment_creation = cursor.getColumnIndex(DatabaseHelper.COLUMN_TIME_COMMENT_CREATION);

                do {
                    if (j == count) {
                        break;
                    }

                    if (cursor.getInt(id_user_work_in_DB) == id_user_work) {
                        commentsUserContent[j][0] = Integer.toString(cursor.getInt(_id_user));
                        commentsUserContent[j][1] = cursor.getString(time_comment_creation);
                        commentsUserContent[j][2] = cursor.getString(comment_text);
                        j++;
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();

            for (int i = 0; i < count; i++) {


                cursor = db.query(DatabaseHelper.TABLE_USERS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int _id_user = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_USER);
                    int nameCommentator = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);

                    do {
                        if (Integer.toString(cursor.getInt(_id_user)).equals(commentsUserContent[i][0])) {
                            commentsUserContent[i][0] = cursor.getString(nameCommentator);
                        }
                    } while (cursor.moveToNext());
                }
                cursor.close();

            }



            userСommentsAdapter stateAdapter2 = new userСommentsAdapter(this, R.layout.listusercomments, states2);
            // устанавливаем адаптер
            listComment.setAdapter(stateAdapter2);
            // слушатель выбора в списке


            setInitialData2(commentsUserContent, count);


            textErrorRequest.setVisibility(View.GONE);
            textErrorRequest5.setVisibility(View.GONE);

            btnAddComment.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (editTextComment.getText().toString().equals("")) {
                        textErrorRequest.setVisibility(View.VISIBLE);
                        textErrorRequest5.setVisibility(View.VISIBLE);
                    } else {
                        textErrorRequest.setVisibility(View.GONE);
                        textErrorRequest5.setVisibility(View.GONE);

                        Date currentDate = new Date();
                        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
                        String dateText = df.format(Calendar.getInstance().getTime());

                        ContentValues cv = new ContentValues();
                        cv.put(DatabaseHelper.COLUMN_ID_USER_WORK, id_user_work);
                        cv.put(DatabaseHelper.COLUMN_ID_USER, id_user);
                        cv.put(DatabaseHelper.COLUMN_COMMENT_TEXT, editTextComment.getText().toString());
                        cv.put(DatabaseHelper.COLUMN_TIME_COMMENT_CREATION, dateText);

                        db.insert(DatabaseHelper.TABLE_COMMENTS, null, cv);

                        int count = 0;

                        Cursor cursor =  db.query(DatabaseHelper.TABLE_COMMENTS, null, null, null, null, null, null);

                        if (cursor.moveToFirst()) {
                            int id_user_work_in_DB = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_USER_WORK);

                            do {
                                if (cursor.getInt(id_user_work_in_DB) == id_user_work) {
                                    count++;
                                }
                            } while (cursor.moveToNext());
                        }
                        cursor.close();

                        String[][] commentsUserContent = new String[count][3];

                            int j = 0;

                            cursor = db.query(DatabaseHelper.TABLE_COMMENTS, null, null, null, null, null, null);

                            if (cursor.moveToFirst()) {
                                int id_user_work_in_DB = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_USER_WORK);
                                int _id_user = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_USER);
                                int comment_text = cursor.getColumnIndex(DatabaseHelper.COLUMN_COMMENT_TEXT);
                                int time_comment_creation = cursor.getColumnIndex(DatabaseHelper.COLUMN_TIME_COMMENT_CREATION);

                                do {
                                    if(j==count) {
                                        break;
                                    }

                                    if (cursor.getInt(id_user_work_in_DB) == id_user_work) {
                                        commentsUserContent[j][0] = Integer.toString(cursor.getInt(_id_user));
                                        commentsUserContent[j][1] = cursor.getString(time_comment_creation);
                                        commentsUserContent[j][2] = cursor.getString(comment_text);
                                        j++;
                                    }
                                } while (cursor.moveToNext());
                            }
                            cursor.close();

                        for (int i = 0; i < count; i++) {

                            cursor = db.query(DatabaseHelper.TABLE_USERS, null, null, null, null, null, null);

                            if (cursor.moveToFirst()) {
                                int _id_user = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_USER);
                                int nameCommentator = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);

                                do {
                                    if (Integer.toString(cursor.getInt(_id_user)).equals(commentsUserContent[i][0])) {
                                        commentsUserContent[i][0] = cursor.getString(nameCommentator);
                                    }
                                } while (cursor.moveToNext());
                            }
                            cursor.close();

                    }
                        listComment.setAdapter(null);
                        stateAdapter2.notifyDataSetChanged();
                        //listComment.setAdapter(null);
                        //userСommentsAdapter stateAdapter3 = new userСommentsAdapter(getApplicationContext(), R.layout.listusercomments, states2);
                        listComment.setAdapter(stateAdapter2);
                        stateAdapter2.notifyDataSetChanged();
                        setInitialData3(commentsUserContent, count);



                }
                }
            });



    }

    private void setInitialData(String[][] othersWork, int n) {
        for (int i = 0; i < n; i++) {

            states.add(new SimilarSolutions(othersWork[i][1], othersWork[i][2]));
        }

    }

    private void setInitialData2(String[][] commentsUserContent, int n) {
        for (int i = 0; i < n; i++) {

            states2.add(new userСomments(commentsUserContent[i][0], commentsUserContent[i][2], commentsUserContent[i][1]));
        }

    }
    private void setInitialData3(String[][] commentsUserContent, int n) {


        states2.add(new userСomments(commentsUserContent[n-1][0], commentsUserContent[n-1][2], commentsUserContent[n-1][1]));


    }


    public void moveToDownload(View view) {
        //Intent intent = new Intent(this, SimilarSolutions.class);
        //startActivity(intent);
    }
}