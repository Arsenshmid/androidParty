package com.example.party6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Button addEventButton = findViewById(R.id.addEventButtonAddActivity);
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем данные из полей ввода
                String title = ((EditText) findViewById(R.id.eventTitleEditText)).getText().toString();
                String place = ((EditText) findViewById(R.id.eventPlaceEditText)).getText().toString();
                String date = ((EditText) findViewById(R.id.eventDateEditText)).getText().toString();

                // Создаем экземпляр DBHelper для работы с базой данных
                DBHelper dbHelper = new DBHelper(AddEventActivity.this);

                // Добавляем новое мероприятие в базу данных
                dbHelper.addEvent(title, place, date);

                // Выводим сообщение об успешном добавлении мероприятия
                Toast.makeText(AddEventActivity.this, "Мероприятие успешно добавлено!", Toast.LENGTH_SHORT).show();

                // Закрываем текущую активность
                finish();
            }
        });
    }
}
