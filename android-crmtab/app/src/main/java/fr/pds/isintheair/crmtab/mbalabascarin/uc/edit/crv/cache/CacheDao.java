package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Muthu on 13/12/2015.
 */
public class CacheDao extends SQLiteOpenHelper {

    static String DB_NAME = "CRV_PREFORMATED_MESSAGE";
    String TABLE_NAME ="message";
    String TABLE_NAME_VISIT_REPORT ="crv";
    int BDVersion = 1;

    public CacheDao(Context context) {
        super(context, DB_NAME, null, 1);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists " + TABLE_NAME + "(id integer primary key autoincrement, message varchar);");
        sqLiteDatabase.execSQL("create table if not exists " + TABLE_NAME_VISIT_REPORT + "(id integer primary key autoincrement, report text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insertMessage(String message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("message", message);
        db.insert(TABLE_NAME, null, contentValues);
        return true;

    }

    public boolean insertReport(String reportJson){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("report", reportJson);
        db.insert(TABLE_NAME_VISIT_REPORT, null, contentValues);
        return true;

    }
    public Integer deleteMessage (String message)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "message = ? ",
                new String[] { message });
    }


    public ArrayList<String> getAllMessages()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("message")));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllReports()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME_VISIT_REPORT, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("report")));
            res.moveToNext();
        }
        return array_list;
    }
}
