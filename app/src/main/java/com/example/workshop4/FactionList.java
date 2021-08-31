package com.example.workshop4;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maintains the overall dataset; specifically of course all the different factions.
 */
public class FactionList
{
    private List<Faction> factions = new ArrayList<>();
    private SQLiteDatabase db;
    public FactionList() {}

    public void load(Context context)
    {
        if(this.db == null) {
            this.db = new Db(context.getApplicationContext()).getWritableDatabase();
        }
        FactionCursor cursor = new FactionCursor(db.query("factions", null, null, null, null, null, null));
        try
        {
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                factions.add(cursor.getFaction());
                cursor.moveToNext();
            }
        }
        finally
        {
            cursor.close();
        }
    }

    public int size()
    {
        return factions.size();
    }

    public Faction get(int i)
    {
        return factions.get(i);
    }

    public int add(Faction newFaction)
    {
        factions.add(newFaction);
        ContentValues cv = new ContentValues();
        cv.put("id", newFaction.getId());
        cv.put("name", newFaction.getName());
        cv.put("strength", newFaction.getStrength());
        cv.put("relationship", newFaction.getRelationship());
        db.insert("factions", null, cv);

        return factions.size() - 1; // Return insertion point
    }

    public void edit(Faction newFaction)
    {
        ContentValues cv = new ContentValues();
        cv.put("id", newFaction.getId());
        cv.put("name", newFaction.getName());
        cv.put("strength", newFaction.getStrength());
        cv.put("relationship", newFaction.getRelationship());

        String[] whereValue = { String.valueOf(newFaction.getId()) };
        db.update("factions", cv, "id = ?", whereValue);

    }

    public void remove(Faction rmFaction)
    {
        factions.remove(rmFaction);
        String[] whereValue = {String.valueOf(rmFaction.getId())};
        db.delete("factions", "id = ?", whereValue);
    }
}
