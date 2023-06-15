package com.example.fueldelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Email;
    private EditText Password;

    private DBUser DBUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Name = findViewById(R.id.editText_name);
        Email = findViewById(R.id.editText_email);
        Password = findViewById(R.id.editText_password);

        Button registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        DBUser = new DBUser(this);
    }

    private void registerUser() {
        String name = Name.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Name.setError("Name is required");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Email.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Password.setError("Password is required");
            return;
        }

        if (DBUser.checkUser(email, password)) {
            Toast.makeText(this, "Email already exists", Toast.LENGTH_LONG).show();
            return;
        }

        long rowId = DBUser.addUser(name, email, password);

        if (rowId > 0) {
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}