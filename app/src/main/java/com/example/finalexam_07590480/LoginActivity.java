package com.example.finalexam_07590480;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalexam_07590480.db.DatabaseHelper;
import com.example.finalexam_07590480.model.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private List<User> mUser = new ArrayList<>();
    DatabaseHelper dbHelper;
    SQLiteDatabase UserDBBBBBBBBBBBBBBBBBBB;
    Cursor mCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(LoginActivity.this);
        UserDBBBBBBBBBBBBBBBBBBB = dbHelper.getWritableDatabase();
        mCursor = UserDBBBBBBBBBBBBBBBBBBB.rawQuery("SELECT " + DatabaseHelper.COL_NAME+","+DatabaseHelper.COL_USERNAME+", "+DatabaseHelper.COL_PASSWORD+" FROM " + DatabaseHelper.TABLE_USER, null);
        mCursor.moveToFirst();
        User user ;

        while(!mCursor.isAfterLast()) {
            user = new User(0,
                    mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_NAME)),
                    mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_USERNAME)),
                    mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_PASSWORD)));
            mUser.add(user);
            mCursor.moveToNext();
        }

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameEditText = findViewById(R.id.username_edit_text);
                String username = usernameEditText.getText().toString();
                EditText passwordEditText = findViewById(R.id.password_edit_text);
                String password = passwordEditText.getText().toString();

                //full box
                if(username.length() > 0 && password.length() > 0) {
                    // check in DB
                    int check = 0;
                    for (User user11 : mUser) {
                        //Match correct
                        if (username.equals(user11.getUsername()) && password.equals(user11.getPassword()) ){
                            Toast.makeText(LoginActivity.this,"Welcome " + user11.getName(), Toast.LENGTH_LONG).show();
                            check = 1;
                            break;
                        }
                    }
                    //not correct
                    if(check == 0){
                        Toast.makeText(LoginActivity.this,"Invalid username or password", Toast.LENGTH_LONG).show();
                    }
                //empty box
                }else {
                    Toast.makeText(LoginActivity.this,"All fields are required", Toast.LENGTH_LONG).show();

                }

            }
        });
        //link register
        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

}
