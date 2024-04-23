package com.example.party6;// В MainActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.party6.DBHelper;
import com.example.party6.NewsItem;
import com.example.party6.ProfileActivity;

import java.util.List;

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
            setupAdminFeatures(); // Добавляем функционал для администратора
        } else {
            welcomeText.setText("Добро пожаловать User");
        }

        // Получаем список новостей из базы данных
        List<NewsItem> newsList = getNewsFromDatabase();

        // Отображаем новости в блоках новостей
        displayNews(newsList);

        // Обработка нажатия на кнопку "Кнопка 5"
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
            }
        });
    }

    private void setupAdminFeatures() {
        Button addEventButton = findViewById(R.id.addEventButton);
        addEventButton.setVisibility(View.VISIBLE); // Показываем кнопку добавления мероприятий
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Здесь можно добавить логику для добавления нового мероприятия в базу данных
                Toast.makeText(MainActivity.this, "Добавление нового мероприятия", Toast.LENGTH_SHORT).show();
                // После добавления мероприятия обновляем список новостей
                List<NewsItem> updatedNewsList = getNewsFromDatabase();
                displayNews(updatedNewsList);
            }
        });
    }

    private List<NewsItem> getNewsFromDatabase() {
        DBHelper dbHelper = new DBHelper(this);
        return dbHelper.getAllNews();
    }

    private void displayNews(List<NewsItem> newsList) {
        // Получаем ссылки на блоки новостей
        LinearLayout news1Layout = findViewById(R.id.news1Layout);
        LinearLayout news2Layout = findViewById(R.id.news2Layout);

        // Очищаем предыдущие новости
        news1Layout.removeAllViews();
        news2Layout.removeAllViews();

        // Перебираем список новостей и отображаем их в блоках новостей
        for (int i = 0; i < newsList.size(); i++) {
            NewsItem newsItem = newsList.get(i);
            TextView newsTextView = new TextView(this);
            newsTextView.setText(newsItem.getTitle() + "\nМесто: " + newsItem.getPlace() + "\nДата: " + newsItem.getDate());
            newsTextView.setTextSize(16);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 16);
            newsTextView.setLayoutParams(layoutParams);
            if (i == 0) {
                news1Layout.addView(newsTextView);
            } else if (i == 1) {
                news2Layout.addView(newsTextView);
            }
        }
    }

    // Метод для открытия активности профиля
    private void openProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
