package com.example.myapplication;

import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH; // полный путь к базе данных
    private static final String DB_NAME = "informationBase.db";
    private static final int SCHEMA = 1; // версия базы данных

    static final String TABLE_USERS = "users"; // таблица с данными о пользователе в бд
    static final String COLUMN_ID_USER = "_id_user";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_EMAIL = "email";
    static final String COLUMN_PASSWORD = "password";
    static final String COLUMN_AVATAR = "avatar";
    static final String COLUMN_RATING = "rating";

    static final String TABLE_DISCIPLINES = "disciplines"; // таблица с информацией про научные дисциплины в бд
    static final String COLUMN_ID_DISCIPLINE = "_id_discipline";
    static final String COLUMN_NAME_DISCIPLINE = "name_discipline";

    static final String TABLE_AUTHORS = "authors"; // таблица с именами авторов преподователей в бд
    static final String COLUMN_ID_AUTHOR = "_id_author";
    static final String COLUMN_NAME_AUTHOR = "name_author";

    static final String TABLE_THEMES = "themes"; // таблица с возможными темами "работ" в бд
    static final String COLUMN_ID_THEME = "_id_theme";
    static final String COLUMN_THEME = "theme";

    static final String TABLE_WORK_DESCRIPTION = "work_description"; // таблица с описанием работ в бд
    static final String COLUMN_ID_WORK_DESCRIPTION = "_id_work_description";
    static final String COLUMN_TYPE_OF_WORK = "type_of_work";
    //COLUMN_ID_DISCIPLINE = "_id_discipline";
    //COLUMN_ID_AUTHOR = "_id_author";
    static final String COLUMN_TASK_NUMBER = "task_number";
    static final String COLUMN_OPTION_NUMBER = "option_number";
    static final String COLUMN_SECTION_NUMBER = "section_number";
    static final String COLUMN_CHAPTER_NUMBER = "chapter_number";
    static final String COLUMN_PARAGRAPH_NUMBER = "paragraph_number";
    static final String COLUMN_PUBLICATION_NUMBER = "publication_number";

    static final String TABLE_THEME_OF_WORK = "theme_of_work"; // таблица с тем какие темы имеют описания работ в бд
    //COLUMN_ID_WORK_DESCRIPTION = "_id_work_description";
    //COLUMN_ID_THEME = "_id_theme";

    static final String TABLE_USER_WORK = "user_work"; // таблица с работами пользователей в бд
    static final String COLUMN_ID_USER_WORK = "_id_user_work";
    //COLUMN_ID_USER = "_id_user";
    //COLUMN_ID_WORK_DESCRIPTION = "_id_work_description";
    static final String COLUMN_TEXT_WORK = "text_work";
    static final String COLUMN_FILE_FROM_WORK = "file_from_work";
    static final String COLUMN_TIME_OF_CREATION = "time_of_creation";

    static final String TABLE_LAST_VIEWED_SOLUTIONS = "last_viewed_solutions"; // таблица с последними просмотренными работами в бд
    //COLUMN_ID_USER = "_id_user";
    //COLUMN_ID_USER_WORK = "_id_user_work";

    static final String TABLE_COMMENTS = "comments"; // таблица с комментариями к работам в бд
    static final String COLUMN_ID_COMMENT = "_id_comment";
    //COLUMN_ID_USER_WORK = "_id_user_work";
    //COLUMN_ID_USER = "_id_user";    // id пользователя, который создал этот комментарий
    static final String COLUMN_COMMENT_TEXT = "comment_text";
    static final String COLUMN_SCORE = "score";

    private Context myContext;

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH =context.getFilesDir().getPath() + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) { }

    void create_db(){

        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                //получаем локальную бд как поток
                myInput = myContext.getAssets().open(DB_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH;

                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
            }
        }
        catch(IOException ex){
            Log.d("DatabaseHelper", ex.getMessage());
        }
        finally {
            try{
                if(myOutput!=null) myOutput.close();
                if(myInput!=null) myInput.close();
            }
            catch(IOException ex){
                Log.d("DatabaseHelper", ex.getMessage());
            }
        }
    }
    public SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}