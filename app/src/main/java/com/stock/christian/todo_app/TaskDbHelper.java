package com.stock.christian.todo_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TaskDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = TaskDbHelper.class.getSimpleName();

    public static final String DB_NAME = "task_list.db";
    public static final int DB_VERSION = 1;

    public static final String TASK_LIST = "task_list";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_IMAGE= "image";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TASK_LIST +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_IMAGE + " INTEGER NOT NULL);";

    public TaskDbHelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank" + getDatabaseName() + " erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            Log.d(LOG_TAG,"Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);

        }catch (Exception ex){
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
