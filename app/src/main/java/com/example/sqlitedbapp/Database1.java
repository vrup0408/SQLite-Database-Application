package com.example.sqlitedbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database1 extends SQLiteOpenHelper {

    String tname="depstar",col1="id",col2="name",col3="surname",col4="marks";

    public Database1(Context context) {
        super(context, "charusat.db", null, 1);
//        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table depstar (id integer primary key autoincrement, name text, surname text, marks integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+tname);
        onCreate(sqLiteDatabase);
    }

    public boolean insert(String id, String name, String surname, String marks)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,surname);
        contentValues.put(col4,marks);
        long res=sqLiteDatabase.insert(tname,null,contentValues);
        if(res==-1)
            return false;
        else
            return true;
    }

    public Cursor show(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+tname,null);
        return cursor;
    }

    public boolean updateDB(String id,String name,String surname,String marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col1,id);
        contentValues.put(col2,name);
        contentValues.put(col3,surname);
        contentValues.put(col4,marks);
        int res=db.update(tname,contentValues,"id=?",new String[] {id});
        if(res>0)
            return true;
        else
            return false;
    }

    public boolean deleteDB(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        int res=db.delete(tname,"id=?",new String[]{id});
        if(res>0)
            return true;
        else
            return false;
    }
}
