package com.example.patrickcummins.videogamepricetracker.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.patrickcummins.videogamepricetracker.Models.GiantBombModels.Result;
import com.example.patrickcummins.videogamepricetracker.R;

import java.util.ArrayList;

/**
 * Created by patrickcummins on 8/22/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper INSTANCE;
    private static final String CREATE_SAVED_GAMES_TABLE = "CREATE TABLE " + SavedGamesValues.TABLE_NAME + " (" + SavedGamesValues._ID + " INTEGER PRIMARY KEY, " + SavedGamesValues.ID + " INTEGER, " + SavedGamesValues.IMAGE_URL + " TEXT, " + SavedGamesValues.NAME + " TEXT)";
    private static final String DROP_SAVED_GAMES_TABLE = "DROP TABLE IF EXISTS " + SavedGamesValues.TABLE_NAME;


    public static abstract class SavedGamesValues implements BaseColumns {
        public static final String TABLE_NAME = "SavedGamesTable";
        public static final String NAME = "GameName";
        public static final String IMAGE_URL = "Image_Url";
        public static final String ID = "id";
    }

    private DatabaseHelper(Context context) {
        super(context, "db", null, 1);

    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseHelper(context);
        }
        return INSTANCE;
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

    public void insertGameTableRow(Result game) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SavedGamesValues.NAME, game.getName());
        try {
            values.put(SavedGamesValues.IMAGE_URL, game.getImage().getIcon_url());
        } catch (Exception e) {
            values.put(SavedGamesValues.IMAGE_URL, R.string.null_string);
        }
        values.put(SavedGamesValues.ID, game.getId());
        db.insertOrThrow(SavedGamesValues.TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<String> getAllSavedGames() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT " + SavedGamesValues.NAME + " FROM " + SavedGamesValues.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        ArrayList<String> allGames = new ArrayList<>();
        while (cursor.moveToNext()) {
            allGames.add(cursor.getString(cursor.getColumnIndex(SavedGamesValues.NAME)));

        }
        return allGames;
    }

    public int getGameId(int position) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT " + SavedGamesValues.ID + " FROM " + SavedGamesValues.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        int counter = 0;
        while (cursor.moveToNext()) {
            if (counter == position) {
                return cursor.getInt(cursor.getColumnIndex(SavedGamesValues.ID));
            } else {
                counter++;
            }


        }
        return -1;

    }
}
