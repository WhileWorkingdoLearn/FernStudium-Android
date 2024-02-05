package com.example.praxisphase_tagvier.datalayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class ToDoAppDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME= "";
    public static final int DB_VER= 1;


    private static final String SEP = " , ";
    private static final String SPACE = " ";
    private static final String DATA_TEXT = "TEXT";
    private static final String DATA_INTEGER = "INTEGER";
    private static final String PRIMARY_KEY = "PRIMARY KEY";



    public class TodoContract implements BaseColumns {
        public final static String TABLE_NAME = "TodoTable";
        public final static String COLLUMN_1 = "Date";
        public final static String COLLUMN_2 = "Place";
        public final static String COLLUMN_3 = "Title";
        public final static String COLLUMN_4 = "Descritption";
        public final String[] CollumnNames ={COLLUMN_1,COLLUMN_2,COLLUMN_3,COLLUMN_4};
    }

    private static final String TABLE_CREATE= "CREATE TABLE " +
            TodoContract.TABLE_NAME + "(" +
            TodoContract._ID + SPACE + DATA_INTEGER + SPACE + PRIMARY_KEY + SEP +
            TodoContract.COLLUMN_1 + SPACE + DATA_INTEGER + SEP  +
            TodoContract.COLLUMN_2 + SPACE + DATA_TEXT + SEP  +
            TodoContract.COLLUMN_3 + SPACE + DATA_TEXT + SEP  +
            TodoContract.COLLUMN_4 + SPACE + DATA_TEXT + SEP +
        ")";

    private static final String TABLE_DELETE = "DROP TABLE IF EXISTS " + TodoContract.TABLE_NAME;

    public static final String[] TABLE_NAMES = {TodoContract.TABLE_NAME};

    private boolean inTransaction = false;

    public ToDoAppDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DB_NAME,factory,DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(TABLE_DELETE);
            onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }

    public boolean getInTransaction(){return inTransaction;}

    private void beginTransaction() {
        inTransaction = true;
    }
    private void setTransactionSuccessful() {
        inTransaction = false;
    }
}
