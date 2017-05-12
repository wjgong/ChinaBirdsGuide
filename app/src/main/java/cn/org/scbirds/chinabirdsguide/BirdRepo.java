package cn.org.scbirds.chinabirdsguide;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.io.IOException;

/**
 * Created by gonggwen on 2017/5/11.
 */

public class BirdRepo {
    private DBHelper mDbHelper;

    public BirdRepo(Context context) {
        mDbHelper = new DBHelper(context);
        try {
            mDbHelper.createDatabase();
        } catch (IOException ioe) {
            throw  new Error("Unable to create database");
        }
        try {
            mDbHelper.openDataBase();
        } catch (SQLiteException sqle) {
            throw sqle;
        }
    }

    public Cursor getBirdList() {
        // Open connection to read only
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String selectQuery = "SELECT  id as " +
                Bird.KEY_ID + "," +
                Bird.KEY_NAME_CN +
                " FROM " + Bird.TABLE;

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }

    public Cursor getBirdListByKeyword(String search) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String selectQuery = "SELECT  id as " +
                Bird.KEY_ID + "," +
                Bird.KEY_NAME_CN +
                " FROM " + Bird.TABLE +
                " WHERE " + Bird.KEY_NAME_CN + "  LIKE  '%" + search + "%'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }
}
