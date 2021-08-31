package com.example.workshop4;

import android.database.Cursor;
import android.database.CursorWrapper;

public class FactionCursor extends CursorWrapper {
    public FactionCursor(Cursor cursor) { super(cursor); }
    public Faction getFaction()
    {
        int id = getInt(getColumnIndex("id"));
        String name = getString(getColumnIndex("name"));
        int strength = getInt(getColumnIndex("strength"));
        int relationship = getInt(getColumnIndex("relationship"));
        return new Faction(id, name, strength, relationship);
    }
}
