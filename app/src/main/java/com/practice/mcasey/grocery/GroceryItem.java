package com.practice.mcasey.grocery;

import java.util.ArrayList;
import java.util.List;

public class GroceryItem {

    private String mGroceryName;
    private String mGroceryAmount;
    private String mGroceryLocation;
    private boolean mCompleted;
    private boolean mRecurring;

    public GroceryItem(){}

    public GroceryItem(String groceryName, String groceryAmount, String groceryLocation, boolean completed,
            boolean recurring) {
        mGroceryName = groceryName;
        mGroceryAmount = groceryAmount;
        mGroceryLocation = groceryLocation;
        mCompleted = completed;
        mRecurring = recurring;
    }

    public String getGroceryName() {
        return mGroceryName;
    }

    public void setGroceryName(String groceryName) {
        mGroceryName = groceryName;
    }

    public String getGroceryAmount() {
        return mGroceryAmount;
    }

    public void setGroceryAmount(String groceryAmount) {
        mGroceryAmount = groceryAmount;
    }

    public String getGroceryLocation() {
        return mGroceryLocation;
    }

    public void setGroceryLocation(String groceryLocation) {
        mGroceryLocation = groceryLocation;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    public boolean isRecurring() {
        return mRecurring;
    }

    public void setRecurring(boolean recurring) {
        mRecurring = recurring;
    }

    public static List<GroceryItem> sGroceryItems = new ArrayList<>();

    public static void res()
    {
        GroceryItem groceryItem;
        for(int i=0;i<20;i++)
        {
            groceryItem = new GroceryItem("Item_"+i, "5", "Publix", false, false);
            sGroceryItems.add(groceryItem);
        }
    }
}
