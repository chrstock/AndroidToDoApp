package com.stock.christian.todo_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TaskDataSource {
    private static final String LOG_TAG = TaskDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private TaskDbHelper dbHelper;

    private String[] columns = {
            TaskDbHelper.COLUMN_ID,
            TaskDbHelper.COLUMN_TITLE,
            TaskDbHelper.COLUMN_IMAGE
    };

    public TaskDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new TaskDbHelper(context);
    }


    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    public Task createTask(String title, int image) {
        ContentValues values = new ContentValues();
        values.put(TaskDbHelper.COLUMN_TITLE, title);
        values.put(TaskDbHelper.COLUMN_IMAGE, image);

        long insertId = database.insert(TaskDbHelper.TASK_LIST, null, values);

        Cursor cursor = database.query(TaskDbHelper.TASK_LIST, columns, TaskDbHelper.COLUMN_ID + "=" + insertId, null, null, null, null);

        cursor.moveToFirst();
        Task task = cursorToTask(cursor);
        cursor.close();

        return task;
    }

    public void deleteTask(Task task) {
        long id = task.getId();

        database.delete(TaskDbHelper.TASK_LIST,
                TaskDbHelper.COLUMN_ID + "=" + id, null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + "Inhalt: " + task.toString());
    }

    public Task cursorToTask(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(TaskDbHelper.COLUMN_ID);
        int idTitle = cursor.getColumnIndex(TaskDbHelper.COLUMN_TITLE);
        int idImage = cursor.getColumnIndex(TaskDbHelper.COLUMN_IMAGE);

        String title = cursor.getString(idTitle);
        int image = cursor.getInt(idImage);
        long id = cursor.getLong(idIndex);

        return new Task(title, image, id);
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();

        Cursor cursor = database.query(TaskDbHelper.TASK_LIST, columns, null, null, null, null, null);

        cursor.moveToFirst();
        Task task;

        while (!cursor.isAfterLast()) {
            task = cursorToTask(cursor);
            taskList.add(task);
            Log.d(LOG_TAG, "ID: " + task.getId() + ", Inhalt: " + task.toString());
            cursor.moveToNext();
        }
        cursor.close();

        return taskList;
    }
}
