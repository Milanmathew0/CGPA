
package com.example.cgpacalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CGPACalculator.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_SUBJECTS = "subjects";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "subject_name";
    public static final String COL_GRADE = "grade_point";
    public static final String COL_CREDIT = "credits";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_SUBJECTS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT NOT NULL, "
                + COL_GRADE + " REAL NOT NULL, "
                + COL_CREDIT + " REAL NOT NULL"
                + ")";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db, int oldVersion, int newVersion) {
        // Add database migration logic here when the schema changes.
    }

    // CREATE: Insert a subject.
    public long insertSubject(String name, double grade, double credit) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_GRADE, grade);
        values.put(COL_CREDIT, credit);

        return db.insert(TABLE_SUBJECTS, null, values);
    }

    // READ: Get all subjects.
    public Cursor getAllSubjects() {
        SQLiteDatabase db = getReadableDatabase();

        return db.query(
                TABLE_SUBJECTS,
                null,
                null,
                null,
                null,
                null,
                COL_ID + " ASC"
        );
    }

    // UPDATE: Modify an existing subject.
    public int updateSubject(
            long id, String name, double grade, double credit) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_GRADE, grade);
        values.put(COL_CREDIT, credit);

        return db.update(
                TABLE_SUBJECTS,
                values,
                COL_ID + " = ?",
                new String[]{String.valueOf(id)}
        );
    }

    // DELETE: Remove a subject.
    public int deleteSubject(long id) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(
                TABLE_SUBJECTS,
                COL_ID + " = ?",
                new String[]{String.valueOf(id)}
        );
    }
}