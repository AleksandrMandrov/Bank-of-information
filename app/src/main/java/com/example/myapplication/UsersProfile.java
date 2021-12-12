package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UsersProfile extends AppCompatActivity {

    TextView grade3;
    TextView grade14;
    TextView nameProfile;
    TextView emailProfile;
    TextView countWorks;
    TextView exit;

    Button red8;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_profile);

        red8 = (Button) findViewById(R.id.red8);


        grade3 = (TextView) findViewById(R.id.grade3);
        grade14 = (TextView) findViewById(R.id.grade14);
        nameProfile = (TextView) findViewById(R.id.nameProfile);
        emailProfile = (TextView) findViewById(R.id.emailProfile);
        countWorks = (TextView) findViewById(R.id.countWorks);
        exit = (TextView) findViewById(R.id.exit);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        databaseHelper.create_db();
        db = databaseHelper.open();

        grade3.setVisibility(View.GONE);
        grade14.setVisibility(View.GONE);
        red8.setVisibility(View.GONE);

        Bundle arguments = getIntent().getExtras();
        int id_user_owner = arguments.getInt("id_user_owner");
        int id_user = arguments.getInt("id_user");

        String ownerName = null;
        String ownerEmail = null;


        cursor = db.query(DatabaseHelper.TABLE_USERS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int id_user_in_DB = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_USER);
            int id_name = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);
            int id_email = cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL);

            do {
                if (cursor.getInt(id_user_in_DB) == id_user_owner) {
                    ownerName = cursor.getString(id_name);
                    ownerEmail = cursor.getString(id_email);
                    break;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        nameProfile.setText(ownerName);
        emailProfile.setText(ownerEmail);

        int count = 0;

        cursor = db.query(DatabaseHelper.TABLE_USER_WORK, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int id_user_in_DB = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_USER);

            do {
                if (cursor.getInt(id_user_in_DB) == id_user_owner) {
                    count++;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        countWorks.setText(Integer.toString(count));

        if (id_user == id_user_owner) {
            red8.setVisibility(View.VISIBLE);
        }

        String finalOwnerName = ownerName;
        String finalOwnerEmail = ownerEmail;
        red8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Bundle arguments = getIntent().getExtras();
                int id_user = arguments.getInt("id_user");

                Intent intent = new Intent(getApplicationContext(), fixProfileData.class);
                intent.putExtra("id_user", id_user);
                intent.putExtra("ownerName", finalOwnerName);
                intent.putExtra("ownerEmail", finalOwnerEmail);
                startActivity(intent);

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Bundle arguments = getIntent().getExtras();
                int id_user = arguments.getInt("id_user");

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }
}