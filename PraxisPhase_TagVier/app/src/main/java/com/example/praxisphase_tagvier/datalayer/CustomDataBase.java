package com.example.praxisphase_tagvier.datalayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class CustomDataBase {

    private ToDoAppDBHelper dbHelper;

    public CustomDataBase(Context context) {
       this.dbHelper = new ToDoAppDBHelper(context, ToDoAppDBHelper.DB_NAME,null, ToDoAppDBHelper.DB_VER);
    }

    public void insert(ContentValues contentValues){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        db.insert(ToDoAppDBHelper.TodoContract.TABLE_NAME,null,contentValues);
        db.setTransactionSuccessful();
        db.endTransaction();
    };
    public Cursor doSelect(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Entry> entries = new ArrayList<>();

        Cursor result = db.query(true,ToDoAppDBHelper.TABLE_NAMES[0],null,null,null,null,null,ToDoAppDBHelper.TodoContract.COLLUMN_1,null);
        /*
        if(result.moveToFirst()){
            int idIndex = result.getColumnIndex(ToDoAppDBHelper.TodoContract._ID);
            int dateIndex = result.getColumnIndex(ToDoAppDBHelper.TodoContract.COLLUMN_1);
            int placeIndex = result.getColumnIndex(ToDoAppDBHelper.TodoContract.COLLUMN_2);
            int titleIndex = result.getColumnIndex(ToDoAppDBHelper.TodoContract.COLLUMN_3);
            int descIndex = result.getColumnIndex(ToDoAppDBHelper.TodoContract.COLLUMN_4);

            do{
                Entry dbEntry = new Entry();
                dbEntry.setId(result.getString(idIndex));
                dbEntry.setDate(result.getString(dateIndex));
                dbEntry.setPlace(result.getString(placeIndex));
                dbEntry.setTitle(result.getString(titleIndex));
                dbEntry.setTitle(result.getString(descIndex));
                entries.add(dbEntry);
            }while (result.moveToNext());

        }
        */
        result.close();
        return result;
    }
    public void update(String value){};
    public void update(String value,int id){};
    public void find(String value){}
    public void delete(String value){};
    public void delete(int id){};
    public void closeDB(){
        dbHelper.close();
    };
}
