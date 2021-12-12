package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fixProfileData extends AppCompatActivity {

    TextView oldName;
    TextView oldEmail;
    TextView textErrorFIx;

    EditText newName;
    EditText newEmail;
    EditText curentPassword;

    Button btnFix;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_profile_data);

        oldName = (TextView) findViewById(R.id.oldName);
        oldEmail = (TextView) findViewById(R.id.oldEmail);
        textErrorFIx = (TextView) findViewById(R.id.textErrorFIx);

        newName = (EditText) findViewById(R.id.newName);
        newEmail = (EditText) findViewById(R.id.newEmail);
        curentPassword = (EditText) findViewById(R.id.curentPassword);

        btnFix = (Button) findViewById(R.id.btnFix);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        databaseHelper.create_db();
        db = databaseHelper.open();

        Bundle arguments = getIntent().getExtras();
        int id_user = arguments.getInt("id_user");
        String ownerName = arguments.getString("ownerName");
        String ownerEmail = arguments.getString("ownerEmail");

        oldName.setText(ownerName);
        oldEmail.setText(ownerEmail);
        textErrorFIx.setVisibility(View.GONE);



        btnFix.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String loginValue = newName.getText().toString();
                String emailValue = newEmail.getText().toString();
                String passwordValue = curentPassword.getText().toString();

                String error = "";
                boolean errorFlag = true;


                if (loginValue.equals("") && emailValue.equals("")) {
                    error += "\n- вы не ввели новые данные";
                    errorFlag = false;
                }

                if (passwordValue.equals("") ) {
                    error += "\n- вы не ввели пароль";
                    errorFlag = false;
                }


                if (errorFlag) {

                    if (!loginValue.equals("") && loginValue.length() < 5) {
                        error += "\n- ваш 'логин' должен содержать не менее 5 символов";
                        errorFlag = false;
                    }

                    final String LOGIN_PATTERN = "^[a-zA-Z0-9]+$";
                    Pattern pattern = Pattern.compile(LOGIN_PATTERN);
                    Matcher matcher = pattern.matcher(loginValue);

                    if (!loginValue.equals("") && !matcher.matches()) {
                        error += "\n- в поле 'Логин' можно использовать только латинские символы и цифры";
                        errorFlag = false;
                    }

                    final String EMAIL_PATTERN =
                            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                    pattern = Pattern.compile(EMAIL_PATTERN);
                    matcher = pattern.matcher(emailValue);
                    if (!emailValue.equals("") && !matcher.matches()) {
                        error += "\n- вы ввели недопустимые символы для поля 'почта'";
                        errorFlag = false;
                    }

                    if (errorFlag) {

                        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, null, null, null, null, null, null);

                        if (cursor.moveToFirst()) {
                            int idName = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);
                            int idemail = cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL);

                            do {
                                if (!loginValue.equals("") && cursor.getString(idName).equals(loginValue)) {
                                    error += "\n- пользователь с таким логином уже существует";
                                    errorFlag = false;
                                    break;
                                }
                            } while (cursor.moveToNext());
                            cursor.moveToFirst();
                            do {
                                if (!emailValue.equals("") && cursor.getString(idemail).equals(emailValue)) {
                                    error += "\n- пользователь с такой почтой уже существует";
                                    errorFlag = false;
                                    break;
                                }
                            } while (cursor.moveToNext());
                        }

                        cursor.close();

                        if (errorFlag) {

                            cursor = db.query(DatabaseHelper.TABLE_USERS, null, null, null, null, null, null);

                            if (cursor.moveToFirst()) {
                                int id_user_in_DB = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_USER);
                                int password = cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD);

                                do {
                                    if (cursor.getInt(id_user_in_DB) == id_user) {
                                        if (!cursor.getString(password).equals(passwordValue)) {
                                            error += "\n- вы ввели неверный пароль";
                                            errorFlag = false;
                                        }
                                        break;
                                    }
                                } while (cursor.moveToNext());
                            }
                            cursor.close();

                            if (errorFlag) {

                                ContentValues cv = new ContentValues();

                                if (!loginValue.equals("")) {
                                    cv.put(DatabaseHelper.COLUMN_NAME, loginValue);

                                    db.update(DatabaseHelper.TABLE_USERS, cv, DatabaseHelper.COLUMN_ID_USER + "=" + id_user, null);
                                }

                                if (!emailValue.equals("")) {

                                    cv = new ContentValues();
                                    cv.put(DatabaseHelper.COLUMN_EMAIL, emailValue);

                                    db.update(DatabaseHelper.TABLE_USERS, cv, DatabaseHelper.COLUMN_ID_USER + "=" + id_user, null);

                                    db.close();


                                }

                                Intent intent = new Intent(getApplicationContext(), UsersProfile.class);
                                intent.putExtra("id_user", id_user);
                                intent.putExtra("id_user_owner", id_user);
                                startActivity(intent);
                            }
                        }
                    }
                }

                    if (!errorFlag) {
                        error = "При редактировании возникли ошибки:" + error;
                        textErrorFIx.setText(error);

                        textErrorFIx.setVisibility(View.VISIBLE);
                    }


                }
        });

    }
}
