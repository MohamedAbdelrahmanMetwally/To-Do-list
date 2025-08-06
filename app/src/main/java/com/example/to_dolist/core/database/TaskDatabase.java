package com.example.to_dolist.core.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
public class TaskDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASKS = "tasks";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    public TaskDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_TASKS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }
    public long addTask(String title) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, title);
            return db.insert(TABLE_TASKS, null, values);
        }
    }
    public int updateTask(int id, String title) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, title);
            return db.update(TABLE_TASKS, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        }
    }
    public int deleteTask(int id) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            return db.delete(TABLE_TASKS, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        }
    }
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_TASKS;
        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                    tasks.add(new Task(id, title));
                } while (cursor.moveToNext());
            }
        }
        return tasks;
    }
}
