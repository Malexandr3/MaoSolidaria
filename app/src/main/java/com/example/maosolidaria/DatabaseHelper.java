package com.example.maosolidaria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MaoSolidaria.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_DONORS = "Doadores";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "nome";
    private static final String COL_CONTACT = "contato";
    private static final String COL_FOOD_TYPE = "tipo_de_alimento";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableDonors = "CREATE TABLE " + TABLE_DONORS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_CONTACT + " TEXT, " +
                COL_FOOD_TYPE + " TEXT)";
        db.execSQL(createTableDonors);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DONORS);
        onCreate(db);
    }

    public boolean insertDonation(String name, String contact, String foodType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_CONTACT, contact);
        contentValues.put(COL_FOOD_TYPE, foodType);

        long result = db.insert(TABLE_DONORS, null, contentValues);
        return result != -1;
    }

    public Cursor getAllDonations() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_DONORS, null);
    }

    public void deleteDonation(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DONORS, COL_ID + "=?", new String[]{String.valueOf(id)});
    }
}
