// DBHelper.java
package com.example.party6;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "party6.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "password TEXT)";
        db.execSQL(sql);
        // Вставляем тестовых пользователей
        db.execSQL("INSERT INTO users (username, password) VALUES ('admin', 'password')");
        db.execSQL("INSERT INTO users (username, password) VALUES ('user', 'password')");

        // Создаем таблицу для новостей
        db.execSQL("CREATE TABLE news (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "place TEXT," +
                "date TEXT)");
        // Вставляем новости
        db.execSQL("INSERT INTO news (title, place, date) VALUES ('Открытие SUBSIDIUM-23', 'КФЕН', '27 марта (Четверг) 18:00 ч')");
        db.execSQL("INSERT INTO news (title, place, date) VALUES ('Посвящение первокурсников КИТ', 'КИТ', '27 сентября (Суббота) 17:00 ч')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS news");
        onCreate(db);
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});
        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        return userExists;
    }

    // Метод для получения всех новостей из базы данных
    public List<NewsItem> getAllNews() {
        List<NewsItem> newsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM news", null);
        if (cursor.moveToFirst()) {
            do {
                NewsItem newsItem = new NewsItem();
                int titleIndex = cursor.getColumnIndex("title");
                int placeIndex = cursor.getColumnIndex("place");
                int dateIndex = cursor.getColumnIndex("date");
                if (titleIndex >= 0 && placeIndex >= 0 && dateIndex >= 0) {
                    newsItem.setTitle(cursor.getString(titleIndex));
                    newsItem.setPlace(cursor.getString(placeIndex));
                    newsItem.setDate(cursor.getString(dateIndex));
                    newsList.add(newsItem);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return newsList;
    }

}