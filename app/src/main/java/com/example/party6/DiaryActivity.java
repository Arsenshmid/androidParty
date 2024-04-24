package com.example.party6;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);


        TextView textView = findViewById(R.id.textView);
        textView.setText("Дневник"); // Пример установки текста в TextView
    }
}
