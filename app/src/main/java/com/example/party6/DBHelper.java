package com.example.party6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PartyDB";
    private static final int DATABASE_VERSION = 1;

    // Таблица событий
    private static final String TABLE_EVENTS = "events";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_PLACE = "place";
    private static final String KEY_DATE = "date";

    // Таблица аппаратуры
    private static final String TABLE_EQUIPMENT = "equipment";
    private static final String KEY_EQUIPMENT_ID = "id";
    private static final String KEY_EQUIPMENT_NAME = "name";
    private static final String KEY_USER_TYPE = "user_type"; // Добавляем новый столбец для отслеживания пользователя

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создание таблицы событий
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_PLACE + " TEXT," + KEY_DATE + " TEXT" + ")";
        db.execSQL(CREATE_EVENTS_TABLE);

        // Создание таблицы аппаратуры
        String CREATE_EQUIPMENT_TABLE = "CREATE TABLE " + TABLE_EQUIPMENT + "("
                + KEY_EQUIPMENT_ID + " INTEGER PRIMARY KEY," + KEY_EQUIPMENT_NAME + " TEXT,"
                + KEY_USER_TYPE + " TEXT" + ")"; // Добавляем столбец для отслеживания пользователя
        db.execSQL(CREATE_EQUIPMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Удаление старых таблиц и создание новых при обновлении БД
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EQUIPMENT);
        onCreate(db);
    }

    // Добавление события в БД
    public void addEvent(String title, String place, String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_PLACE, place);
        values.put(KEY_DATE, date);

        db.insert(TABLE_EVENTS, null, values);
        db.close();
    }

    // Получение всех событий из БД
    public List<NewsItem> getAllEvents() {
        List<NewsItem> eventList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_EVENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                NewsItem newsItem = new NewsItem(cursor.getString(1), cursor.getString(2), cursor.getString(3));
                eventList.add(newsItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return eventList;
    }

    // Добавление аппаратуры в БД
    public void addEquipment(String equipment, String userType) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EQUIPMENT_NAME, equipment);
        values.put(KEY_USER_TYPE, userType); // Сохраняем тип пользователя

        db.insert(TABLE_EQUIPMENT, null, values);
        db.close();
    }

    // Получение списка аппаратуры для указанного пользователя
    public List<String> getEquipmentForUser(String userType) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> equipmentList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_EQUIPMENT, new String[]{KEY_EQUIPMENT_NAME}, KEY_USER_TYPE + "=?", new String[]{userType}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String equipment = cursor.getString(cursor.getColumnIndex(KEY_EQUIPMENT_NAME));
                equipmentList.add(equipment);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return equipmentList;
    }
}
