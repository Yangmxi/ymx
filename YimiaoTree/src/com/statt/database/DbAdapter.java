
package com.statt.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

public class DbAdapter {

    private static final String DATABASE_NAME = "TIANTI.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_TABLE = "schedule";
    private static final String DATABASE_CREATE = "create table schedule(_id integer PRIMARY KEY autoincrement, "
            + "teacher String not null,"
            + "student String not null,"
            + "class String not null,"
            + "subject String not null,"
            + "place String not null,"
            + "data_start String not null,"
            + "data_end String not null," + "time String not null);";

    public static final String KEY_ROWID = "_id";
    public static final String KEY_TEACHER = "teacher";
    public static final String KEY_STUDENT = "student";
    public static final String KEY_CLASS = "class";
    public static final String KEY_SUBJECT = "subject";
    public static final String KEY_PLACE = "place";
    public static final String KEY_DATA_START = "data_start";
    public static final String KEY_DATA_END = "data_end";
    public static final String KEY_TIME = "time";
    private Context mContext = null;
    private static DatabaseHelper mDbHelper;
    private static SQLiteDatabase mDb;

    public DbAdapter(Context cxt) {
        this.mContext = cxt;
    }

    public DbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public void DropTable(String tab) {
        mDbHelper.getWritableDatabase().execSQL("drop table" + tab);
    }

    public Cursor getAllTableInfo() {
        Cursor cur = mDb.rawQuery("SELECT * FROM schedule", null);
        cur.moveToFirst();
        return cur;
    }

    public Cursor getTableInfoById(int rowId) {
        Cursor cur = mDb.rawQuery("SELECT * FROM schedule where _id =" + rowId,
                null);
        cur.moveToFirst();
        return cur;
    }

    public boolean deleteById(long rowId) {

        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public boolean clearTable() {

        return mDb.delete(DATABASE_TABLE, null, null) > 0;
    }

    public Cursor getStudent(String studentName) {

        Cursor cur = mDb.query(DATABASE_TABLE, new String[] {
                KEY_ROWID,
                KEY_TEACHER, KEY_STUDENT, KEY_CLASS, KEY_SUBJECT, KEY_PLACE,
                KEY_DATA_START, KEY_DATA_END, KEY_TIME
        }, "student=?",
                new String[] {
                    studentName
                }, null, null, null);
        cur.moveToFirst();
        return cur;
    }

    public Cursor getTeacher(String teacherName) {
        Cursor cur = mDb.query(DATABASE_TABLE, new String[] {
                KEY_ROWID,
                KEY_TEACHER, KEY_STUDENT, KEY_CLASS, KEY_SUBJECT, KEY_PLACE,
                KEY_DATA_START, KEY_DATA_END, KEY_TIME
        }, "teacher=?",
                new String[] {
                    teacherName
                }, null, null, null);
        cur.moveToFirst();
        return cur;
    }

    public Cursor getSQL(String teacher, String student, String classes, String subject,
            String place) {
        StringBuilder sb = new StringBuilder("select * from schedule");
        if (!TextUtils.isEmpty(teacher) || !TextUtils.isEmpty(student) || !TextUtils.isEmpty(classes)
                || !TextUtils.isEmpty(subject) || !TextUtils.isEmpty(place)) {
            sb = sb.append(" where");
        }

        boolean has = false;

        if (!TextUtils.isEmpty(teacher)) {
            sb.append(" teacher like '" + teacher + "'");
            has = true;
        }

        if (!TextUtils.isEmpty(student)) {
            if (has) {
                sb.append(" and");
            }

            sb.append(" student like '" + student + "'");
            has = true;
        }

        if (!TextUtils.isEmpty(classes)) {
            if (has) {
                sb.append(" and");
            }

            sb.append(" class like '" + classes + "'");
            has = true;
        }

        if (!TextUtils.isEmpty(subject)) {
            if (has) {
                sb.append(" and");
            }

            sb.append(" subject like '" + subject + "'");
            has = true;
        }

        if (!TextUtils.isEmpty(place)) {
            if (has) {
                sb.append(" and");
            }

            sb.append(" place like '" + place + "'");
        }
        sb.append(" order by _id asc");
        Log.d("sql", sb.toString());
        Cursor cursor = mDb.rawQuery(sb.toString(), null);
        cursor.moveToFirst();
        return cursor;
    }

    public boolean updateTable(long rowId, String teacher, String student,
            String classes, String subject, String place, String data_start,
            String data_end, String time) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TEACHER, teacher);
        initialValues.put(KEY_STUDENT, student);
        initialValues.put(KEY_CLASS, classes);
        initialValues.put(KEY_SUBJECT, subject);
        initialValues.put(KEY_PLACE, place);
        initialValues.put(KEY_DATA_START, data_start);
        initialValues.put(KEY_DATA_END, data_end);
        initialValues.put(KEY_TIME, time);

        return mDb.update(DATABASE_TABLE, initialValues, KEY_ROWID + "="
                + rowId, null) > 0;
    }

    public boolean insertTable(String teacher, String student, String classes,
            String subject, String place, String data_start, String data_end,
            String time) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TEACHER, teacher);
        initialValues.put(KEY_STUDENT, student);
        initialValues.put(KEY_CLASS, classes);
        initialValues.put(KEY_SUBJECT, subject);
        initialValues.put(KEY_PLACE, place);
        initialValues.put(KEY_DATA_START, data_start);
        initialValues.put(KEY_DATA_END, data_end);
        initialValues.put(KEY_TIME, time);

        return mDb.insert(DATABASE_TABLE, null, initialValues) > 0;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS schedule");
            onCreate(db);
        }

    }
}
