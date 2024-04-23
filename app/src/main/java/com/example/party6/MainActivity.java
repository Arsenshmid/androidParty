package com.example.party6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получение данных о пользователе из предыдущей активности
        Intent intent = getIntent();
        String userType = intent.getStringExtra("userType");

        // Отображение приветствия в зависимости от типа пользователя
        TextView welcomeText = findViewById(R.id.welcomeText);
        if (userType != null && userType.equals("admin")) {
            welcomeText.setText("Добро пожаловать Admin");
        } else {
            welcomeText.setText("Добро пожаловать User");
        }
    }
}
