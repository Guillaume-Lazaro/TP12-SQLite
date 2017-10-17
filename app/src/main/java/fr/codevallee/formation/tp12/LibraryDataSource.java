package fr.codevallee.formation.tp12;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by guillaumelazaro on 16/10/2017.
 */

public class LibraryDataSource {


    private final LibraryDBHelper helper;
    private SQLiteDatabase db;

    public LibraryDataSource(Context context) {
        helper = new LibraryDBHelper(context);
    }

    public SQLiteDatabase getDB() {
        if (db == null)
            open();
        return db;
    }

    public void open() throws SQLException {
        db = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    // factories
    public UserDAO newUserDAO() {
        return new UserDAO(this);
    }
}