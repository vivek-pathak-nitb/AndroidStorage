package example.storage.com.androidstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Manager class for performing DB operations.
 */

class DbManager {

    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    DbManager(final Context ctx) {
        context = ctx;
    }

    public DbManager open() throws SQLException {
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    void insert(final String name,
                final String desc) {
        final ContentValues contentValue = new ContentValues();
        contentValue.put(DbHelper.SUBJECT, name);
        contentValue.put(DbHelper.DESC, desc);
        database.insert(DbHelper.TABLE_NAME, null, contentValue);
    }

    Cursor fetch() {
        final String[] columns = new String[]{DbHelper._ID, DbHelper.SUBJECT, DbHelper.DESC};
        final Cursor cursor = database.query(DbHelper.TABLE_NAME, columns, null, null, null, null,
                null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    int update(final long _id,
               final String name,
               final String desc) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.SUBJECT, name);
        contentValues.put(DbHelper.DESC, desc);
        return database.update(DbHelper.TABLE_NAME, contentValues, DbHelper._ID + " = " + _id,
                null);
    }

    void delete(final long _id) {
        database.delete(DbHelper.TABLE_NAME, DbHelper._ID + "=" + _id, null);
    }
}
