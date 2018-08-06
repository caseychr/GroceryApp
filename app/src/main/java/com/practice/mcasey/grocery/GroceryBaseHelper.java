package com.practice.mcasey.grocery;

import static com.practice.mcasey.grocery.GroceryDBSchema.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GroceryBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "groceryBase.db";

    public GroceryBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ GroceryTable.NAME+"("+
        "_id integer primary key autoincrement, "+
        GroceryTable.Cols.UUID+", "+
        GroceryTable.Cols.GROCERY_NAME+", "+
        GroceryTable.Cols.GROCERY_AMOUNT+", "+
        GroceryTable.Cols.GROCERY_LOCATION+", "+
        GroceryTable.Cols.GROCERY_COMPLETED+", "+
        GroceryTable.Cols.GROCERY_RECURRING+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
