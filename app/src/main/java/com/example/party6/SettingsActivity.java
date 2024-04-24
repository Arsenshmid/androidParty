package com.example.party6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private boolean isBlackBackground = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button helpButton = findViewById(R.id.helpButton);
        Button changeBackgroundButton = findViewById(R.id.changeBackgroundButton);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHelp();
            }
        });

        changeBackgroundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackground();
            }
        });
    }

    private void showHelp() {
        // Показать краткую справку
        Toast.makeText(this, "Это приложение для мероприятий и фотоаппаратов", Toast.LENGTH_SHORT).show();
    }

    private void changeBackground() {
        // Проверяем текущий цвет фона и меняем его
        if (!isBlackBackground) {
            // Сменить фон на черный
            getWindow().setBackgroundDrawableResource(android.R.color.black);
            isBlackBackground = true;
        } else {
            // Вернуть предыдущий фон (белый, например)
            getWindow().setBackgroundDrawableResource(android.R.color.white);
            isBlackBackground = false;
        }
    }

}
