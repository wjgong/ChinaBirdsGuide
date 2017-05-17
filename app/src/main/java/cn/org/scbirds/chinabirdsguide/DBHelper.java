package cn.org.scbirds.chinabirdsguide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by gonggwen on 2017/5/10.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "china_birds.db";
    public static String DATABASE_PATH = "";

    private SQLiteDatabase mDataBase;
    private final Context mContext;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        DATABASE_PATH = mContext.getDatabasePath(DATABASE_NAME).toString();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
/*        String CREATE_TABLE_BIRD = "CREATE TABLE " + Bird.TABLE + "("
                + Bird.KEY_ID + " INTEGER, "
                + Bird.KEY_NAME_CN + " TEXT )";

        db.execSQL(CREATE_TABLE_BIRD);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
/*        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Bird.TABLE);

        // Create tables again
        onCreate(db);*/
        if (newVersion > oldVersion) {
            Log.v("Database Upgrade", "Database version is higher than the old");
            deleteDataBase();
        }
    }

    // Create an empty database on the system
    public void createDatabase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            Log.v("DBHelper", "db exists");
        } else {
            this.getReadableDatabase();
            try {
                this.close();
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    // Check database already exist or not
    private boolean checkDataBase() {
        boolean checkDB = false;

        try {
            String path = DATABASE_PATH;
            File dbFile = new File(path);
            checkDB = dbFile.exists();
        } catch (SQLiteException e) {
            Log.d("DBHelper", "cannot check if DB file exists");
        }
        return checkDB;
    }

    // Copy database from local assets-folder to the just created empty database in system folder
    private void copyDataBase() throws IOException {
        String outFileName = DATABASE_PATH;
        OutputStream outputStream = new FileOutputStream(outFileName);
        InputStream inputStream = mContext.getAssets().open(DATABASE_NAME);
        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }

    public void openDataBase() throws SQLiteException {
        String path = DATABASE_PATH;
        mDataBase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    }

    public synchronized void closeDataBase() throws SQLiteException {
        if (mDataBase != null) {
            mDataBase.close();
        }
        super.close();
    }

    // Delete database
    public void deleteDataBase() {
        File file = new File(DATABASE_PATH);
        if (file.exists()) {
            file.delete();
            System.out.println("delete database file.");
        }
    }
}
