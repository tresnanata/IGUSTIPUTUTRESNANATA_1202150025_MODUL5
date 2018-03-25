package com.example.khimi.igustipututresnanata_1202150025_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Khimi on 3/25/2018.
 */

public class Database extends SQLiteOpenHelper{
    //deklarasi objek
    Context mContext;
    SQLiteDatabase mDatabase;

    //variable
    public static final String nama_database = "db_modul5";
    public static final String nama_tabel = "tb_daftartodo";
    public static final String column_1 = "todo";
    public static final String column_2 = "description";
    public static final String column_3 = "priority";

    //konstruktor
    public Database(Context context) {
        super(context, nama_database, null, 1);
        this.mContext = context;
        mDatabase = this.getWritableDatabase();
        mDatabase.execSQL("create table if not exists "+nama_tabel+" (todo varchar(50) primary key, description varchar(50), priority varchar(3))");
    }

    //database dibuat
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+nama_tabel+" (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+nama_tabel);
        onCreate(sqLiteDatabase);
    }

    public boolean inputdata(AddData list) {
        //mencocokan kolom beserta nilainya
        ContentValues val = new ContentValues();
        val.put(column_1, list.getTodo());
        val.put(column_2, list.getDesccription());
        val.put(column_3, list.getPriority());
        long hasil = mDatabase.insert(nama_tabel, null, val);
        if (hasil==-1) {
            return false;
        }else {
            return true;
        }
    }

    //untuk hapus data pada database
    public boolean removedata(String ToDo) {
        return mDatabase.delete(nama_tabel, column_1+"=\""+ToDo+"\"", null)>0;
    }

    //digunakan untuk mencatat dan membaca database
    public void readdata(ArrayList<AddData> daftar){
        Cursor cursor = this.getReadableDatabase().rawQuery("select todo, description, priority from "+nama_tabel, null);
        while (cursor.moveToNext()){
            daftar.add(new AddData(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
}


