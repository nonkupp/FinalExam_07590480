package com.example.finalexam_07590480;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalexam_07590480.db.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase userDBBBBBBBBBBBBB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(RegisterActivity.this);
        userDBBBBBBBBBBBBB = dbHelper.getWritableDatabase();

        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText fullnameEditText = findViewById(R.id.full_name_edit_text);
                String inputname = fullnameEditText.getText().toString();
                EditText usernameEditText = findViewById(R.id.username_edit_text);
                String inputusername = usernameEditText.getText().toString();
                EditText passwordEditText = findViewById(R.id.password_edit_text);
                String inputpassword = passwordEditText.getText().toString();

                if(inputname.length() > 0 && inputusername.length() > 0 && inputpassword.length() > 0){
                    ContentValues cv = new ContentValues();
                    cv.put("name",inputname);
                    cv.put("username",inputusername);
                    cv.put("password",inputpassword);

                    userDBBBBBBBBBBBBB.insert("user",null,cv);
                    Toast.makeText(RegisterActivity.this,"Register successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegisterActivity.this,"All fields are required", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
