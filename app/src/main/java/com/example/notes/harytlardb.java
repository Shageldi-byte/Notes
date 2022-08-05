package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class harytlardb extends SQLiteOpenHelper {
    public static final String DATA_BASE_NAME="harytlardb.db";
    public static final String Table_name="studengt_table";


    public harytlardb(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          db.execSQL("CREATE TABLE "+Table_name+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,haryt_ady TEXT,bahasy TEXT,sany TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("DROP TABLE IF EXISTS "+Table_name);
          onCreate(db);
    }

    public boolean insertData(String haryt_ady,String bahasy,String sany){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("haryt_ady",haryt_ady);
        contentValues.put("bahasy",bahasy);
        contentValues.put("sany",sany);



       long result= db.insert(Table_name,null,contentValues);
       if(result==-1){
           return false;
       }else{
           return true;
       }
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+Table_name,null);
        return res;
    }
    public boolean updateData(String id,String sany){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("sany",sany);
        db.update(Table_name,contentValues,"ID=?",new String[]{id});
        return true;
    }

    public Cursor getSelect(String haryt_ady)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Table_name+" WHERE haryt_ady = '"+haryt_ady+"'", null);
        return cursor;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(Table_name,"ID=?",new String[]{id});

    }

    public void all(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(db);
    }

}
