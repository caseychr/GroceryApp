package com.practice.mcasey.grocery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroceryLab {

    private static GroceryLab sGroceryLab;

    private List<GroceryItem> mGroceryItems;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static GroceryLab get(Context context)
    {
        if(sGroceryLab == null)
            sGroceryLab = new GroceryLab(context);
        return sGroceryLab;
    }

    private static ContentValues getContentValues(GroceryItem groceryItem)
    {
        ContentValues values = new ContentValues();
        values.put(GroceryDBSchema.GroceryTable.Cols.UUID, groceryItem.getUUID().toString());
        values.put(GroceryDBSchema.GroceryTable.Cols.GROCERY_NAME, groceryItem.getGroceryName());
        values.put(GroceryDBSchema.GroceryTable.Cols.GROCERY_AMOUNT, groceryItem.getGroceryAmount());
        values.put(GroceryDBSchema.GroceryTable.Cols.GROCERY_LOCATION, groceryItem.getGroceryLocation());
        values.put(GroceryDBSchema.GroceryTable.Cols.GROCERY_COMPLETED, groceryItem.isCompleted() ? 1 : 0);
        values.put(GroceryDBSchema.GroceryTable.Cols.GROCERY_RECURRING, groceryItem.isRecurring() ? 1 : 0);
        return values;
    }

    private GroceryLab(Context context)
    {
        mContext = context.getApplicationContext();
        mDatabase = new GroceryBaseHelper(mContext).getWritableDatabase();
        //mGroceryItems = new ArrayList<>();
        /*for(int i=0;i<100;i++)
        {
            GroceryItem crime = new GroceryItem();
            crime.setTitle("Crime #"+i);
            crime.setSolved(i%2==0);
            crime.setRequiresPolice(i%4==0);
            mCrimes.add(crime);
        }*/
    }

    public List<GroceryItem> getGroceries() {
        List<GroceryItem> groceryItems = new ArrayList<>();
        GroceryCursorWrapper cursor = queryGroceries(null, null);
        try
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                groceryItems.add(cursor.getGrocery());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return groceryItems;
    }

    public GroceryItem getGrocery(UUID id)
    {
        GroceryCursorWrapper cursor = queryGroceries(GroceryDBSchema.GroceryTable.Cols.UUID+" = ?",
                new String[] {id.toString()});

        try
        {
            if(cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getGrocery();
        }finally {
            cursor.close();
        }
    }

    public void addGrocery(GroceryItem groceryItem)
    {
        ContentValues values = getContentValues(groceryItem);
        mDatabase.insert(GroceryDBSchema.GroceryTable.NAME, null, values);
    }

    public void deleteGrocery(GroceryItem crime) {
        mDatabase.delete(GroceryDBSchema.GroceryTable.NAME,
                GroceryDBSchema.GroceryTable.Cols.UUID + " = ?", new String[]{crime.getUUID().toString()});
    }

    public void updateGrocery(GroceryItem groceryItem)
    {
        String uuidString = groceryItem.getUUID().toString();
        ContentValues values = getContentValues(groceryItem);
        mDatabase.update(GroceryDBSchema.GroceryTable.NAME, values,
                GroceryDBSchema.GroceryTable.Cols.UUID+" = ?", new String[] { uuidString});
    }

    private GroceryCursorWrapper queryGroceries(String whereClause, String[] whereArgs)
    {
        Cursor cursor =  mDatabase.query(
                GroceryDBSchema.GroceryTable.NAME,
                null,
                whereClause,
                whereArgs, null, null, null
        );
        return new GroceryCursorWrapper(cursor);
    }
}
