package com.example.workshop4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Db extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "factions.db";

    public Db(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE factions (id INTEGER, name TEXT, strength INTEGER, relationship INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2) {}

}
