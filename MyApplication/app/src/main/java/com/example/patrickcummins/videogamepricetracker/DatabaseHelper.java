package com.example.patrickcummins.videogamepricetracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by patrickcummins on 8/22/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper INSTANCE;
    private static final String CREATE_SAVED_GAMES_TABLE = "CREATE TABLE " + SavedGamesValues.TABLE_NAME + " (" + SavedGamesValues._ID + " INTEGER PRIMARY KEY, " + SavedGamesValues.NAME + " TEXT)";
    private static final String DROP_SAVED_GAMES_TABLE = "DROP TABLE IF EXISTS " + SavedGamesValues.TABLE_NAME;

    public static abstract class SavedGamesValues implements BaseColumns {
        public static final String TABLE_NAME = "SavedGamesTable";
        public static final String NAME = "GameName";
    }

    private DatabaseHelper(Context context) {
        super(context, "db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_SAVED_GAMES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_SAVED_GAMES_TABLE);
        onCreate(sqLiteDatabase);

    }
    public ArrayList<String> getAllSavedGames(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query ="SELECT " +SavedGamesValues.NAME + " FROM " + SavedGamesValues.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        ArrayList<String> allGames = new ArrayList<>();
        while (cursor.moveToNext()){
            allGames.add(cursor.getString(cursor.getColumnIndex(SavedGamesValues.NAME)));

        }
        return allGames;
    }
}
