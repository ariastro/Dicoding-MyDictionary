package com.example.astronout.mydictionary.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.astronout.mydictionary.model.Kamus;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.astronout.mydictionary.db.DatabaseContract.KamusColumns.KATA;
import static com.example.astronout.mydictionary.db.DatabaseContract.KamusColumns.TRANSLATE;

public class KamusHelper {

    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public KamusHelper(Context context){
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<Kamus> getDataByKata(String search, boolean isEnglish){
        String DATABASE_TABLE = isEnglish ? DatabaseContract.TABLE_ENGLISH : DatabaseContract.TABLE_INDONESIA;
        String result = "";
        Cursor cursor = database.rawQuery("SELECT * FROM " + DATABASE_TABLE +
                " WHERE " + DatabaseContract.KamusColumns.KATA + " LIKE '%" + search.trim() + "%'", null);
        cursor.moveToFirst();
        ArrayList<Kamus> arrayList = new ArrayList<>();
        Kamus kamus;
        if (cursor.getCount()>0) {
            do {
                kamus = new Kamus();
                kamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamus.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                kamus.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATE)));

                arrayList.add(kamus);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<Kamus> getAllData(boolean isEnglish){
        String DATABASE_TABLE = isEnglish ? DatabaseContract.TABLE_ENGLISH : DatabaseContract.TABLE_INDONESIA;
        Cursor cursor = database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " ORDER BY " + _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<Kamus> arrayList = new ArrayList<>();
        Kamus kamus;
        if (cursor.getCount()>0) {
            do {
                kamus = new Kamus();
                kamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamus.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                kamus.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATE)));


                arrayList.add(kamus);
                cursor.moveToNext();


            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Kamus kamus, boolean isEnglish){
        String DATABASE_TABLE = isEnglish ? DatabaseContract.TABLE_ENGLISH : DatabaseContract.TABLE_INDONESIA;
        ContentValues initialValues =  new ContentValues();
        initialValues.put(KATA, kamus.getKata());
        initialValues.put(TRANSLATE, kamus.getTranslate());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }

    public void insertTransaction(ArrayList<Kamus> kamusModels, boolean isEnglish) {
        String DATABASE_TABLE = isEnglish ? DatabaseContract.TABLE_ENGLISH : DatabaseContract.TABLE_INDONESIA;
        String sql = "INSERT INTO " + DATABASE_TABLE + " (" +
                DatabaseContract.KamusColumns.KATA + ", " +
                DatabaseContract.KamusColumns.TRANSLATE + ") VALUES (?, ?)";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);
        for (int i = 0; i < kamusModels.size(); i++) {
            stmt.bindString(1, kamusModels.get(i).getKata());
            stmt.bindString(2, kamusModels.get(i).getTranslate());
            stmt.execute();
            stmt.clearBindings();
        }

        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public int update(Kamus kamus, boolean isEnglish){
        String DATABASE_TABLE = isEnglish ? DatabaseContract.TABLE_ENGLISH : DatabaseContract.TABLE_INDONESIA;
        ContentValues args = new ContentValues();
        args.put(KATA, kamus.getKata());
        args.put(TRANSLATE, kamus.getTranslate());
        return database.update(DATABASE_TABLE, args, _ID + "= '" + kamus.getId() + "'", null);
    }


    public int delete(int id, boolean isEnglish){
        String DATABASE_TABLE = isEnglish ? DatabaseContract.TABLE_ENGLISH : DatabaseContract.TABLE_INDONESIA;
        return database.delete(DATABASE_TABLE, _ID + " = '"+id+"'", null);
    }
}
